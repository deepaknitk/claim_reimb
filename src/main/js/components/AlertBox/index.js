import React from 'react';
import Button from '../Button';
import BAHASA from '../../locales/lts.id';
import ENGLISH from '../../locales/lts.en';

const ConfirmationBox = (props) => {
    return(
        <div className="modal-overlay">
            <div className={'modal_wrapper border_radius ' + props.extraClass}>
                <div className="modal_header">
                    <span>{props.headerText}</span>
                    <span onClick={props.confirmationCancel} className="icon-close"></span>
                </div>
                <div>
                    <div className="flex align_center m-t-15 m-b-5">
                        <span className="icon-warning f-s-60 m-r-10 m-b-0 p-0 alert"></span>
                        <div className="f-s-14 text_last_center">
                            {props.message}
                        </div>
                    </div>
                    <div
                        className="center-all">
                        <Button
                            type="submit"
                            cssClassName="m-10 disable_btn btn_alert btn-secondary"
                            buttonClickFunc={props.confirmationCancel}
                            buttonName={localStorage.getItem('systemLang') === 'en' ? ENGLISH.no : BAHASA.no}
                        />
                        <Button
                            type="submit"
                            cssClassName="m-10 submit_btn btn_alert btn-success"
                            buttonClickFunc={props.confirmationSuccess}
                            buttonName= {localStorage.getItem('systemLang') === 'en' ? ENGLISH.yes : BAHASA.yes}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};



export default ConfirmationBox;
