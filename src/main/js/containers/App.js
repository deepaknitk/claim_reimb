import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {withRouter} from 'react-router';
import isEqual from 'lodash.isequal';
import * as actions from '../actions';
import '../styles/index.scss';
import Routes from '../routes.jsx';
import OverlayMessage from '../components/OverlayMessage';
import loaderImg from '../images/loader.gif';
import ENGLISH_LOCALE from '../locales/lts.en.js';
import BAHASA_LOCALE from '../locales/lts.id.js';
import Header from '../components/Header';
import Footer from '../components/Footer';
import axios from 'axios/index';
import Sidebar from '../components/Sidebar';

let USER = JSON.parse(localStorage.getItem('user'))
import SideBar from '../components/Sidebar';

class App extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            systemLang: '',
            successMessage: '',
            errorMessage: '',
            ltsAlert: null,
            localeFile: {},
            uiConfigs: {},
            multilineImageUploadProgress: 0,
            userName: '',
            profileImage: '',
            isRegisterActive: false,
            locationUrl: ''
        };
    }

    authUser() {
        if (!USER) {
            if (this.props.location.search !== '') {
                let code = this.props.location.search.split('=')[1].split('&')[0];

                axios({
                    method: 'get',
                    url: 'https://slack.com/api/oauth.access?client_id=304468210898.305541541543&client_secret=d93eb31dd7fbb9217ceeb713da523b2d&code=' + code,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                    .then(function (response) {
                        console.log(response);
                        this.setState({userName: response.data.user.name});
                        let tempUser = {
                            email: response.data.user.email,
                            name: response.data.user.name,
                            avatar_24: response.data.user.image_24,
                            avatar_192: response.data.user.image_192
                        };
                        localStorage.setItem('user', JSON.stringify(tempUser));
                        axios({
                            method: 'get',
                            url: 'http://localhost:8080/claims/user/findById/?userEmail=' + tempUser.email
                        })
                            .then(function (res) {
                                console.log('Response', res);
                                this.setState({isRegisterActive: res.data.success});
                                tempUser.empId = res.data.data.employeeId;
                                console.log(tempUser);
                                console.log('success', this.state.isRegisterActive);
                                localStorage.setItem('user', JSON.stringify(tempUser));
                            }.bind(this));
                    }.bind(this));
            }
        } else {
            this.setState({userName: USER.name});
            this.setState({profileImage: USER.avatar_192});
        }
    }

    componentDidMount() {

        this.setState({locationUrl: this.props.location.pathname});
        this.authUser();
    }

    componentWillMount() {
        localStorage.setItem('systemLang', 'en');
        // if (localStorage.getItem('systemLang')) {
        //     this.setState({systemLang: localStorage.getItem('systemLang')});
        // }
        // else {
        //     //setting default lang to indonessian(id)
        //     localStorage.setItem('systemLang', 'en');
        // }
        this.handleLanguageChange(localStorage.getItem('systemLang'));
    }

    componentWillReceiveProps(nextProps) {
        if (!isEqual(nextProps.appState.message.successMessage, this.state.successMessage) &&
            nextProps.appState.message.successMessage) {
            this.setState({successMessage: nextProps.appState.message.successMessage});
            this.removeMsgAfterSomeTime(2);
        }
        if (!isEqual(nextProps.appState.message.errorMessage, this.state.errorMessage) &&
            nextProps.appState.message.errorMessage) {
            this.setState({errorMessage: nextProps.appState.message.errorMessage});
            this.removeMsgAfterSomeTime(5);
            this.animateToTop();
        }
        if (!isEqual(nextProps.appState.app.uiConfigs, this.state.uiConfigs) &&
            nextProps.appState.app.uiConfigs) {
            this.setState({uiConfigs: nextProps.appState.app.uiConfigs});
        }
    }

    handleLanguageChange(lang) {
        if (lang === 'en') {
            this.setState({
                localeFile: ENGLISH_LOCALE,
                systemLang: 'en'
            });
            localStorage.setItem('systemLang', 'en');
        }
        else {
            this.setState({
                localeFile: BAHASA_LOCALE,
                systemLang: 'id'
            });
            localStorage.setItem('systemLang', 'id');
        }
    }

    removeMsgAfterSomeTime(time) {
        setTimeout(() => {
            this.closeOverlayMsg();
        }, time * 1000);
    }

    animateToTop(e) {
        if (e) {
            e.preventDefault();
        }
        if (typeof window !== 'undefined') {
            let scrollToTop = window.setInterval(function () {
                let pos = window.pageYOffset;
                if (pos > 0) {
                    window.scrollTo(0, pos - 20);
                } else {
                    window.clearInterval(scrollToTop);
                }
            }, 16);
        }
    }

    closeOverlayMsg() {
        this.props.actions.setErrorMessage('');
        this.setState({successMessage: ''});
        this.setState({errorMessage: ''});
    }

    getErrorMsg() {
        if (this.state.errorMessage) {
            return (
                <OverlayMessage
                    overlayText={this.state.errorMessage}
                    handleOverlayClose={this.closeOverlayMsg.bind(this)}
                    colorClass="error"/>
            );
        } else if (this.state.successMessage) {
            return (
                <OverlayMessage
                    overlayText={this.state.successMessage}
                    handleOverlayClose={this.closeOverlayMsg.bind(this)}
                    colorClass="productOverlay"/>
            );
        }
    }

    setAlert(ltsAlert) {
        this.setState({ltsAlert});
    }

    getLoader() {
        if (this.props.appState.loader.showLoader) {
            return (
                <div className="loader">
                    <img className="loader-img" src={loaderImg}/>
                </div>
            );
        }
    }

    makeAppDirty(isDirty) {
        window.onbeforeunload = () => {
            return isDirty ? 1 : null;
        };
    }

    render() {
        return (
            <div style={{width: '100%', height: '100vh'}}>
                {this.getErrorMsg()}
                {this.getLoader()}
                <Header cssClassName={'navbar_container subheader_container'}
                        userName={this.state.userName}
                        systemLang ={this.state.systemLang}
                        setAlert= {this.setAlert.bind(this)}
                        localeFile= {this.state.localeFile}
                        handleLanguageChange = {this.handleLanguageChange.bind(this)}
                        {...this.props}/>

                {/*{this.state.userName && this.state.locationUrl !== '/register' ? <Sidebar/> : ''}*/}
                {this.state.userName ? <Sidebar/> : ''}

                {/*<Profile user={USER}*/}
                         {/*{...this.props}/>*/}

                <Routes systemLang ={this.state.systemLang}
                    setAlert= {this.setAlert.bind(this)}
                    user={USER}
                    isRegisterActive={this.state.isRegisterActive}
                    localeFile= {this.state.localeFile}
                    handleLanguageChange = {this.handleLanguageChange.bind(this)}
                    makeAppDirty = {this.makeAppDirty.bind(this)}
                    {...this.props}/>

                {this.state.ltsAlert}
                <Footer copyrightMsg={this.state.localeFile.copy_right_msg}/>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        appState: state
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(actions, dispatch)
    };
}

export default withRouter(connect(
    mapStateToProps, mapDispatchToProps
)(App));
