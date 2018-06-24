import React from 'react';
import propTypes from 'prop-types';
import classNames from 'classnames';

const PaymentSelectBox = (props) => {
    return (
        <div className="form-group">
            <label htmlFor="select" className={props.cssClassName}>{props.title}<span className= "required">*</span></label>
            <select
                className= {classNames('form-control', {ltsError: !props.errorValidationState})}
                value={props.value}
                name={props.name}
                onChange={(event) => props.changeHandler(props.name, props.validationFun, event)}>
                 <option disabled value="">{props.placeholder}</option>
                {props.options.map(opt => {
                        return (
                            <option key={opt.expenseTypeCode} value={opt.expenseTypeCode}>{opt.expenseTypeCode}</option>
                        );
                    })
                }
            </select>
            {props.errorValidationState ? <span className="error-msg">{props.errorMsg}</span> : ''}
        </div>
    );
};

PaymentSelectBox.propTypes = {
    title: propTypes.string.isRequired,
    value: propTypes.string,
    name: propTypes.string.isRequired,
    changeHandler: propTypes.func.isRequired,
    options: propTypes.array.isRequired
};

PaymentSelectBox.defaultprops = {
 title: propTypes.string.isRequired,
 value: '',
 name: '',
 changeHandler: () => {},
 options: []
};

export default PaymentSelectBox;
