import React from 'react';
import propTypes from 'prop-types';
import classNames from 'classnames';

const TextArea = (props) => {
    return (
        <div className="form-group">
            <label className={props.cssClassName}>{props.title} {props.required ?
                <span className="required">*</span> : ''}</label>
            <textarea
                className={classNames('form-control', {ltsError: !props.errorValidationState})}
                name={props.name}
                placeholder={props.placeholder}
                maxLength={props.maxLength}
                value={props.value}
                onChange={(event) => props.changeHandler(props.name, props.validationFun, event)}/>
            <span className="error-msg">{props.errorMsg}</span>
        </div>
    );
};

TextArea.propTypes = {
    title: propTypes.string.isRequired,
    name: propTypes.string.isRequired,
    value: propTypes.string.isRequired,
    changeHandler: propTypes.func.isRequired,
    placeholder: propTypes.string,
    maxLength: propTypes.number
};

TextArea.defaultprops = {
    title: '',
    name: '',
    value: '',
    changeHandler: () => {
    },
    placeholder: '',
    maxLength: 256
};
export default TextArea;
