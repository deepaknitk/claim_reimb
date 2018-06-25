import React from 'react';
import './styles.scss';
import axios from 'axios';

let USER = JSON.parse(localStorage.getItem('user'))

class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isRegisterDone: false
        };
        this.submitChange = this.submitChange.bind(this);
    }

    submitChange() {
        let empId = document.getElementById('employeeId').value;
        let name = document.getElementById('employeeName').value;
        USER = JSON.parse(localStorage.getItem('user'))
        console.log('id', empId, 'name', name);
        axios({
            method: 'post',
            url: 'http://localhost:8080/claims/user/save',
            data: {
                userName: name,
                userEmail: USER.email,
                employeeId: empId
            }
        })
            .then(function (response) {
                console.log(response);
                let temp = {
                    name: name,
                    email: USER.email,
                    avatar_24: USER.avatar_24,
                    avatar_192: USER.avatar_192,
                    empID: empId
                };
                console.log(this.state.isRegisterDone);
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
                                            <input type="number" name="employeeId" id="employeeId" placeholder="eg. 2011232"/>
                                        </div>
                                        <div className="col-6 formField">
                                            <label htmlFor="employeeName">Name: </label>
                                            <input type="text" name="employeeName" id="employeeName" placeholder="eg. Babu Rao"/>
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
