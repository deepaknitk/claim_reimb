export function createApiParams(uiConfigs) {
    return '?storeId='+ uiConfigs.storeId + '&requestId=' + Date.now() + '&clientId=' + uiConfigs.clientId;
}
