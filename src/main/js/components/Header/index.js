import React from 'react';
import './styles.scss';

const Header = (props) => {
    return (
        <div>
            <nav className={`navbar navbar-expand-lg ${props.cssClassName}`}>
                <a className="navbar-brand p-t-10 p-b-10">
                    <span>{props.localeFile.app_name}
                    </span>
                </a>
                <div className="pull-right language-container">
                    <span
                        className={props
                        .systemLang
                        .substr(0, 2) === 'en'
                        ? 'active'
                        : ''}
                        onClick={props
                        .handleLanguageChange
                        .bind(this, 'en')}>EN
                    </span>
                    <span className= "langulage-devider">|</span>
                    <span
                        className={props
                        .systemLang
                        .substr(0, 2) === 'id'
                        ? 'active'
                        : ''}
                        onClick={props
                        .handleLanguageChange
                        .bind(this, 'id')}>ID</span>

                        <span className= "logout icon-exit"></span>
                </div>
            </nav>
        </div>
    );
};
export default Header;
