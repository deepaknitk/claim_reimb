import React from 'react';
import {Link} from 'react-router-dom';
import './style.scss';
import BAHASA from '../../locales/lts.id';
import ENGLISH from '../../locales/lts.en';

const SubmitSuccess = () => {
    return (
        <div>
            <div className="margin-lr40 p-t-20 p-b-20 p-l-30 p-r-30">
                <div className="sub_header_border_top"></div>
                <span className="subheader_span f-s-24 page-title">
                    {localStorage.getItem('systemLang') === 'en' ? ENGLISH.sub_header : BAHASA.sub_header}
                </span>
                <div className="current_page_status">
                    <span
                        className="sub_header_border_buttom">{localStorage.getItem('systemLang') === 'en' ? ENGLISH.home : BAHASA.home}  </span>
                            »
                    <span> {localStorage.getItem('systemLang') === 'en' ? ENGLISH.submission : BAHASA.submission} </span>
                            »
                    <span> {localStorage.getItem('systemLang') === 'en' ? ENGLISH.thanks : BAHASA.thanks}</span>
                </div>
            </div>

            <div className="success_form_layout m-t-15">
                <span
                    className="success_header">{localStorage.getItem('systemLang') === 'en' ? ENGLISH.thanks : BAHASA.thanks}</span>
                <div className="m-t-30 m-b-30 f-w-300">
                    {localStorage.getItem('systemLang') === 'en' ? ENGLISH.RFQsubmitSuccess : BAHASA.RFQsubmitSuccess}
                </div>
                <div className="back_to_home">
                    <Link to="/rfq" className="back_link m-r-15">
                        {localStorage.getItem('systemLang') === 'en' ? ENGLISH.submitAgain : BAHASA.submitAgain}
                    </Link>
                    <span className="m-r-20 m-r-20"> | </span>
                    <span className="back_link m-r-15">
                      {localStorage.getItem('systemLang') === 'en' ? ENGLISH.visitBlibli : BAHASA.visitBlibli}
                        <a href="https://www.blibli.com/">blibli.com</a>
                  </span>
                </div>
            </div>
        </div>
    );
};

export default SubmitSuccess;
