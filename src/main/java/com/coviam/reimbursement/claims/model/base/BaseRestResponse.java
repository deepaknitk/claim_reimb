package com.coviam.reimbursement.claims.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Foram Shah on 22/06/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseRestResponse<T> implements Serializable {

    private static final long serialVersionUID = -2202435222425148959L;

    private String errorMessage;
    private String errorCode;
    private boolean success;
    private T data;
    private Paging paging;

    public BaseRestResponse(boolean success) {
        this.success = success;
    }

    public BaseRestResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public BaseRestResponse(boolean success, T data, Paging paging) {
        this.success = success;
        this.data = data;
        this.paging = paging;
    }

    public BaseRestResponse(String errorCode, String errorMessage, boolean success, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.success = success;
        this.data = data;
    }

    public BaseRestResponse(String errorCode, String errorMessage, boolean success) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.success = success;
    }
}
