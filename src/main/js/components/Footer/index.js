import React from 'react';
import blibliLogo from '../../styles/fonts/logo-blibli.png';
import './styles.scss';

const Footer = (props) => {
    return (
        <div
            className="footer_container">
            <span className = "footer_copyright p-t-15 p-b-15" >{props.copyrightMsg}
                <span className="heart_icon" >♥</span>
                by</span>
            <span>
                <img className= "footer-icon" alt="Logo Not Found"src={blibliLogo}/></span>
        </div>
    );
};

export default Footer;
