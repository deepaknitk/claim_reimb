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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paging implements Serializable {

    private Integer page;
    private Integer totalPage;
    private Integer itemPerPage;
    private Long totalItem;

}

