import React from 'react';
import propTypes from 'prop-types';
import classNames from 'classnames';

const InputPrice = (props) => {
    return (
        <div className="form-group">
            <label className={props.cssClassName}>{props.title}<span className= "required"> *</span></label>
            <div className="input-group">
                <div className="input-group-prepend">
                    <div className="input-group-text">
                        <span>Rp</span>
                    </div>
                </div>
                <input className={classNames('form-control', {ltsError: !props.errorValidationState})}
                    name={props.name}
                    placeholder={props.placeholder}
                    type={props.inputType}
                    value={props.value}
                    onChange = {(event) => props.changeHandler(props.name, props.validationFun, event)}/>
            </div>
            {!props.errorValidationState && <span className="priceRequired error-msg">{props.errorMsg}</span>}

        </div>

    );
};

InputPrice.propTypes = {
    inputType: propTypes.string.isRequired,
    name: propTypes.string.isRequired,
    value: propTypes.string.isRequired,
    title: propTypes.string.isRequired,
    changeHandler: propTypes.func.isRequired
};
InputPrice.defaultProps = {
    inputType: 'number',
    name: '',
    value: '',
    title: '',
    changeHandler: () => {}
};

export default InputPrice;
