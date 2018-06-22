require('es6-object-assign').polyfill();
import objectAssign from 'object-assign';
import * as types from '../constants/actionTypes';

export default function errorMessageReducer(state = {}, action) {
  switch (action.type) {
    case types.SET_ERROR_MESSAGE:
      return objectAssign({}, {errorMessage: action.response});
    case types.SET_SUCCESS_MESSAGE:
      return objectAssign({}, {successMessage: action.response});
    default:
      return state;
  }
}
