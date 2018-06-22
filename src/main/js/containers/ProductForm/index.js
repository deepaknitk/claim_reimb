import React from 'react';
import Input from '../../components/Input/index';
import InputPrice from '../../components/InputPrice/index';
import UploadImage from '../../components/ImageInput/index';
import TextArea from '../../components/TextArea/index';



const ProductForm = (props) => {
    return(
        <div>
        <div className="row">
            <div className="col-md-12">
                <Input
                    cssClassName="input_span"
                    changeHandler={props.changeHandler}
                    name="productName"
                    placeholder={props.localeFile.product_name}
                    maxLength={255}
                    value={props.productName}
                    validationFun="isEmpty"
                    inputType="text"
                    title={props.localeFile.product_name}
                    errorValidationState={props.ProductErrorValidationState}
                     errorMsg={props.localeFile.err_product_name}
                    />
            </div>
        </div>
        <div className="row">
            <div className="col-md-5">
                <Input
                    cssClassName="input_span"
                    changeHandler={props.changeHandler}
                    name="quantity"
                    placeholder={props.localeFile.qty}
                    value={props.quantity}
                    validationFun="isValidQuantity"
                    maxLength={255}
                    inputType="number"
                    title={props.localeFile.qty}
                    errorValidationState={props.quantityErrorValidationState}
                    errorMsg={props.localeFile.err_qty}
                    />
            </div>
            <div className="col-md-7">
                <InputPrice
                    cssClassName="input_span"
                    changeHandler={props.changeHandler}
                    name="price"
                    placeholder={props.localeFile.price}
                    maxLength={255}
                    value={props.price}
                    validationFun="isValidFloat"
                    inputType="number"
                    title={props.localeFile.price}
                    errorValidationState={props.priceErrorValidationState}
                    errorMsg={props.localeFile.err_price}
                    />
            </div>
        </div>
        <div className="row">
            <div className="col-md-12">
                <UploadImage
                    cssClassName="input_span"
                    changeHandler={props.readURL}
                    inputType="file"
                    id="productImage"
                    fileName={props.fileName ? props.fileName : <span className="input_span">{props.localeFile.chooseFile}</span>}
                    imageUrl = {props.imageUrl}
                    title={props.localeFile.upload_img}
                    errorValidationState={props.invalidImageFile || props.isLargeFileToUpload}
                    errorMsg={ (props.invalidImageFile || props.isLargeFileToUpload) ? (props.invalidImageFile ? props.localeFile.invalidImageFileMultiline : props.localeFile.isLargeFileToUpload) : ''}
                    />
            </div>
        </div>
        <div className="row">
            <div className="col-md-12">
                <TextArea
                    cssClassName="input_span"
                    changeHandler={props.changeHandler}
                    name="productDiscription"
                    placeholder={props.localeFile.discription}
                    validationFun="isEmpty"
                    maxLength={255}
                    value={props.productDiscription}
                    title={props.localeFile.discription}
                    errorValidationState={props.discriptionErrorValidationState}
                    errorMsg={props.localeFile.err_discription}
                    />
            </div>
        </div>
    </div>
    );
};
export default ProductForm;
