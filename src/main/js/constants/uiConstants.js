let getUrl = window.location;
export const baseUrl = getUrl .protocol + '//' + getUrl.host;
export const UI_BASE_PATH = '/lts-ui/views';
export const BASE_API_URL = baseUrl + '/lts-ui/api/';

export const MASTER_DETAILS = BASE_API_URL + 'rfq/master-data-list';
export const SAVE_RFQ = BASE_API_URL + 'rfq/create';
export const SAVE_FILE = BASE_API_URL + 'file/upload';
