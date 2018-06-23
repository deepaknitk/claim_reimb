import React from 'react';
import './styles.scss';

const Header = (props) => {
    return (
        <div>
            <nav className={`navbar navbar-expand-lg ${props.cssClassName}`}>
                <a className="navbar-brand p-t-10 p-b-10">
                    <span>
                        Claim Reimbursement process
                    </span>
                </a>
            </nav>
        </div>
    );
};
export default Header;
