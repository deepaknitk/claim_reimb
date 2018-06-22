import * as types from '../constants/actionTypes';

export function toggleLoader(isLoader) {
    return {
        type: types.TOGGLE_LOADER,
        isLoader
    };
}
