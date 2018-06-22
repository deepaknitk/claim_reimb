import React from 'react';
import PropTypes from 'prop-types';
import './styles.scss';

const Modal = (props) => {
        return(
            <div className="modal-overlay">
            <div className={'modal_wrapper ' + props.extraClass}>
                <div className="modal_header">
                    <span>{props.headerText}</span>
                    <span onClick={props.closeModal} className="icon-close"></span>
                </div>
                <div className="modal_body">
                    {props.bodyContent}
                </div>
                <div className="modal_footer">
                    {props.footerContent}
                </div>
            </div>
            </div>
        );
};


Modal.defaultProps = {
    closeModal: ()=>{},
    extraClass: '',
    headerText: ''
};

Modal.propTypes = {
    closeModal: PropTypes.func,
    extraClass: PropTypes.string,
    headerText: PropTypes.string
};

export default Modal;
