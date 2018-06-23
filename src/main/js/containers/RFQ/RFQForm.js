import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';
import {bindActionCreators} from 'redux';
import * as actions from '../../actions/index';
import Validator from '../../utils/validator.js';
import '../../styles/day-picker.scss';
import Button from '../../components/Button';
import TableLayout from '../../components/Table';
import '../../styles/RFQForm.scss';
import Modal from '../../components/Modal';
import ProductForm from '../ProductForm';
import ConfirmationBox from '../../components/AlertBox';
import Input from '../../components/Input';


class RFQForm extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            employeeId: '',
            isValidEmployeeId: true,
            employeeEmail: '',
            isValidEmployeeEmail: true,
            billNo: '',
            isValidBillNo: true,
            quantity: '',
            isValidQuantity: true,
            price: '',
            isValidPrice: true,
            date: '',
            isValidDate: true,
            imageUrl: '',
            isValidImageUrl: true,
            claimDiscription: '',
            isValidClaimDiscription: true,
            remarks: '',
            isValidRemarks: true,
            claims: [],
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
            fileUploadStatus: {},
            confirmationBox: false,
            singleSelect: false,
            multipleSelect: false,
            type: '',
            isValidType: true,
            isValidClaimTable: true
        };
        this.changeHandler = this.changeHandler.bind(this);
        this.onDayChange = this.onDayChange.bind(this);
        this.validateformSubmission = this.validateformSubmission.bind(this);
        this.clearClaimForm = this.clearClaimForm.bind(this);
        this.getTableHeader = this.getTableHeader.bind(this);
        this.getTableBody = this.getTableBody.bind(this);
        this.addClaimHandler = this.addClaimHandler.bind(this);
        this.deleteClaimHandler = this.deleteClaimHandler.bind(this);
        this.getClaimDetails = this.getClaimDetails.bind(this);
        this.deleteMultipalProductHandler = this.deleteMultipalProductHandler.bind(this);
        this.saveClaims = this.saveClaims.bind(this);
        this.clearAddProductForm = this.clearAddProductForm.bind(this);
        this.validateSaveClaimForm = this.validateSaveClaimForm.bind(this);
        this.readURL = this.readURL.bind(this);
        this.showProductImage = this.showProductImage.bind(this);
        this.editProductHandler = this.editProductHandler.bind(this);
        this.isDeleteGranted = this.isDeleteGranted.bind(this);
        this.getRfqProducts = this.getRfqProducts.bind(this);
    }

    componentDidMount() {
        console.log('mounted');
    }

    changeHandler(fieldName, validationFuncName, event) {
        this.setState({[fieldName]: event.target.value});
        this.validateField(fieldName, validationFuncName, event.target.value);
    }

    onDayChange(fieldName, validationFuncName, day) {
        this.setState({date: day});
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
        let allRequiredFeilds = [
            {name: 'employeeId', validFun: 'isEmpty'},
            {name: 'employeeEmail', validFun: 'isValidEmailId'}
        ];
        let isFormValidationFlag = true;
        allRequiredFeilds.map(eachField => {
            const currentFieldStatus = this.validateField(eachField.name, eachField.validFun, this.state[eachField.name].trim());
            if (!currentFieldStatus) {
                this.setState({formValidationFlag: false});
                isFormValidationFlag = false;
            }
        });
        if (!this.state.claims.length) {
            this.state.isValidClaimTable = false;
        }
        if (isFormValidationFlag) {
            this.setState({formValidationFlag: true}, () => {
                console.log('yes');
                //this.props.actions.sendRfqDetails(this.getClaimDetails());
            });
        }
    }

    validateSaveClaimForm() {
        let allRequiredFeilds =
            [
                {name: 'imageUrl', validFunName: 'isEmpty'},
                {name: 'billNo', validFunName: 'isEmpty'},
                {name: 'price', validFunName: 'isValidFloat'},
                {name: 'claimDiscription', validFunName: 'isEmpty'},
                {name: 'type', validFunName: 'isEmpty'}
            ];
        let isSaveClaimFormValid = true;
        allRequiredFeilds.map(eachfeild => {
            const currentFieldStatus = this.validateField(eachfeild.name, eachfeild.validFunName, this.state[eachfeild.name].trim());
            if (!currentFieldStatus) {
                isSaveClaimFormValid = false;
            }
        });
        const currentFieldStatus = this.validateField('date', 'isValidDate', this.state.date);
        if (!currentFieldStatus) {
            isSaveClaimFormValid = false;
        }
        if (isSaveClaimFormValid) {
            this.saveClaims(this.state.showEditProductPopUp);
        }
    }

    getTableHeader() {
        return (
            <tr className="table_layout table_header">
                <th></th>
                <th>Date</th>
                <th>Bill No.</th>
                <th>Discriptions</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Remarks</th>
                <th>Image</th>
                <th></th>
                <th></th>
            </tr>
        );
    }

    getTableBody() {
        return (
            <tbody className="table_body">
            {this.state.claims.map((claimDetails, i) => <tr key={i}
                                                       className={claimDetails.isCheckedIndex ? 'checkedRowColor' : ''}>
                <td><input type="checkbox" name={i} id={i}
                           checked={claimDetails.isCheckedIndex ? true : false} onClick={() => this.handleCheckBox(i)}/>
                </td>
                <td>{claimDetails.date.toLocaleDateString()}</td>
                <td>{claimDetails.billNo}</td>
                <td>{claimDetails.claimDiscription}</td>
                <td>{claimDetails.type}</td>
                <td>{claimDetails.price}</td>
                <td>{claimDetails.remarks}</td>
                <td className="product_image pointer"
                    onClick={() => this.showProductImage(claimDetails)}>View File
                </td>
                <td className="icon-mode_edit icon_clickable table_icons icon_edit"
                    onClick={() => this.editProductHandler(claimDetails, i)}></td>
                <td className="icon-delete icon_clickable table_icons icon_delete"
                    onClick={() => this.deleteClaimHandler(i)}></td>
            </tr>)}
            </tbody>
        );
    }

    showProductImage(selectedProduct) {
        this.setState({showpopup: true, showProductImagePopUp: true, selectedProduct: selectedProduct});
    }

    getImageBody() {
        const selectedProduct = this.state.selectedProduct;
        if (selectedProduct.imageUrl !== undefined && selectedProduct.imageUrl !== '') {
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

    addClaimHandler() {
        this.setState({showpopup: true, showAddProductpopUp: true});
    }

    saveClaims(isUpdatingExistingProduct) {
        const claims = {
            date: this.state.date,
            billNo: this.state.billNo,
            claimDiscription: this.state.claimDiscription,
            type: this.state.type,
            price: this.state.price,
            remarks: this.state.remarks,
            imageUrl: this.state.imageUrl,
            fileName: this.state.fileName,
            fileToUpload: this.state.fileToUpload,
            isCheckedIndex: false
        };
        let tempProducts = this.state.claims;
        if (!isUpdatingExistingProduct) {
            tempProducts.push(claims);
            this.setState({claims: tempProducts, showpopup: false, showAddProductPopUp: false});
        } else {
            const selectedProductIndex = this.state.selectedProductIndex;
            const tempProducts = this.state.claims;
            tempProducts[selectedProductIndex] = claims;
            this.setState({
                claims: tempProducts,
                showEditProductPopUp: false,
                isUpdatingExistingProduct: false,
                selectedProductIndex: null,
                showpopup: false
            });
        }
        this.clearAddProductForm();
    }

    confirmationSuccessforSingleItem() {
        this.deleteProduct();
    }

    confirmationSuccessforMultipleItem() {
        this.isDeleteGranted();
    }

    confirmationSuccessforSwitchRfqType() {
        this.radioChangeHandler(this.state.switchRfqTypeTo);
    }

    confirmationCancel() {
        const tempProduct = this.state.claims;
        tempProduct.map(product => {
            product.isCheckedIndex = false;
        });
        this.setState({
            confirmationBox: false,
            singleSelect: false,
            switchRfqType: false,
            multipleSelect: false,
            claims: tempProduct,
            checkedIndex: 0
        });
    }

    deleteMultipalProductHandler() {
        this.setState({confirmationBox: true, multipleSelect: true});
    }

    isDeleteGranted() {
        let remProducts = this.state.claims.filter(product => product.isCheckedIndex !== true);
        this.setState({showpopup: false, showDeleteProductPopUp: false, claims: remProducts, checkedIndex: 0});
        this.setState({confirmationBox: false, multipleSelect: false});
    }

    deleteClaimHandler(index) {
        this.setState({confirmationBox: true, selectedProductIndex: index, singleSelect: true});
    }

    deleteProduct() {
        let addedProducts = this.state.claims;
        addedProducts.splice(this.state.selectedProductIndex, 1);
        this.setState({claims: addedProducts});
        this.setState({selectedProductIndex: null, confirmationBox: false, singleSelect: false});
    }

    getClaimDetails() {
        const claimPayload = {
            employeeId: this.state.employeeId,
            employeeEmail: this.state.employeeEmail,
            claims: this.state.claims

        };
        console.log(claimPayload);
        return claimPayload;
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
        this.state.claims.map(product => {
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
        let tempProducts = this.state.claims;
        if (document.getElementById(index).checked) {
            tempProducts[index].isCheckedIndex = true;
            let checkedIndex = this.state.checkedIndex;
            this.setState({checkedIndex: checkedIndex + 1});
            this.setState({claims: tempProducts});
        }
        if (!document.getElementById(index).checked) {
            tempProducts[index].isCheckedIndex = false;
            let checkedIndex = this.state.checkedIndex;
            this.setState({checkedIndex: checkedIndex - 1});
            this.setState({claims: tempProducts});
        }
    }

    clearClaimForm() {
        this.setState({
            employeeId: '',
            isValidEmployeeId: true,
            isValidEmployeeEmail: true,
            isValidClaimTable: true,
            employeeEmail: '',
            claims: []
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
                    buttonClickFunc={this.validateSaveClaimForm}
                    buttonName={!this.state.showEditProductPopUp ? this.props.localeFile.add : this.props.localeFile.save}
                    isDisabled={false}/>
            </div>
        );
    }

    clearAddProductForm(closeModal) {
        let productFormField = ['billNo', 'date', 'type', 'price', 'imageUrl', 'claimDiscription', 'remarks'];
        productFormField.map(productField => {
            let validationState = 'isValid' + productField.charAt(0).toUpperCase() + productField.slice(1);
            this.setState({[productField]: '', [validationState]: true});
        });
        this.setState({
            fileName: null,
            imageUrl: '',
            fileToUpload: null
        });
    }

    readURL(e) {
        this.setState({
            fileToUpload: null,
            imageUrl: '',
            fileName: null
        });
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

    editProductHandler(product, selectedProductIndex) {
        this.setState({
            showpopup: true,
            showEditProductPopUp: true,
            selectedProduct: product,
            selectedProductIndex: selectedProductIndex
        }, () => {
            let ProductInfo = this.state.selectedProduct;
            this.setState({
                billNo: ProductInfo.billNo,
                date: ProductInfo.date,
                type: ProductInfo.type,
                price: ProductInfo.price,
                imageUrl: ProductInfo.imageUrl,
                claimDiscription: ProductInfo.claimDiscription,
                remarks: ProductInfo.remarks,
                fileName: ProductInfo.fileName
            });
        });
    }

    getClaimTableLayout() {
        return (
            <div>
                <div className="m-t-5 m-b-5 flexContainer m-t-20 m-b-20" style={{justifyContent: 'center'}}>
                    <div className="flexContainer" style={{textAlign: 'center'}}>
                        <Button
                            icon={<span className="icon-add m-r-4 f-w-700 add_delete_icon"></span>}
                            type="submit"
                            cssClassName="m-10 add_product_btn clr_light_black add_remove_btn p-t-5 p-b-5"
                            buttonClickFunc={this.addClaimHandler}
                            buttonName='Add Claim'
                            isDisabled={false}/>

                        <Button
                            icon={<span className="icon-delete m-r-4 f-w-700 add_delete_icon"></span>}
                            type="submit"
                            cssClassName="m-10 delete_product_btn add_remove_btn p-t-5 p-b-5"
                            buttonClickFunc={this.deleteMultipalProductHandler}
                            buttonName='Delete Claim'
                            isDisabled={this.state.checkedIndex ? false : true}/>
                    </div>

                </div>
                <div className="table-container">
                    <TableLayout
                        cssClassName="table_layout table_header"
                        getTableHeader={this.getTableHeader}
                        getTableBody={this.getTableBody}
                        isTableEmpty={this.state.claims.length === 0
                            ? <div className="text_center span_middle">You have not added any Claim</div>
                            : ''}/> {!this.state.isValidClaimTable && !this.state.claims.length
                    ? <span className="no_product_added">Add claim which you want to submit</span>
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
                        headerText="Add claim"
                        extraClass={'vertical-modal'}
                        bodyContent={<ProductForm localeFile={this.props.localeFile}
                                                  billNo={this.state.billNo}
                                                  claimDiscription={this.state.claimDiscription}
                                                  quantity={this.state.quantity}
                                                  fileName={this.state.fileName}
                                                  date={this.state.date}
                                                  dateErrorValidationState={this.state.isValidDate}
                                                  imageUrl={this.state.imageUrl}
                                                  imageUrlErrorValidationState={this.state.isValidImageUrl}
                                                  remarks={this.state.remarks}
                                                  type={this.state.type}
                                                  changeHandler={this.changeHandler}
                                                  onDayChange={this.onDayChange}
                                                  price={this.state.price}
                                                  billNoErrorValidationState={this.state.isValidBillNo}
                                                  quantityErrorValidationState={this.state.isValidQuantity}
                                                  typeErrorValidationState={this.state.isValidType}
                                                  priceErrorValidationState={this.state.isValidPrice}
                                                  isLargeFileToUpload={this.state.isLargeFileToUpload}
                                                  invalidImageFile={this.state.invalidImageFile}
                                                  discriptionErrorValidationState={this.state.isValidClaimDiscription}
                                                  remarksErrorValidationState={this.state.isValidRemarks}
                                                  readURL={this.readURL}/>}
                        footerContent={this.popupFooterContent()}
                        closeModal={() => {
                            this.setState({
                                showpopup: false,
                                showAddProductpopUp: false
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
                                                   billNo={this.state.billNo}
                                                   claimDiscription={this.state.claimDiscription}
                                                   quantity={this.state.quantity}
                                                   fileName={this.state.fileName}
                                                   date={this.state.date}
                                                   dateErrorValidationState={this.state.isValidDate}
                                                   imageUrl={this.state.imageUrl}
                                                   imageUrlErrorValidationState={this.state.isValidImageUrl}
                                                   remarks={this.state.remarks}
                                                   type={this.state.type}
                                                   changeHandler={this.changeHandler}
                                                   onDayChange={this.onDayChange}
                                                   price={this.state.price}
                                                   billNoErrorValidationState={this.state.isValidBillNo}
                                                   quantityErrorValidationState={this.state.isValidQuantity}
                                                   typeErrorValidationState={this.state.isValidType}
                                                   priceErrorValidationState={this.state.isValidPrice}
                                                   isLargeFileToUpload={this.state.isLargeFileToUpload}
                                                   invalidImageFile={this.state.invalidImageFile}
                                                   discriptionErrorValidationState={this.state.isValidClaimDiscription}
                                                   remarksErrorValidationState={this.state.isValidRemarks}
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
                                fileName: null,
                                fileToUpload: null
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
                        headerText="Delete Claim"
                        message="You want to delete your claim ?"
                        confirmationSuccess={this.confirmationSuccessforSingleItem.bind(this)}
                        confirmationCancel={this.confirmationCancel.bind(this)}/> : ''}
                </div>

                <div>
                    {this.state.confirmationBox && this.state.multipleSelect ? <ConfirmationBox
                        headerText="Delete Claims"
                        message={<span>You want to delete selected claims ? </span>}
                        confirmationSuccess={this.confirmationSuccessforMultipleItem.bind(this)}
                        confirmationCancel={this.confirmationCancel.bind(this)}/> : ''}
                </div>

                <div className="form_layout m-l-30 m-r-30 p-30 m-t-30">
                    <div style={{display: 'flex', justifyContent: 'center'}}>
                        <div className="col-md-6 card p-30">
                            <Input
                                cssClassName="input_span"
                                required={true}
                                changeHandler={this.changeHandler}
                                inputType="text"
                                title="Employee Id."
                                name="employeeId"
                                placeholder="Employee Id"
                                maxLength={255}
                                value={this.state.employeeId}
                                validationFun="isEmpty"
                                errorValidationState={this.state.isValidEmployeeId}
                                errorMsg="Enter your employee Id"
                            />


                            <Input
                                cssClassName="input_span"
                                required={true}
                                changeHandler={this.changeHandler}
                                inputType="text"
                                title="Employee Email."
                                name="employeeEmail"
                                placeholder="Employee email Id"
                                maxLength={255}
                                value={this.state.employeeEmail}
                                validationFun="isValidEmailId"
                                errorValidationState={this.state.isValidEmployeeEmail}
                                errorMsg="Enter your email Id"
                            />
                        </div>
                    </div>
                    <div className="m-r-10">{this.getClaimTableLayout()}</div>
                </div>


                <div className="btn-container submit_form_container">
                    <Button
                        type="submit"
                        cssClassName="m-r-10 disable_btn btn-secondary"
                        buttonClickFunc={this.clearClaimForm}
                        buttonName="Cancel Claim"
                        isDisabled={this.props.isDisabled}/>
                    <Button
                        type="submit"
                        cssClassName="m-r-10 submit_btn btn-success"
                        buttonClickFunc={this.validateformSubmission}
                        buttonName="Send Claim"
                        isDisabled={this.props.formValidationFlag}/>
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
