
import * as types from '../constants/actionTypes';

export default function FileUploadReducer(state = {}, action) {
    switch (action.type) {
        case types.SET_FILE_PROGRESS:
        return Object.assign({}, state, state.fileUpload, {[action.payload.index]: action.payload});
        case types.RESET_FILE_UPLOAD:
            return {};
        default:
        return state;
    }
}
