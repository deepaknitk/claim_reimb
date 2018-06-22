function trim(str) {
    str = str.replace(/\s+/g, ' ');
    str = str.replace(/^\s|\s$/g, '');
    return str;
}

function isValidString(name) {

    if (name === null || name === undefined) {
        return false;
    }

    name = trim(name);
    if (name === '') {
        return false;
    }
    let namePattern = /^[a-zA-Z\s\.]+$/;

    let matches = name.match(namePattern);
    if (matches === null || (matches[0] !== name)) {
        return false;
    }
    return true;
}

function isValidNumeric(num) {
    if (num === null || num === undefined) {
        return false;
    }
    // num = trim(num);
    if (num === '') {
        return false;
    }

    let numPattern = /^[0-9]*$/;
    let matches = num.match(numPattern);
    if (matches === null || (matches[0] !== num)) {
        return false;
    }
    return true;
}
function isValidQuantity(num) {
    if (isValidNumeric(num) && parseInt(num, 10) !== 0) {
        return true;
    }
    return false;


}
function isValidFloat(num) {
    if (num === null || num === undefined) {
        return false;
    }
    if (num === '') {
        return false;
    }

    let floatPattern = /[0-9]+(\.[0-9][0-9]?)?/;
    let matches = num.match(floatPattern);
    if (matches === null || (matches[0] !== num)) {
        return false;
    }
    return true;
}

function isValidAlphaNum(alphaNum) {

    if (alphaNum === null || alphaNum === undefined) {
        return false;
    }

    alphaNum = trim(alphaNum);
    if (alphaNum === '') {
        return false;
    }

    let alphaNumPattern = /^[a-zA-Z\s\.0-9]+$/;

    let matches = alphaNum.match(alphaNumPattern);
    if (matches === null || (matches[0] !== alphaNum)) {
        return false;
    }
    return true;
}

function isValidPassword(str) {
    let returnValue = false;

    // check for null string
    if (str === null) {
        return returnValue;
    }
    str = trim(str);

    // check for empty or for spaces
    if ((str === '') || (str.search(/\s/) !== -1)) {
        return returnValue;
    }
    let re = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_.!*?]).{8,}$/;
    // let re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
    return re.test(str);
}

function isValidEmailId(str) {
    let returnValue = false;

    // check for null string
    if (str === null || str === undefined || str.length > 64) {
        return returnValue;
    }
    str = trim(str);

    // check for empty or for spaces
    if ((str === '') || (str.search(/\s/) !== -1)) {
        return returnValue;
    }
    // check for valid email pattern
    // let pEmail = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    let caseSensitiveCheck = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$/i;
    let isValidEmail = /^[_A-Za-z0-9-+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/i;
    let matches2 = str.match(isValidEmail);
    let matches1 = str.match(caseSensitiveCheck);
    if (matches2 === null || matches2[0] !== str) {
        return returnValue;
    }
    if (matches1 === null || matches1[0] !== str) {
        return returnValue;
    }
    return true;
}

function isValidCountryCode(countryCode) {

    let returnValue = false;
    let matches;

    if (countryCode === null || countryCode === undefined) {
        return returnValue;
    }

    countryCode = trim(countryCode);

    if ((countryCode === '') || (countryCode.search(/\s/) !== -1)) {
        return returnValue;
    }

    // allowing user to enter "+" charecter in other ISD code
    if (countryCode.charAt(0) === '+') {
        countryCode = countryCode.replace('+', '');
    }

    matches = countryCode.match(/^[0-9]{1,4}$/);

    if (matches === null || matches[0] !== countryCode) {
        return returnValue;
    }

    return true;
}

function isValidMobileNumber(mobile) {

    if (!isValidNumeric(mobile) || !isEmpty(mobile) || mobile.length < 8 || mobile.length > 14) {
        return false;
    }


    return true;
}

function isValidZipcode(num, numDigit) {
    let returnValue = false;

    num = trim(num);
    // check for empty or for spaces
    if ((num === '') || (num.search(/\s/) !== -1)) {
        return returnValue;
    }

    if (!isNumber(parseInt(num, 10))) {
        return returnValue;
    }

    // check for zipcode length
    if (parseInt(num.length, 10) !== numDigit) {
        return returnValue;
    } else {
        let zipPattern = /^[0-9]*$/;
        let matches = num.match(zipPattern);
        if (matches === null || (matches[0] !== num)) {
            return false;
        }
        return true;
    }
}
function isValidCurrency(currency) {
    let currencyPattern = /(?=.)^\$?(([1-9][0-9]{0,2}(,[0-9]{3})*)|[0-9]+)?(\.[0-9]{1,2})?$/;
    let matches = currency.match(currencyPattern);
    if (matches === null || (matches[0] !== currency || currency <= 0)) {
        return false;
    }
    return true;

}

function isNumber(obj) {
    return !isNaN(parseFloat(obj));
}

function isValidDate(dateStr) {
    let date = new Date(dateStr);
    // let ddmmyyyyPattern = /^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$/;
    return !isNaN(date.valueOf());
}
function isEmpty(anyStr) {
    if ((!anyStr) || (anyStr === '') || anyStr.length === 0) {
        return false;
    }
    return true;

}
function isEmptyArr(arr) {
    if (arr.length === 0) {
        return false;
    }
    return true;

}
const exposedFunctions = {
    'isValidCountryCode': isValidCountryCode,
    'isValidDate': isValidDate,
    'isValidMobileNumber': isValidMobileNumber,
    'isValidEmailId': isValidEmailId,
    'isValidPassword': isValidPassword,
    'isValidZipcode': isValidZipcode,
    'isValidString': isValidString,
    'isValidNumeric': isValidNumeric,
    'isValidAlphaNum': isValidAlphaNum,
    'isValidCurrency': isValidCurrency,
    'isEmpty': isEmpty,
    'isEmptyArr': isEmptyArr,
    'isValidFloat': isValidFloat,
    'isValidQuantity': isValidQuantity,
    'trim': trim
};

export default exposedFunctions;
