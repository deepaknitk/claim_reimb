package com.coviam.reimbursement.claims.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWebRequest {

    private String userName;

    private String userEmail;

    private String employeeId;

}
