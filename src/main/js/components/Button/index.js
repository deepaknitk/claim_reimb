import React from 'react';
import PropTypes from 'prop-types';
import './styles.scss';
import classNames from 'classnames';

const Button = (props) => {
        return (
            <button
                className= {classNames('btn btn-default', props.cssClassName)}
                onClick={props.buttonClickFunc}
                type={props.type}
                disabled={props.isDisabled}>
                {props.buttonName}
                {props.icon}
            </button>
        );
};

Button.defaultProps = {
    buttonName: 'OK',
    type: 'submit',
    className: 'btn btn-default',
    isDisabled: false,
    buttonClickFunc: function () {}
};
Button.propTypes = {
    buttonName: PropTypes.oneOfType([
                    PropTypes.string,
                    PropTypes.object
                ]),
    type: PropTypes.string.isRequired,
    className: PropTypes.string,
    isDisabled: PropTypes.bool,
    buttonClickFunc: PropTypes.func
};
export default Button;
