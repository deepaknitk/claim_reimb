package com.coviam.reimbursement.claims.utils;

import java.util.regex.Pattern;

public class ClaimUtilities {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX_FIRST =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX_SECOND =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email){
        return VALID_EMAIL_ADDRESS_REGEX_FIRST.matcher(email).matches() && VALID_EMAIL_ADDRESS_REGEX_SECOND.matcher(email).matches();
    }
}
