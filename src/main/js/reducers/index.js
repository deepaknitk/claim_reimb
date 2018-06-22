require('es6-object-assign').polyfill();
import { combineReducers } from 'redux';
import rfqReducer from './rfqReducer';
import messageReducer from './messageReducer';
import loaderReducer from './loaderReducer';
import appReducer from './appReducer';
import fileUploadReducer from './fileUploadReducer';
const rootReducer = combineReducers(Object.assign({}, {
    rfq: rfqReducer,
    message: messageReducer,
    loader: loaderReducer,
    app: appReducer,
    fileUpload: fileUploadReducer

}));
export default rootReducer;
