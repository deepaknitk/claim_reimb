import React from 'react';
import Input from '../../components/Input/index';
import InputPrice from '../../components/InputPrice/index';
import UploadImage from '../../components/ImageInput/index';
import TextArea from '../../components/TextArea/index';
import SelectBox from '../../components/selectBox';
import {formatDate} from 'react-day-picker/moment';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import CurrencyType from '../../components/CurrencyType';


const ProductForm = (props) => {
    return (
        <div>
            <div className="row">
                <div className="col-md-12">
                    <UploadImage
                        cssClassName="input_span"
                        changeHandler={props.readURL}
                        inputType="file"
                        id="productImage"
                        fileName={props.fileName ? props.fileName :
                            <span className="input_span">{props.localeFile.chooseFile}</span>}
                        imageUrl={props.imageUrl}
                        title="Upload Bill"
                        errorValidationState={props.imageUrlErrorValidationState}
                        errorMsg= "Upload your bill"
                    />
                </div>
            </div>
            <div className="row">
                <div className="col-md-6">
                    <Input
                        cssClassName="input_span"
                        required={true}
                        changeHandler={props.changeHandler}
                        name="billNo"
                        placeholder="Bill NO"
                        maxLength={255}
                        value={props.billNo}
                        validationFun="isEmpty"
                        inputType="number"
                        title="Bill NO"
                        errorValidationState={props.billNoErrorValidationState}
                        errorMsg="Bill NO is required"
                    />
                </div>
                <div className="col-md-6">
                    <span>Date <span className="required">*</span></span>
                    <DayPickerInput
                        onDayChange={(day) => props.onDayChange('date', 'isEmpty', day)}
                        format="DD/MM/YYYY"
                        formatDate={formatDate}
                        placeholder="DD/MM/YYYY"
                        value={props.date}
                        inputProps={{
                            readOnly: 'readOnly',
                            id: 'date'
                        }}
                        dayPickerProps={{
                            selectedDays: props.selectedDay,
                            disabledDays: {
                                before: new Date()
                            }
                        }}
                    />
                    {!props.dateErrorValidationState ? <span className="date error-msg" style={{color: 'red'}}>Enter Bill Date</span> : ''}
                </div>
            </div>

            <div className="row">
                <div className="col-md-6">
                    <SelectBox
                        cssClassName="input_span"
                        changeHandler={props.changeHandler}
                        title="Type"
                        name="type"
                        placeholder="Select claim type"
                        validationFun="isEmpty"
                        value={props.type}
                        errorMsg="Select your claim type"
                        errorValidationState={props.typeErrorValidationState}
                        options={props.expenseType}/>
                </div>
                <div className="col-md-6">
                    <CurrencyType
                        cssClassName="input_span"
                        changeHandler={props.changeHandler}
                        title="Currency"
                        name="type"
                        placeholder="Select currency"
                        validationFun="isEmpty"
                        value={'INR'}
                        errorMsg="Select your claim type"
                        errorValidationState={props.typeErrorValidationState}
                        options={[{name: 'INR', value: 'INR'}, {name: 'Id', value: 'Id'}]}/>
                    <InputPrice
                        cssClassName="input_span"
                        changeHandler={props.changeHandler}
                        name="price"
                        placeholder="Amount"
                        maxLength={255}
                        value={props.price}
                        validationFun="isValidFloat"
                        inputType="number"
                        title="Amount"
                        errorValidationState={props.priceErrorValidationState}
                        errorMsg="Amount is required"
                    />
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                <TextArea
                    cssClassName="input_span"
                    required={true}
                    changeHandler={props.changeHandler}
                    name="claimDiscription"
                    placeholder="Claim Discriptions"
                    validationFun="isEmpty"
                    maxLength={255}
                    value={props.claimDiscription}
                    title="Claim Discription"
                    errorValidationState={props.discriptionErrorValidationState}
                    errorMsg="Discription is required"
                />
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                <TextArea
                    cssClassName="input_span"
                    changeHandler={props.changeHandler}
                    name="remarks"
                    placeholder="Remarks"
                    validationFun="isEmpty"
                    maxLength={255}
                    value={props.remarks}
                    title="Remarks"
                    errorValidationState={props.remarksErrorValidationState}
                    errorMsg="Remarks is required"
                />
                </div>
            </div>
        </div>
    );
};
export default ProductForm;
