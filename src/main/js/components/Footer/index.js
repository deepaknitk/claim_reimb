import React from 'react';
import blibliLogo from '../../styles/fonts/logo-blibli.png';
import './styles.scss';

const Footer = (props) => {
    return (
        <div
            className="footer_container">
            <span className = "footer_copyright p-t-15 p-b-15" >Copyright © 2018 Digital Products Team
                {/*<span className="heart_icon" >♥</span>*/}
                </span>
        </div>
    );
};

export default Footer;
