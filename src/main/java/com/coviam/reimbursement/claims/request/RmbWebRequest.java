package com.coviam.reimbursement.claims.request;

import com.coviam.reimbursement.claims.model.base.ReimbursementDto;
import com.coviam.reimbursement.claims.model.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor

public class RmbWebRequest {

    @NotNull(message = Constants.USER_ID_NULL) String userId;

    @Valid List<ReimbursementDto> rmbItemList;

}
