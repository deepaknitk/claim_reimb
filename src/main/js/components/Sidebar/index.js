import React from 'react';
import './styles.scss';

const Sidebar = () => {
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-2 sidebar">
                    <div className="sidebar-menu">
                        <a href="/claims/views/rfq">New Claims</a>
                        <a href="/claims/views/">Claim History</a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Sidebar;
