import React from 'react';
import './styles.scss';

const Register = () => {
    return (
        <div>
            <div className="container-fluid" style={{height: '100%'}}>
                <div className="row" style={{height: '100%'}}>
                    <div className="col justify-content-center center-all" style={{ marginTop: '20px' }}>
                        <div className="registrationForm">
                            <h3>Register Me</h3>
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
                                    <button className="btn">Register</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Register;
