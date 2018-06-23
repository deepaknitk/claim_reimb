package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {

    boolean findByExpenseTypeCode(String expenseTypeCode);

    ExpenseType findByExpenseTypeCodeAndMarkForDeleteFalse(String expenseTypeCode);

    List<ExpenseType> findByMarkForDeleteFalse();

}
