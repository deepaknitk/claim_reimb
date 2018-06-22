import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import { withRouter } from 'react-router';
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
            multilineImageUploadProgress: 0
        };
    }
    componentWillMount() {
        if (localStorage.getItem('systemLang')) {
            this.setState({systemLang: localStorage.getItem('systemLang')});
        }
        else {
            //setting default lang to indonessian(id)
            localStorage.setItem('systemLang', 'id');
        }
        this.handleLanguageChange(localStorage.getItem('systemLang'));
    }
    componentWillReceiveProps(nextProps) {
        if(!isEqual(nextProps.appState.message.successMessage, this.state.successMessage) &&
            nextProps.appState.message.successMessage) {
            this.setState({successMessage: nextProps.appState.message.successMessage});
            this.removeMsgAfterSomeTime(2);
        }
        if(!isEqual(nextProps.appState.message.errorMessage, this.state.errorMessage) &&
            nextProps.appState.message.errorMessage) {
            this.setState({errorMessage: nextProps.appState.message.errorMessage});
            this.removeMsgAfterSomeTime(5);
            this.animateToTop();
        }
        if(!isEqual(nextProps.appState.app.uiConfigs, this.state.uiConfigs) &&
            nextProps.appState.app.uiConfigs) {
            this.setState({uiConfigs: nextProps.appState.app.uiConfigs});
        }
    }
    handleLanguageChange(lang) {
        if (lang === 'en') {
            this.setState({localeFile: ENGLISH_LOCALE,
                            systemLang: 'en'});
            localStorage.setItem('systemLang', 'en');
        }
        else {
            this.setState({localeFile: BAHASA_LOCALE,
                            systemLang: 'id'});
            localStorage.setItem('systemLang', 'id');
        }
    }

    removeMsgAfterSomeTime(time) {
        setTimeout(() => {
            this.closeOverlayMsg();
        }, time * 1000);
    }
    animateToTop(e) {
        if(e) {
            e.preventDefault();
        }
        if(typeof window !== 'undefined') {
            let scrollToTop = window.setInterval(function() {
                let pos = window.pageYOffset;
                if ( pos > 0 ) {
                    window.scrollTo( 0, pos - 20 );
                } else {
                    window.clearInterval( scrollToTop );
                }
            }, 16);
        }
    }
    closeOverlayMsg() {
        this.props.actions.setErrorMessage('');
        this.setState({ successMessage: ''});
        this.setState({ errorMessage: ''});
    }
    getErrorMsg() {
        if(this.state.errorMessage) {
            return (
                <OverlayMessage
                    overlayText={this.state.errorMessage}
                    handleOverlayClose={this.closeOverlayMsg.bind(this)}
                    colorClass="error"/>
            );
        } else if(this.state.successMessage) {
            return (
                <OverlayMessage
                    overlayText={this.state.successMessage}
                    handleOverlayClose={this.closeOverlayMsg.bind(this)}
                    colorClass="productOverlay"/>
            );
        }
    }
    setAlert(ltsAlert) {
        this.setState({ ltsAlert });
    }
    getLoader() {
        if(this.props.appState.loader.showLoader) {
            return (
                <div className="loader">
                    <img className="loader-img" src={ loaderImg } />
                </div>
            );
        }
    }
    makeAppDirty(isDirty) {
        window.onbeforeunload = () => {return isDirty ? 1 : null;};
    }
    render() {
        return (
            <div>
                {this.getErrorMsg()}
                {this.getLoader()}
                <Header cssClassName={'navbar_container subheader_container'}
                        systemLang ={this.state.systemLang}
                        setAlert= {this.setAlert.bind(this)}
                        localeFile= {this.state.localeFile}
                        handleLanguageChange = {this.handleLanguageChange.bind(this)}
                        {...this.props}/>

                <Routes systemLang ={this.state.systemLang}
                    setAlert= {this.setAlert.bind(this)}
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
