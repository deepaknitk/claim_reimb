import React from 'react';
import './styles.scss';

const Profile = ({user}) => {
    return (
        <div className="container-fluid profile">
            <div className="row">
                <div className="col-3">
                    <img src={user.avatar_192} alt=""/>
                </div>
                <div className="col-7 profile__details">
                    <div className="row">
                        <div className="col-3">
                            Name
                        </div>
                        <div className="col-9">
                            : {user.name}
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-3">
                            Email
                        </div>
                        <div className="col-9">
                            : {user.email}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Profile;
