import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';
import {formatDate} from 'react-day-picker/moment';
import {bindActionCreators} from 'redux';
import isEqual from 'lodash.isequal';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import classNames from 'classnames';
import * as actions from '../../actions/index';
import Validator from '../../utils/validator.js';
import '../../styles/day-picker.scss';
import Button from '../../components/Button';
import Input from '../../components/Input';
import BusinessSelectBox from '../../components/BusinessTypeSelectBox';
import TextArea from '../../components/TextArea';
import TableLayout from '../../components/Table';
import '../../styles/RFQForm.scss';
import Modal from '../../components/Modal';
import ProductForm from '../ProductForm';
import UploadFile from '../../components/ImageInput';
import Radio from '../../components/Radio/index.js';
import ProgressBar from '../../components/ProgressBar/index.js';
import ConfirmationBox from '../../components/AlertBox';
import RfqHeader from './RfqHeader';


class RFQForm extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            dealerName: '',
            isValidDealerName: true,
            dealerEmail: '',
            isValidDealerEmail: true,
            companyName: '',
            isValidCompanyName: true,
            companyAddress: '',
            isValidCompanyAddress: true,
            phoneNumber: '',
            isValidPhoneNumber: true,
            businessType: '',
            isValidBusinessType: true,
            industrySector: [],
            paymentMethod: '',
            isValidPaymentMethod: true,
            shippingAddress: '',
            isValidShippingAddress: true,
            receiptDate: '',
            isValidReceiptDate: true,
            paymentMethods: [],
            productName: '',
            isValidProductName: true,
            quantity: '',
            isValidQuantity: true,
            price: '',
            isValidPrice: true,
            imageUrl: null,
            isValidImageUrl: true,
            productDiscription: '',
            isValidProductDiscription: true,
            products: [],
            formValidationFlag: false,
            showpopup: false,
            showAddProductpopUp: false,
            showProductImagePopUp: false,
            showEditProductPopUp: false,
            showDeleteProductPopUp: false,
            checkedIndex: 0,
            isProductAdded: false,
            selectedProduct: {},
            selectedProductIndex: null,
            areYouSure: false,
            isProductTableValid: true,
            fileName: null,
            fileToUpload: null,
            isLargeFileToUpload: false,
            invalidImageFile: false,
            rfqTypeList: [],
            selectedRfqType: '',
            isBulkRfqFileUploadValid: true,
            fileUploadStatus: {},
            confirmationBox: false,
            singleSelect: false,
            multipleSelect: false,
            switchRfqType: false,
            switchRfqTypeTo: null
        };
        this.changeHandler = this.changeHandler.bind(this);
        this.onDayChange = this.onDayChange.bind(this);
        this.validateformSubmission = this.validateformSubmission.bind(this);
        this.clearRfqForm = this.clearRfqForm.bind(this);
        this.getTableHeader = this.getTableHeader.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
        this.addProductHandler = this.addProductHandler.bind(this);
        this.deleteProductHandler = this.deleteProductHandler.bind(this);
        this.getRFQDetails = this.getRFQDetails.bind(this);
        this.deleteMultipalProductHandler = this.deleteMultipalProductHandler.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
        this.clearAddProductForm = this.clearAddProductForm.bind(this);
        this.validateSaveProductForm = this.validateSaveProductForm.bind(this);
        this.readURL = this.readURL.bind(this);
        this.showProductImage = this.showProductImage.bind(this);
        this.editProductHandler = this.editProductHandler.bind(this);
        this.isDeleteGranted = this.isDeleteGranted.bind(this);
        this.getRfqProducts = this.getRfqProducts.bind(this);
        this.radioChangeHandler = this.radioChangeHandler.bind(this);
        this.getProductImageCount = this.getProductImageCount.bind(this);
        this.getDefaultPayment = this.getDefaultPayment.bind(this);
    }

    componentDidMount() {
        this.props.actions.getMasterDetails();
        this.props.actions.resetSaveRfqDetails();
        this.props.actions.resetFileUpload();
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.uiConfigs && nextProps.uiConfigs !== this.props.uiConfigs) {
            this.setState({
                industrySector: nextProps.uiConfigs.data.businessTypeList,
                paymentMethods: nextProps.uiConfigs.data.paymentMethodList,
                rfqTypeList: nextProps.uiConfigs.data.rfqTypeList,
                selectedRfqType: nextProps.uiConfigs.data.rfqTypeList[0]
            });
        }
        if (nextProps.RfqState.submitRfqResponse && !isEqual(this.props.RfqState.submitRfqResponse, nextProps.RfqState.submitRfqResponse)) {
            const products = nextProps.RfqState.submitRfqResponse.data.rfqItemList;
            if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_MULTILINE') {
                const addedProducts = this.state.products;
                products.map((product, index) => {
                    if (addedProducts[index].imageUrl !== null) {
                        const uploadFile = {
                            rfqId: nextProps.RfqState.submitRfqResponse.data.rfqId,
                            rfqItemId: product.rfqItemId,
                            rfqTypeId: this.state.selectedRfqType.rfqTypeId,
                            fileToUpload: addedProducts[index].fileToUpload,
                            fileName: addedProducts[index].fileName,
                            itemId: product.itemId,
                            index: index
                        };
                        this.props.actions.saveImageFile(uploadFile);
                    }
                });
            }
            else {
                const uploadFile = {
                    rfqId: nextProps.RfqState.submitRfqResponse.data.rfqId,
                    rfqItemId: products[0].rfqItemId,
                    rfqTypeId: this.state.selectedRfqType.rfqTypeId,
                    fileToUpload: this.state.fileToUpload,
                    fileName: this.state.fileName,
                    itemId: products[0].itemId,
                    index: 0
                };
                this.props.actions.saveImageFile(uploadFile);
            }
        }
        if (nextProps.RfqState.submitRfqResponse && this.props.RfqState.submitRfqResponse !== nextProps.RfqState.submitRfqResponse && this.state.selectedRfqType.rfqTypeCode === 'RFQ_MULTILINE') {
            if (this.getProductImageCount() === 0) {
                if (this.state.formValidationFlag) {
                    this.props.history.push('submitSuccess');
                }
            }
        }
        if (nextProps.fileUpload && nextProps.fileUpload !== this.props.fileUpload) {
            this.setState({fileUploadStatus: nextProps.fileUpload}, () => {
                if (Object.keys(this.state.fileUploadStatus).length) {
                    if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_MULTILINE') {
                        let fileApiSuccesscount = 0;
                        for (let key in this.state.fileUploadStatus) {
                            if (this.state.fileUploadStatus[key].successStatus !== 'inProgress' && this.state.fileUploadStatus[key].successStatus || !this.state.fileUploadStatus[key].successStatus) {
                                fileApiSuccesscount++;
                            }
                        }
                        let imageCount = this.getProductImageCount();
                        if (fileApiSuccesscount === imageCount) {
                            this.props.history.push('submitSuccess');
                        }
                    }
                    if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_BULK') {
                        if (this.state.fileUploadStatus[0].successStatus !== 'inProgress' && this.state.fileUploadStatus[0].successStatus) {
                            this.props.history.push('submitSuccess');
                        }
                    }
                }
            });
        }
    }

    getProductImageCount() {
        let imageCount = 0;
        this.state.products.map(product => {
            if (product.imageUrl !== null) {
                imageCount++;
            }
        });
        return imageCount;
    }

    changeHandler(fieldName, validationFuncName, event) {
        this.setState({[fieldName]: event.target.value});
        this.validateField(fieldName, validationFuncName, event.target.value);
    }

    onDayChange(fieldName, validationFuncName, day) {
        this.setState({receiptDate: day});
        this.validateField(fieldName, validationFuncName, day);
    }

    validateField(field, validationFuncName, value) {
        let isValid = Validator[validationFuncName](value);
        let validationState = 'isValid' + field
            .charAt(0)
            .toUpperCase() + field.slice(1);
        this.setState({[validationState]: isValid});
        return isValid;
    }

    validateformSubmission() {
        let allRequiredFeilds = [{name: 'dealerName', validFun: 'isEmpty'},
            {name: 'dealerEmail', validFun: 'isValidEmailId'},
            {name: 'companyName', validFun: 'isEmpty'},
            {name: 'companyAddress', validFun: 'isEmpty'},
            {name: 'phoneNumber', validFun: 'isValidMobileNumber'},
            {name: 'businessType', validFun: 'isEmpty'},
            {name: 'shippingAddress', validFun: 'isEmpty'}
        ];
        let isFormValidationFlag = true;
        allRequiredFeilds.map(eachField => {
            const currentFieldStatus = this.validateField(eachField.name, eachField.validFun, this.state[eachField.name].trim());
            if (!currentFieldStatus) {
                this.setState({formValidationFlag: false});
                isFormValidationFlag = false;
            }
            if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_MULTILINE') {
                if (!this.state.products.length) {
                    this.setState({isProductTableValid: false});
                    isFormValidationFlag = false;
                }
            }
            if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_BULK') {
                if (!this.state.fileName) {
                    this.setState({isBulkRfqFileUploadValid: false});
                    isFormValidationFlag = false;
                }
            }
        });
        const currentFieldStatus = this.validateField('receiptDate', 'isValidDate', this.state.receiptDate);
        if (!currentFieldStatus) {
            this.setState({formValidationFlag: false});
            isFormValidationFlag = false;
        }
        if (isFormValidationFlag) {
            this.setState({formValidationFlag: true}, () => {
                this.props.actions.sendRfqDetails(this.getRFQDetails());
            });
        }
    }

    validateSaveProductForm() {
        let allRequiredFeilds =
            [
                {name: 'productName', validFunName: 'isEmpty'},
                {name: 'quantity', validFunName: 'isValidQuantity'},
                {name: 'price', validFunName: 'isValidFloat'},
                {name: 'productDiscription', validFunName: 'isEmpty'}];
        let isSaveProductFormValid = true;
        allRequiredFeilds.map(eachfeild => {
            const currentFieldStatus = this.validateField(eachfeild.name, eachfeild.validFunName, this.state[eachfeild.name].trim());
            if (!currentFieldStatus) {
                isSaveProductFormValid = false;
            }
        });
        if (isSaveProductFormValid) {
            this.saveProduct(this.state.showEditProductPopUp);
        }
    }

    getTableHeader() {
        return (
            <tr className="table_layout table_header">
                <th></th>
                <th>{this.props.localeFile.serial_no}</th>
                <th>{this.props.localeFile.product_name}</th>
                <th>{this.props.localeFile.discription}</th>
                <th>{this.props.localeFile.qty}</th>
                <th>{this.props.localeFile.price}</th>
                <th>{this.props.localeFile.photo}</th>
                <th></th>
                <th></th>
            </tr>
        );
    }

    getTableBody() {
        const fileStatus = this.state.fileUploadStatus;
        return (
            <tbody className="table_body">
            {this.state.products.map((product, i) => <tr key={i}
                                                         className={product.isCheckedIndex ? 'checkedRowColor' : ''}>
                <td><input type="checkbox" name={i} id={i}
                           checked={product.isCheckedIndex ? true : false} onClick={() => this.handleCheckBox(i)}/>
                </td>
                <td>{i + 1}</td>
                <td>{product.productName}</td>
                <td>{product.productDiscription}</td>
                <td>{product.quantity}</td>
                <td>{product.price}</td>
                <td className="product_image pointer"
                    onClick={() => this.showProductImage(product)}>{this.props.localeFile.view_product}</td>
                <td
                    className="icon-mode_edit icon_clickable table_icons icon_edit"
                    onClick={() => this.editProductHandler(product, i)}></td>
                <td
                    className="icon-delete icon_clickable table_icons icon_delete"
                    onClick={() => this.deleteProductHandler(i)}></td>
                <td>
                    {this.state.fileUploadStatus && product.imageUrl && fileStatus[i] ?
                        <ProgressBar
                            successStatus={(fileStatus[i].successStatus === 'inProgress') ? 'bg-success' : fileStatus[i].successStatus === true ? 'bg-success' : 'bg-danger'}
                            fileStatus={fileStatus[i]}/>
                        : ''}
                </td>
            </tr>)}
            </tbody>
        );
    }

    showProductImage(selectedProduct) {
        this.setState({showpopup: true, showProductImagePopUp: true, selectedProduct: selectedProduct});
    }

    getImageBody() {
        const selectedProduct = this.state.selectedProduct;
        if (selectedProduct.imageUrl !== undefined && selectedProduct.imageUrl !== null) {
            return (<img className="product_preview" src={selectedProduct.imageUrl}
                         width="500"
            />);
        } else {
            return (
                <div className="no-image-available input_span">
                    <span>{this.props.localeFile.noImageAvailable}</span>
                </div>
            );
        }
    }

    addProductHandler() {
        this.setState({showpopup: true, showAddProductpopUp: true});
    }

    saveProduct(isUpdatingExistingProduct) {
        if (!isUpdatingExistingProduct && this.state.products.length === this.props.uiConfigs.data.maximumLineItemsAllowed) {
            this.clearAddProductForm();
            this.setState({showpopup: false, showAddProductpopUp: false});
            this.props.actions.setErrorMessage(this.props.localeFile.maxItemLimit);
        }
        else {
            const product = {
                productName: this.state.productName,
                productDiscription: this.state.productDiscription,
                quantity: this.state.quantity,
                price: this.state.price,
                imageUrl: this.state.imageUrl,
                fileName: this.state.fileName,
                fileToUpload: this.state.fileToUpload,
                isCheckedIndex: false
            };
            let tempProducts = this.state.products;
            if (!isUpdatingExistingProduct) {
                tempProducts.push(product);
                this.setState({products: tempProducts});
                this.props.actions.setSuccessMessage(this.props.localeFile.productAdded);
            } else {
                const selectedProductIndex = this.state.selectedProductIndex;
                const tempProducts = this.state.products;
                tempProducts[selectedProductIndex] = product;
                this.setState({
                    products: tempProducts,
                    showEditProductPopUp: false,
                    isUpdatingExistingProduct: false,
                    selectedProductIndex: null,
                    showpopup: false
                });
            }
            this.clearAddProductForm();
        }
    }

    confirmationSuccessforSingleItem() {
        this.deleteProduct();
    }

    confirmationSuccessforMultipleItem() {
        this.isDeleteGranted();
    }

    rfqTypeSwitchAlert(rfqType) {
        if (this.state.products.length || this.state.imageUrl) {
            this.setState({switchRfqTypeTo: rfqType});
            this.setState({confirmationBox: true, switchRfqType: true});
        }
        else {
            this.radioChangeHandler(rfqType);
        }
    }

    confirmationSuccessforSwitchRfqType() {
        this.radioChangeHandler(this.state.switchRfqTypeTo);
    }

    confirmationCancel() {
        const tempProduct = this.state.products;
        tempProduct.map(product => {
            product.isCheckedIndex = false;
        });
        this.setState({
            confirmationBox: false,
            singleSelect: false,
            switchRfqType: false,
            multipleSelect: false,
            products: tempProduct,
            checkedIndex: 0
        });
    }

    deleteMultipalProductHandler() {
        this.setState({confirmationBox: true, multipleSelect: true});
    }

    isDeleteGranted() {
        let remProducts = this.state.products.filter(product => product.isCheckedIndex !== true);
        this.setState({showpopup: false, showDeleteProductPopUp: false, products: remProducts, checkedIndex: 0});
        this.setState({confirmationBox: false, multipleSelect: false});
    }

    deleteProductHandler(index) {
        this.setState({confirmationBox: true, selectedProductIndex: index, singleSelect: true});
    }

    deleteProduct() {
        let addedProducts = this.state.products;
        addedProducts.splice(this.state.selectedProductIndex, 1);
        this.setState({products: addedProducts});
        this.setState({selectedProductIndex: null, confirmationBox: false, singleSelect: false});
    }

    getRFQDetails() {
        const RfqPayLoad = {
            'rfqTypeId': this.state.selectedRfqType.rfqTypeId,
            'paymentMethodId': this.getDefaultPayment(),
            'rfqDeliveryDate': this.state.receiptDate,
            'rfqDeliveryAddress': this.state.shippingAddress,
            'dealer': {
                'dealerName': this.state.dealerName,
                'dealerEmail': this.state.dealerEmail.trim(),
                'dealerPhone': this.state.phoneNumber,
                'dealerCompanyName': this.state.companyName,
                'dealerCompanyAddress': this.state.companyAddress,
                'businessTypeCode': this.state.businessType
            },
            'rfqItemList': this.getRfqProducts()
        };
        return RfqPayLoad;
    }

    getDefaultPayment() {
        let defaultPaymentMethod = null;
        const paymentTypeList = this.state.paymentMethods;
        paymentTypeList.map(paymentMethod => {
            if (paymentMethod.paymentMethodCode === 'DEFAULT') {
                defaultPaymentMethod = paymentMethod.paymentMethodId;
            }
        });
        return defaultPaymentMethod;
    }

    getRfqProducts() {
        const rfqProduct = [];
        this.state.products.map(product => {
            const lineItem = {
                'rfqItemName': product.productName,
                'rfqItemDescription': product.productDiscription,
                'rfqItemQuantity': product.quantity,
                'rfqItemPrice': product.price,
                'rfqItemFileName': product.fileName
            };
            rfqProduct.push(lineItem);
        });
        return rfqProduct;
    }

    handleCheckBox(index) {
        let tempProducts = this.state.products;
        if (document.getElementById(index).checked) {
            tempProducts[index].isCheckedIndex = true;
            let checkedIndex = this.state.checkedIndex;
            this.setState({checkedIndex: checkedIndex + 1});
            this.setState({products: tempProducts});
        }
        if (!document.getElementById(index).checked) {
            tempProducts[index].isCheckedIndex = false;
            let checkedIndex = this.state.checkedIndex;
            this.setState({checkedIndex: checkedIndex - 1});
            this.setState({products: tempProducts});
        }
    }

    clearRfqForm() {
        if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_BULK') {
            document.getElementById('customFile').value = '';
        }
        let allRequiredFeilds = [
            'dealerName',
            'dealerEmail',
            'companyName',
            'companyAddress',
            'phoneNumber',
            'businessType',
            'paymentMethod',
            'shippingAddress',
            'receiptDate'
        ];
        allRequiredFeilds.map(field => {
            let validationState = 'isValid' + field.charAt(0).toUpperCase() + field.slice(1);
            this.setState({[field]: '', [validationState]: true});
        });
        this.setState({
            products: [], isProductTableValid: true, fileToUpload: null,
            fileName: null, imageUrl: null, invalidImageFile: false, isBulkRfqFileUploadValid: true
        });
    }

    popupFooterContent() {
        return (
            <div className="addProductFooter">
                <Button
                    type="submit"
                    cssClassName="m-r-10 disable_btn product_form_btn btn-secondary"
                    buttonClickFunc={() => this.clearAddProductForm()}
                    buttonName={!this.state.showEditProductPopUp ? this.props.localeFile.clean_up : this.props.localeFile.cancel}
                    isDisabled={false}/>
                <Button
                    type="submit"
                    cssClassName="m-r-10 submit_btn product_form_btn btn-success"
                    buttonClickFunc={this.validateSaveProductForm}
                    buttonName={!this.state.showEditProductPopUp ? this.props.localeFile.add : this.props.localeFile.save}
                    isDisabled={false}/>
            </div>
        );
    }

    clearAddProductForm(closeModal) {
        if (!closeModal) {
            if (this.state.selectedRfqType.rfqTypeCode === 'RFQ_MULTILINE') {
                document.getElementById('productImage').value = '';
            }
        }
        let productFormField = ['productName', 'quantity', 'price', 'imageUrl', 'productDiscription'];
        productFormField.map(productField => {
            let validationState = 'isValid' + productField.charAt(0).toUpperCase() + productField.slice(1);
            this.setState({[productField]: '', [validationState]: true});
        });
        this.setState({
            fileName: null,
            imageUrl: null,
            fileToUpload: null,
            invalidImageFile: false,
            isLargeFileToUpload: false
        });
    }

    readURL(e) {
        this.setState({
            isLargeFileToUpload: false,
            isBulkRfqFileUploadValid: true,
            invalidImageFile: false,
            fileToUpload: null,
            imageUrl: null,
            fileName: null
        });
        let fileExt = e.target.value;
        fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
        let imagePattern = this.state.selectedRfqType.rfqTypeAllowedFileTypes;
        let isMatched = fileExt.toLowerCase().match(imagePattern);
        if (!isMatched) {
            this.setState({invalidImageFile: true});
        }
        else if (e.target.files[0].size / 1024 / 1024 > this.props.uiConfigs.data.maximumFileSizeAllowed) {
            this.setState({isLargeFileToUpload: true});
        }
        else {
            if (e.target.files && e.target.files[0]) {
                let reader = new FileReader();
                this.setState({
                    fileName: e.target.files[0].name, fileToUpload: e.target.files[0],
                    invalidImageFile: false, isLargeFileToUpload: false
                });
                reader.onload = (e) => {
                    this.setState({imageUrl: e.target.result});
                };
                reader.readAsDataURL(e.target.files[0]);
            }
        }
    }

    editProductHandler(product, selectedProductIndex) {
        this.setState({
            showpopup: true,
            showEditProductPopUp: true,
            selectedProduct: product,
            selectedProductIndex: selectedProductIndex
        }, () => {
            let ProductInfo = this.state.selectedProduct;
            this.setState({
                productName: ProductInfo.productName,
                quantity: ProductInfo.quantity,
                price: ProductInfo.price,
                imageUrl: ProductInfo.imageUrl,
                productDiscription: ProductInfo.productDiscription,
                fileName: ProductInfo.fileName
            });
        });
    }

    radioChangeHandler(rfqType) {
        this.props.actions.resetSaveRfqDetails();
        this.props.actions.resetFileUpload();
        this.setState({
            selectedRfqType: rfqType,
            isBulkRfqFileUploadValid: true, isProductTableValid: true,
            products: [], imageUrl: null, fileName: null, fileToUpload: null,
            isLargeFileToUpload: false, invalidImageFile: false,
            confirmation: false,
            switchRfqType: false
        });
    }

    getMultiLineLayout() {
        return (
            <div>
                <div className="m-t-5 m-b-5 flexContainer">
               <span className="input_span m-r-10">{this.props.localeFile.product}
                   <span className="required"> *</span>
               </span>
                    <div className="flexContainer">
                        <Button
                            icon={<span className="icon-add m-r-4 f-w-700 add_delete_icon"></span>}
                            type="submit"
                            cssClassName="m-10 add_product_btn clr_light_black add_remove_btn p-t-5 p-b-5"
                            buttonClickFunc={this.addProductHandler}
                            buttonName={this.props.localeFile.add_product}
                            isDisabled={this.state.products.length === this.props.uiConfigs.data.maximumLineItemsAllowed}/>

                        <Button
                            icon={<span className="icon-delete m-r-4 f-w-700 add_delete_icon"></span>}
                            type="submit"
                            cssClassName="m-10 delete_product_btn add_remove_btn p-t-5 p-b-5"
                            buttonClickFunc={this.deleteMultipalProductHandler}
                            buttonName={this.props.localeFile.delete_product}
                            isDisabled={this.state.checkedIndex ? false : true}/>
                    </div>

                </div>
                <div className="table-container">
                    <TableLayout
                        cssClassName="table_layout table_header"
                        getTableHeader={this.getTableHeader}
                        getTableBody={this.getTableBody}
                        isTableEmpty={this.state.products.length === 0
                            ? <div className="text_center span_middle">{this.props.localeFile.err_noProduct}</div>
                            : ''}/> {!this.state.isProductTableValid && !this.state.products.length
                    ? <span className="no_product_added">{this.props.localeFile.no_product_added}</span>
                    : ''}
                </div>
            </div>
        );
    }

    getBulkRfqLayout() {
        return (
            <div>
                <div className="col-md-5 p-0">
                    <span className="input_span upload_file">{this.props.localeFile.uploadBulk}
                        <span className="required"> *</span>
                    </span>
                    <UploadFile
                        cssClassName="input_span"
                        changeHandler={this.readURL}
                        inputType="file"
                        id="customFile"
                        fileName={this.state.imageUrl !== null ? this.state.fileName :
                            <span className="input_span">{this.props.localeFile.chooseFile}</span>}
                        imageUrl={this.state.imageUrl}
                        title=""
                        errorValidationState={this.state.invalidImageFile || this.state.isLargeFileToUpload}
                        errorMsg={(this.state.invalidImageFile || this.state.isLargeFileToUpload) ? (this.state.invalidImageFile ? this.props.localeFile.invalidImageFileBulk : this.props.localeFile.isLargeFileToUpload) : ''}
                    />
                    {!this.state.isBulkRfqFileUploadValid ?
                        <span className="bulk-file error-msg">{this.props.localeFile.err_fileUpload}</span> : ''}
                </div>
                <div className="col-md-5 m-t-10">
                    {this.state.fileUploadStatus && this.state.fileUploadStatus[0] ?
                        <ProgressBar
                            successStatus={(this.state.fileUploadStatus[0].successStatus === 'inProgress') ? 'bg-success' : this.state.fileUploadStatus[0].successStatus === true ? 'bg-success' : 'bg-danger'}
                            fileStatus={this.state.fileUploadStatus[0]}/>
                        : ''}
                </div>
            </div>
        );
    }

    render() {
        return (
            <div>
                <div>
                    {this.state.showpopup && this.state.showAddProductpopUp && <Modal
                        headerText={this.props.localeFile.product}
                        extraClass={'vertical-modal'}
                        bodyContent={< ProductForm localeFile={this.props.localeFile}
                                                   productName={this.state.productName}
                                                   productDiscription={this.state.productDiscription}
                                                   quantity={this.state.quantity}
                                                   fileName={this.state.fileName}
                                                   changeHandler={this.changeHandler}
                                                   price={this.state.price}
                                                   ProductErrorValidationState={this.state.isValidProductName}
                                                   quantityErrorValidationState={this.state.isValidQuantity}
                                                   priceErrorValidationState={this.state.isValidPrice}
                                                   isLargeFileToUpload={this.state.isLargeFileToUpload}
                                                   invalidImageFile={this.state.invalidImageFile}
                                                   discriptionErrorValidationState={this.state.isValidProductDiscription}
                                                   readURL={this.readURL}/>}
                        footerContent={this.popupFooterContent()}
                        closeModal={() => {
                            this.setState({
                                showpopup: false,
                                showAddProductpopUp: false,
                                isLargeFileToUpload: false,
                                invalidImageFile: false
                            }, () => {
                                this.clearAddProductForm(true);
                            });
                        }}/>}
                </div>
                <div>
                    {this.state.showpopup && this.state.showEditProductPopUp && <Modal
                        headerText={this.props.localeFile.editProduct}
                        extraClass={'vertical-modal'}
                        bodyContent={< ProductForm localeFile={this.props.localeFile}
                                                   productName={this.state.productName}
                                                   productDiscription={this.state.productDiscription}
                                                   quantity={this.state.quantity}
                                                   imageUrl={this.state.imageUrl}
                                                   fileName={this.state.fileName}
                                                   changeHandler={this.changeHandler}
                                                   price={this.state.price}
                                                   ProductErrorValidationState={this.state.isValidProductName}
                                                   quantityErrorValidationState={this.state.isValidQuantity}
                                                   priceErrorValidationState={this.state.isValidPrice}
                                                   discriptionErrorValidationState={this.state.isValidProductDiscription}
                                                   isLargeFileToUpload={this.state.isLargeFileToUpload}
                                                   invalidImageFile={this.state.invalidImageFile}
                                                   readURL={this.readURL}
                        />}
                        footerContent={this.popupFooterContent()}
                        closeModal={() => {
                            this.clearAddProductForm();
                            this.setState({
                                showpopup: false,
                                showEditProductPopUp: false,
                                isUpdatingExistingProduct: false,
                                selectedProductIndex: null,
                                productName: '',
                                quantity: '',
                                price: '',
                                imageUrl: null,
                                productDiscription: '',
                                fileName: null,
                                fileToUpload: null,
                                isLargeFileToUpload: false,
                                invalidImageFile: false
                            });
                        }}/>}
                </div>
                <div>
                    {this.state.showpopup && this.state.showProductImagePopUp && <Modal
                        headerText={this.state.selectedProduct.productName}
                        extraClass={'vertical-modal showImage'}
                        bodyContent={this.getImageBody()}
                        closeModal={() => {
                            this.setState({showpopup: false, showProductImagePopUp: false});
                        }}/>}
                </div>

                <div>
                    {this.state.confirmationBox && this.state.singleSelect ? <ConfirmationBox
                        headerText={this.props.localeFile.delete_product}
                        message={<h6>{this.props.localeFile.deleteAlert}</h6>}
                        confirmationSuccess={this.confirmationSuccessforSingleItem.bind(this)}
                        confirmationCancel={this.confirmationCancel.bind(this)}/> : ''}
                </div>

                <div>
                    {this.state.confirmationBox && this.state.multipleSelect ? <ConfirmationBox
                        headerText={this.props.localeFile.delete_product}
                        message={
                            <h6> {this.props.localeFile.deleteMultiSelectBeg} {this.state.checkedIndex} {this.props.localeFile.deleteMultiSelectlas}</h6>}
                        confirmationSuccess={this.confirmationSuccessforMultipleItem.bind(this)}
                        confirmationCancel={this.confirmationCancel.bind(this)}/> : ''}
                </div>

                <div>
                    {this.state.confirmationBox && this.state.switchRfqType ? <ConfirmationBox
                        headerText={this.props.localeFile.switchRfqType}
                        message={<h6>{this.props.localeFile.switchRfqAlert}</h6>}
                        confirmationSuccess={this.confirmationSuccessforSwitchRfqType.bind(this, 'hi')}
                        confirmationCancel={this.confirmationCancel.bind(this)}/> : ''}
                </div>
                <RfqHeader localeFile={this.props.localeFile}/>
                <div className="form_layout m-l-30 m-r-30 p-30">
                    <div className="user-details-container mb16">
                        <h4 className="form_header">{this.props.localeFile.user_details}</h4>
                        <div className="row">
                            <div className="col-md-4">
                                <Input
                                    cssClassName="input_span"
                                    required={true}
                                    changeHandler={this.changeHandler}
                                    inputType="text"
                                    title={this.props.localeFile.name}
                                    name="dealerName"
                                    placeholder={this.props.localeFile.name}
                                    maxLength={255}
                                    value={this.state.dealerName}
                                    validationFun="isEmpty"
                                    errorValidationState={this.state.isValidDealerName}
                                    errorMsg={this.props.localeFile.err_name}/>
                                <Input
                                    cssClassName="input_span"
                                    changeHandler={this.changeHandler}
                                    inputType="text"
                                    title={this.props.localeFile.company_name}
                                    name="companyName"
                                    placeholder={this.props.localeFile.company_name}
                                    maxLength={255}
                                    value={this.state.companyName}
                                    validationFun="isEmpty"
                                    errorValidationState={this.state.isValidCompanyName}
                                    errorMsg={this.props.localeFile.err_compName}/>
                                <TextArea
                                    cssClassName="input_span"
                                    changeHandler={this.changeHandler}
                                    title={this.props.localeFile.company_add}
                                    name="companyAddress"
                                    placeholder={this.props.localeFile.company_add}
                                    maxLength={255}
                                    value={this.state.companyAddress}
                                    validationFun="isEmpty"
                                    errorValidationState={this.state.isValidCompanyAddress}
                                    errorMsg={this.props.localeFile.err_compAdd}/>
                            </div>
                            <div className="col-md-4 offset-md-1">
                                <Input
                                    cssClassName="input_span"
                                    changeHandler={this.changeHandler}
                                    inputType="text"
                                    title={this.props.localeFile.email}
                                    name="dealerEmail"
                                    placeholder={this.props.localeFile.email}
                                    maxLength={255}
                                    value={this.state.dealerEmail}
                                    validationFun="isValidEmailId"
                                    errorValidationState={this.state.isValidDealerEmail}
                                    errorMsg={this.props.localeFile.err_email}/>
                                <Input
                                    cssClassName="input_span"
                                    changeHandler={this.changeHandler}
                                    inputType="number"
                                    title={this.props.localeFile.teliphone}
                                    name="phoneNumber"
                                    placeholder={this.props.localeFile.phoneNumber}
                                    maxLength={255}
                                    value={this.state.phoneNumber}
                                    validationFun="isValidMobileNumber"
                                    errorValidationState={this.state.isValidPhoneNumber}
                                    errorMsg={this.props.localeFile.err_mob}/>
                                <BusinessSelectBox
                                    cssClassName="input_span"
                                    changeHandler={this.changeHandler}
                                    title={this.props.localeFile.industry}
                                    name="businessType"
                                    placeholder={this.props.localeFile.selectIndustrialFeild}
                                    validationFun="isEmpty"
                                    value={this.state.businessType}
                                    errorMsg={this.props.localeFile.err_industry}
                                    errorValidationState={this.state.isValidBusinessType}
                                    options={this.state.industrySector}/>
                            </div>
                        </div>
                    </div>
                    <div className="user-details-container mt16">
                        <h4 className="form_header">{this.props.localeFile.rfq_details}</h4>
                        <div className="row">
                            <div className="col-md-4">
                                {/*<PaymentSelectBox*/}
                                {/*cssClassName="input_span"*/}
                                {/*changeHandler={this.changeHandler}*/}
                                {/*title={this.props.localeFile.payment_method}*/}
                                {/*name="paymentMethod"*/}
                                {/*placeholder={this.props.localeFile.selectPaymentMethod}*/}
                                {/*value={this.state.paymentMethod}*/}
                                {/*errorMsg={this.props.localeFile.err_payment}*/}
                                {/*validationFun="isEmpty"*/}
                                {/*errorValidationState={this.state.isValidPaymentMethod}*/}
                                {/*options={this.state.paymentMethods}/>*/}

                                <TextArea
                                    cssClassName="input_span"
                                    changeHandler={this.changeHandler}
                                    title={this.props.localeFile.shipping_add}
                                    name="shippingAddress"
                                    placeholder={this.props.localeFile.shipping_add}
                                    maxLength={255}
                                    value={this.state.shippingAddress}
                                    validationFun="isEmpty"
                                    errorMsg={this.props.localeFile.err_shipping}
                                    errorValidationState={this.state.isValidShippingAddress}/>
                            </div>
                            <div className="col-md-4 offset-md-1">
                                <label htmlFor="select" className="input_span">{this.props.localeFile.receipt_date}
                                    <span className="required"> *</span>
                                </label>
                                <div className={classNames({'border-err': !this.state.isValidReceiptDate})}>
                                    <DayPickerInput
                                        firstDayOfWeek={1}
                                        onDayChange={(day) => this.onDayChange('receiptDate', 'isEmpty', day)}
                                        format="DD/MM/YYYY"
                                        formatDate={formatDate}
                                        placeholder="DD/MM/YYYY"
                                        value={this.state.receiptDate}
                                        inputProps={{
                                            readOnly: 'readOnly',
                                            id: 'date'
                                        }}
                                        dayPickerProps={{
                                            selectedDays: this.props.selectedDay,
                                            disabledDays: {
                                                before: new Date()
                                            },
                                            locale: this.props.localeFile.calenderLocalization,
                                            months: this.props.localeFile.MONTHS,
                                            weekdaysLong: this.props.localeFile.WEEKDAYS_LONG,
                                            weekdaysShort: this.props.localeFile.WEEKDAYS_SHORT
                                        }}/>
                                </div>
                                <label htmlFor="date" className="icon-calendar icon_positioning"></label>
                                {!this.state.isValidReceiptDate &&
                                <span className="receiptDate error-msg">{this.props.localeFile.err_receiptDate}</span>}
                            </div>
                        </div>
                    </div>
                    <div className="rfqRadioContainer">
                        <span className="input_span m-r-10">{this.props.localeFile.uploadProduct}</span>
                        {
                            this.state.rfqTypeList.map(rfqType => {
                                return (
                                    <Radio
                                        inputType="radio"
                                        value={rfqType}
                                        displayValue={localStorage.getItem('systemLang') === 'en' ? rfqType.rfqTypeDescriptionEn : rfqType.rfqTypeDescriptionId}
                                        name="rfqTypeList"
                                        key={rfqType.rfqTypeId}
                                        checked={this.state.selectedRfqType.rfqTypeId === rfqType.rfqTypeId}
                                        radioChangeHandler={() => this.rfqTypeSwitchAlert(rfqType)}
                                    />
                                );
                            })
                        }
                    </div>
                    {this.state.selectedRfqType.rfqTypeCode === 'RFQ_MULTILINE' ?
                        <div className="m-r-10">{this.getMultiLineLayout()}</div> :
                        <div className="m-r-10">{this.getBulkRfqLayout()}</div>}

                    <div className="btn-container submit_form_container">
                        <Button
                            type="submit"
                            cssClassName="m-r-10 disable_btn btn-secondary"
                            buttonClickFunc={this.clearRfqForm}
                            buttonName={this.props.localeFile.clean_up}
                            isDisabled={this.props.isDisabled}/>
                        <Button
                            type="submit"
                            cssClassName="m-r-10 submit_btn btn-success"
                            buttonClickFunc={this.validateformSubmission}
                            buttonName={this.props.localeFile.submit_req}
                            isDisabled={this.props.formValidationFlag}/>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        RfqState: state.rfq,
        uiConfigs: state.app.uiConfigs,
        fileUpload: state.fileUpload
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        actions: bindActionCreators(actions, dispatch)
    };
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(RFQForm));
