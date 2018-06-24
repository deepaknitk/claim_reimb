import React from 'react';
import './styles.scss';

const Header = (props) => {
    return (
        <div>
            <nav className={`navbar navbar-expand-lg ${props.cssClassName}`}>
                <a>
                    <h4 className="check"><span style={{fontSize: '30px', color: '#EE8609'}}>₹</span>eimbursementBuzz</h4>
                </a>
                {props.userName}
            </nav>
        </div>
    );
};
export default Header;
