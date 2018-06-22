import * as types from '../constants/actionTypes';

export function setErrorMessage(response) {
	return {
		type: types.SET_ERROR_MESSAGE,
		response
	};
}

export function setSuccessMessage(response) {
    return {
        type: types.SET_SUCCESS_MESSAGE,
        response
    };
}
