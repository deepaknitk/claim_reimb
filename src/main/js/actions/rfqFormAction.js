import Axios from 'axios';
import * as types from '../constants/actionTypes';
import * as setMessage from './messageAction';
import * as uiConstant from '../constants/uiConstants';
import {toggleLoader} from './loaderAction';
import BAHASA from '../locales/lts.id';
import ENGLISH from '../locales/lts.en';
import {setErrorMessage} from './messageAction';

export const saveClaimRequest = (claimsDetails) => {
    return (dispatch) => {
        Axios({
            method: 'post',
            url: '/claims/api/reimbursement/create',
            header: {
                'Content-Type': 'multipart/form-data'
            },
            data: claimsDetails
        })
            .then(response => {
                if (response.data.success) {
                    dispatch(setMessage.setSuccessMessage('Claim submitted'));
                } else {
                    dispatch(setMessage.setErrorMessage(response.data.errorMessage));
                }
            })
            .catch(() => {
                dispatch(setMessage.setErrorMessage('Internal server error'));
            });
    };
};


export const saveImageFile = (uploadFile) => {
    const SAVE_FILE = uiConstant.SAVE_FILE;
    return (dispatch) => {
        dispatch(toggleLoader(true));
        const formData = new FormData();
        formData.append('file', uploadFile.fileToUpload);
        const config = {
            params: {
                rfqId: uploadFile.rfqId,
                rfqItemId: uploadFile.rfqItemId,
                rfqTypeId: uploadFile.rfqTypeId
            },
            headers: {
                'content-type': 'multipart/form-data'
            },
            onUploadProgress: progressEvent => {
                let percentCompleted = Math.round((progressEvent.loaded * 100) / progressEvent.total);
                if (percentCompleted < 100) {
                    dispatch(toggleLoader(true));
                }
                uploadFile.percentage = percentCompleted;
                uploadFile.successStatus = 'inProgress';
                dispatch(setImageUploadedData(uploadFile));
            }
        };
        Axios.post(SAVE_FILE, formData, config)
            .then((response) => {
                if (response.data.success) {
                    uploadFile.successStatus = true;
                    dispatch(setImageUploadedData(uploadFile));
                }
                else {
                    uploadFile.successStatus = false;
                    dispatch(setImageUploadedData(uploadFile));
                    if (response.data.errorCode in ENGLISH.errorCode && response.data.errorCode in BAHASA.errorCode) {
                        dispatch(setErrorMessage(localStorage.getItem('systemLang') === 'en' ? ENGLISH.errorCode[response.data.errorCode] : BAHASA.errorCode[response.data.errorCode]));
                    }
                    else {
                        dispatch(setErrorMessage(localStorage.getItem('systemLang') === 'en' ? ENGLISH.errorCode.somethigWentWrong : BAHASA.errorCode.somethigWentWrong));
                    }
                }
                dispatch(toggleLoader(false));
            })
            .catch(() => {
                uploadFile.successStatus = false;
                dispatch(setImageUploadedData(uploadFile));
                dispatch(setErrorMessage(localStorage.getItem('systemLang') === 'en' ? ENGLISH.errorCode.somethigWentWrong : BAHASA.errorCode.somethigWentWrong));
                dispatch(toggleLoader(false));
            });
    };
};

export function resetFileUpload() {
    return {type: types.RESET_FILE_UPLOAD};
}

export function resetSaveRfqDetails() {
    return {type: types.CLEAR_RFQ_STATE};
}

const saveRfqDetailsResponse = (rfqDetailsResponse) => {
    return {type: types.SAVE_RFQ_DETAILS_RESPONSE, payload: rfqDetailsResponse};
};

export function setImageUploadedData(uploadFile) {
    return {type: types.SET_FILE_PROGRESS, payload: uploadFile};
}
