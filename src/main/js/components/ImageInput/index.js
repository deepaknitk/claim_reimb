import React from 'react';
import propTypes from 'prop-types';
import classNames from 'classnames';

const ImageInput = (props) => {
    return (
        <div className="form-group" className={props.cssClassName}>
            <label className={props.cssClassName}>{props.title} <span className="required">*</span> </label>
            <div className="custom-file">
                <input type="file" multiple="false"
                       className={classNames('custom-file-input', {ltsError: !props.errorValidationState})}
                       id={props.id}
                       onChange={(e) => props.changeHandler(e)}/>
                <label className="custom-file-label browsefile" htmlFor="id">
                    {props.fileName}
                </label>
                {!props.errorValidationState ? <span className="file_format error-msg">{props.errorMsg}</span> : ''}
            </div>
        </div>
    );
};

ImageInput.propTypes = {
    inputType: propTypes.string.isRequired,
    name: propTypes.string.isRequired,
    id: propTypes.string,
    value: propTypes.string.isRequired,
    title: propTypes.string.isRequired,
    changeHandler: propTypes.func.isRequired
};
ImageInput.defaultProps = {
    inputType: 'file',
    name: '',
    id: '',
    value: '',
    title: '',
    changeHandler: () => {
    }
};

export default ImageInput;
