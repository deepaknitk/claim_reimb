import React from 'react';
import propTypes from 'prop-types';

const Radio = (props) =>{
             return(
                <div className="form-check form-check-inline">
                <label className="form-check-label">
                    <input type="radio"
                           className="form-check-input"
                           value={props.value}
                           name= {props.name}
                           checked={props.checked}
                           onChange={() => props.radioChangeHandler()}
                    />{localStorage.getItem('systemLang') === 'en' ? props.value.rfqTypeDescriptionEn : props.value.rfqTypeDescriptionId}
                </label>
            </div>
           );
};

Radio.propTypes = {
    inputType: propTypes.oneOf(['radio']).isRequired,
    value: propTypes.object.isRequired,
    title: propTypes.string,
    changeHandler: propTypes.func.isRequired
};
Radio.defaultProps = {
   inputType: 'radio',
   value: '',
   title: '',
   changeHandler: () => {}
  };

export default Radio;
