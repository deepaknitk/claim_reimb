require('es6-object-assign').polyfill();
import * as types from '../constants/actionTypes';
import objectAssign from 'object-assign';

export default function rfqReducer (state = {}, action) {
    switch(action.type) {
        case types.SAVE_MASTER_DETAILS:
            return objectAssign({}, state, {masterDetails: action.payload.value});
        default:
            return state;
    }
}
