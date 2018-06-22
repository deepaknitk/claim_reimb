require('es6-object-assign').polyfill();
import objectAssign from 'object-assign';
import * as types from '../constants/actionTypes';

export default function appReducer(state = {}, action) {
  switch (action.type) {
    case types.SAVE_UI_CONFIGS:
      return objectAssign({}, {uiConfigs: action.response});
    default:
      return state;
  }
}
