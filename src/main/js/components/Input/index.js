import React from 'react';
import propTypes from 'prop-types';
import classNames from 'classnames';

const Input = (props) => {
    return (
        <div className="form-group">
            <label className={props.cssClassName}>{props.title} {props.required ?
                <span className="required">*</span> : ''}</label>
            <input className={classNames('form-control', {ltsError: !props.errorValidationState})}
                   name={props.name}
                   placeholder={props.placeholder}
                   type={props.inputType}
                   value={props.value}
                   maxLength={props.maxLength}
                   onChange={(event) => props.changeHandler(props.name, props.validationFun, event)}/>
            <span className="error-msg">{props.errorMsg}</span>
        </div>
    );
};

Input.propTypes = {
    inputType: propTypes.oneOf(['text', 'number']).isRequired,
    name: propTypes.string.isRequired,
    value: propTypes.string.isRequired,
    title: propTypes.string.isRequired,
    changeHandler: propTypes.func.isRequired,
    placeholder: propTypes.string,
    maxLength: propTypes.number
};
Input.defaultProps = {
    inputType: 'text',
    name: '',
    value: '',
    title: '',
    changeHandler: () => {
    },
    placeholder: '',
    maxLength: 256
};

export default Input;
