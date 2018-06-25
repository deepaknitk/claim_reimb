import React from 'react';
import './styles.scss';
import axios from 'axios';

let USER = JSON.parse(localStorage.getItem('user'));

class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employeeId: '',
            employeeName: '',
            isRegisterDone: false
        };
        this.submitChange = this.submitChange.bind(this);
        this.changeHandler = this.changeHandler.bind(this);
    }


    changeHandler(event, fieldName) {
        this.setState({[fieldName]: event.target.value});
    }

    submitChange() {
        USER = JSON.parse(localStorage.getItem('user'));
        axios({
            method: 'post',
            url: 'http://localhost:8080/claims/user/save',
            data: {
                userName: this.state.employeeName,
                userEmail: USER.email,
                employeeId: this.state.employeeId
            }
        })
            .then(function (response) {
                let temp = {
                    name: name,
                    email: USER.email,
                    avatar_24: USER.avatar_24,
                    avatar_192: USER.avatar_192,
                    empID: this.state.employeeId
                };
                this.setState({isRegisterDone: response.data.success});
                localStorage.setItem('user', JSON.stringify(temp));
            }.bind(this));
    }

    componentDidMount() {
        if (this.props.user) {
            document.getElementById('employeeName').value = this.props.user.name;
        }
    }

    render() {
        return (
            <div>
                {this.props.isRegisterActive || this.state.isRegisterDone ?
                    this.props.history.push('/createClaim')
                    :
                    <div className="container-fluid" style={{height: '100%'}}>
                        <div className="row" style={{height: '100%'}}>
                            <div className="col justify-content-center center-all" style={{ marginTop: '20px' }}>
                                <div className="registrationForm">
                                    <h3>Register Me!</h3>
                                    <div className="fields">
                                        <div className="col-6 formField">
                                            <label htmlFor="employeeId">Employee ID: </label>
                                            <input type="number" name="employeeId" id="employeeId" placeholder="eg. 2011232" onChange={(event) => this.changeHandler(event, 'employeeId')}/>
                                        </div>
                                        <div className="col-6 formField">
                                            <label htmlFor="employeeName">Name: </label>
                                            <input type="text" name="employeeName" id="employeeName" placeholder="eg. Babu Rao" onChange={(event) => this.changeHandler(event, 'employeeName')}/>
                                        </div>
                                        <div className="center-all formField">
                                            <button onClick={this.submitChange} className="btn">Register</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                }
            </div>
        );
    }
}

export default Register;
