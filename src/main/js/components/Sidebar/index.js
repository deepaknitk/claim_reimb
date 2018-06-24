import React from 'react';
import {NavLink} from 'react-router-dom';
import './styles.scss';


const Sidebar = () => {
    return (
        <div style={{width: '20%', float: 'left'}}>
            <div className="sidebar">
                <div className="sidebar-menu">
                    <NavLink to="/createClaim" activeStyle={{ color: 'red' }}>New Claims</NavLink>
                    <NavLink to="/dashboard" activeStyle={{ color: 'red' }}>Claim History</NavLink>
                    <NavLink to="/manageClaims" activeStyle={{ color: 'red' }}>Manage Claims</NavLink>
                </div>
            </div>
        </div>
    );
};

export default Sidebar;
