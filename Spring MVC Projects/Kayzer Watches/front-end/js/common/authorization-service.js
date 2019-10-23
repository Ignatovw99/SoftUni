var app = app || {};

let authorization = {};

function saveCredentials(credentials) {
    authorization['token'] = credentials;
    authorization['role'] = JSON.parse(atob(credentials.split('.')[1]))['role'];
}

function getCredentials() {
    if (authorization['token']) {return 'Bearer ' + authorization['token'];}
}

function getRole() {
    if (authorization['role']) return authorization['role'];
}

function evictCredentials() {
    authorization = {};
}

app.authorizationService = function () {
    return {
        saveCredentials: saveCredentials,
        getCredentials: getCredentials,
        getRole: getRole,
        evictCredentials: evictCredentials
    }
}();