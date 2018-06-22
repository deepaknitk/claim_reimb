require('es6-object-assign').polyfill();
import * as types from '../constants/actionTypes';
import objectAssign from 'object-assign';

export default function rfqReducer (state = {}, action) {
    switch(action.type) {
        case types.SAVE_MASTER_DETAILS:
            return objectAssign({}, state, {masterDetails: action.payload.value});
        case types.SAVE_RFQ_DETAILS_RESPONSE:
            return objectAssign({}, state, {submitRfqResponse: action.payload});
        case types.SAVE_IMAGE:
             return objectAssign({}, state, { userImgObject: action.response });
        case types.CLEAR_RFQ_STATE:
            return {};
        default:
            return state;
    }
}
