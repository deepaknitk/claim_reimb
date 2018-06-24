import React from 'react';
import propTypes from 'prop-types';
import classNames from 'classnames';

const CurrenctType = (props) => {
    return (
        <div style = {{width: '70px', float: 'left'}}>
            <label htmlFor="select" className={props.cssClassName}>{props.title}</label>
            <select
                style={{width: '70px'}}
                className={classNames('form-control', {ltsError: !props.errorValidationState})}
                value={props.value}
                name={props.name}
                onChange={(event) => props.changeHandler(props.name, props.validationFun, event)}>
                {props.options.map(opt => {
                    return (
                        <option key={opt.name} value={opt.value}>{opt.name}</option>
                    );
                })
                }
            </select>
            <span className="error-msg">{props.errorMsg}</span>
        </div>
    );
};

CurrenctType.propTypes = {
    title: propTypes.string.isRequired,
    value: propTypes.string,
    name: propTypes.string.isRequired,
    changeHandler: propTypes.func.isRequired,
    options: propTypes.array.isRequired
};

CurrenctType.defaultprops = {
    title: propTypes.string.isRequired,
    value: '',
    name: '',
    changeHandler: () => {
    },
    options: []
};

export default CurrenctType;
