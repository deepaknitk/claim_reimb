import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware, compose } from 'redux';
import { Provider } from 'react-redux';
import { BrowserRouter, Route } from 'react-router-dom';
import thunk from 'redux-thunk';
import * as UI_CONSTANTS from './constants/uiConstants';
import reducer from './reducers/index';
import App from './containers/App.js';

if(typeof (window) !== undefined) {
    const store = createStore(reducer,
        compose(applyMiddleware(thunk),
        window.devToolsExtension ? window.devToolsExtension() : f => f));
    ReactDOM.render(
        <Provider store={store}>
            <BrowserRouter basename={UI_CONSTANTS.UI_BASE_PATH}>
                <Route path="/" component={App}/>
            </BrowserRouter>
        </Provider>,
        document.getElementById('mount'));
}
