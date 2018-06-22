import Axios from 'axios';
import * as types from '../constants/actionTypes';
import * as uiConstant from '../constants/uiConstants';
import {toggleLoader} from './loaderAction';
import {setErrorMessage} from './messageAction';
import ENGLISH from '../locales/lts.en';
import BAHASA from '../locales/lts.id';

export const getMasterDetails = () => {
    return (dispatch) => {
        const masterApiUrl = uiConstant.MASTER_DETAILS;
        dispatch(toggleLoader(false));
        Axios
            .get(masterApiUrl)
            .then(response => {
                if(response.data.success) {
                    dispatch(saveMasterDetails(response.data));
                    toggleLoader(false);
                }
                else {
                    dispatch(setErrorMessage(localStorage.getItem('systemLang') === 'en' ? ENGLISH.errorCode.somethigWentWrong : BAHASA.errorCode.somethigWentWrong));
                }
            })
            .catch( () => {
                dispatch(setErrorMessage(localStorage.getItem('systemLang') === 'en' ? ENGLISH.errorCode.somethigWentWrong : BAHASA.errorCode.somethigWentWrong));
                toggleLoader(false);
            });
    };
};

export function saveMasterDetails(response) {
    return {type: types.SAVE_UI_CONFIGS, response};
}
