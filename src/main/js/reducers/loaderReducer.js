require('es6-object-assign').polyfill();
import objectAssign from 'object-assign';
import * as types from '../constants/actionTypes';

export default function loaderReducer(state = {}, action) {
  switch (action.type) {
    case types.TOGGLE_LOADER:
      return objectAssign({}, {showLoader: action.isLoader});
    default:
      return state;
  }
}
