package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.repository.ExpenseTypeRepository;
import com.coviam.reimbursement.claims.service.api.ExpenseTypeService;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    @Override
    public ExpenseType save(ExpenseType expenseType){
        CommonUtils.checkError(expenseType != null, Error.EXPENSE_TYPE_NULL);
        boolean expenseTypeCodeExists = expenseTypeRepository.findByExpenseTypeCode(expenseType.getExpenseTypeCode());
        CommonUtils.checkError(expenseTypeCodeExists != false, Error.EXPENSE_TYPE_CODE_INVALID);
        ExpenseType result = expenseTypeRepository.save(expenseType);
        CommonUtils.checkError(result != null, Error.EXPENSE_TYPE_NOT_SAVED);
        return expenseType;
    }

    @Override
    public ExpenseType findByExpenseTypeCode(String expenseTypeCode){
        log.debug("findExpenseType with code: {} ",expenseTypeCode);
        CommonUtils.checkError(expenseTypeCode != null,Error.EXPENSE_TYPE_CODE_NULL );
        ExpenseType expenseType = expenseTypeRepository.findByExpenseTypeCodeAndMarkForDeleteFalse(expenseTypeCode);
        CommonUtils.checkError(expenseType != null,Error.EXPENSE_TYPE_NOT_FOUND);

        return expenseType;
    }

    @Override
    public List<ExpenseType> findAll(){
        List<ExpenseType> expenseTypes = expenseTypeRepository.findByMarkForDeleteFalse();
        CommonUtils.checkError(expenseTypes.size() != 0,Error.EXPENSE_TYPE_NOT_FOUND);
        return expenseTypes;
    }
}
