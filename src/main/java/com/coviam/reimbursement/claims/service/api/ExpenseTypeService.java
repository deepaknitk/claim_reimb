package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.ExpenseType;

import java.util.List;

public interface ExpenseTypeService {

    ExpenseType save(ExpenseType expenseType);

    ExpenseType findByExpenseTypeCode(String expenseTypeCode);

    List<ExpenseType> findAll();
}
