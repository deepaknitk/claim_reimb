import React from 'react';
import './styles.scss';

let USER = localStorage.getItem('user');

const Header = (props) => {
    return (
        <div>
            <nav className={`navbar navbar-expand-lg ${props.cssClassName}`}>
                <a>
                    <h4 className="check"><span style={{fontSize: '30px', color: '#EE8609'}}>₹</span>eimbursementBuzz</h4>
                </a>
                {!USER ?
                    props.userName ?
                        <div>
                            <a href="/claims/views/profile"
                               className="user">{props.userName}</a>&nbsp;&nbsp;&nbsp;
                            <a href="/claims/views/" onClick={() => {
                                localStorage.removeItem('user');
                                USER = localStorage.getItem('user');
                            }} className="user-logout">Logout</a>
                        </div> : ''
                    :
                    <div>
                        <a href="/claims/views/profile"
                           className="user">{props.userName}</a>&nbsp;&nbsp;&nbsp;
                        <a href="/claims/views/" onClick={() => {
                            localStorage.removeItem('user');
                            USER = localStorage.getItem('user');
                        }} className="user-logout">Logout</a>
                    </div>
                }
            </nav>
        </div>
    );
};

export default Header;
