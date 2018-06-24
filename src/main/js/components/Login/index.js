import React from 'react';
import './styles.scss';

const LOGIN = ({property}) => {
    return (
        <div>
            {property.user === null ?
                <div className="container-fluid" style={{backgroundColor: '#456C95', height: '620px'}}>
                    <div className="row" style={{height: '100%'}}>
                        <div className="col justify-content-center align-self-center center-all">
                            <h1 className="check"><span className="heading">₹</span>eimbursementBuzz</h1>
                            <a href="https://slack.com/oauth/authorize?client_id=304468210898.305541541543&scope=identity.avatar,identity.email,identity.basic"><img src="https://api.slack.com/img/sign_in_with_slack.png" /></a>
                        </div>
                    </div>
                </div>
                : property.history.push('/createClaim')}
        </div>
    );
};

export default LOGIN;
