package com.spring.prsnexpnmngm.service;

import com.spring.prsnexpnmngm.model.Expense;
import com.spring.prsnexpnmngm.repository.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseMapper expenseMapper;

    public int insertExpense(Expense expense) {
        return expenseMapper.insertExpense(expense);
    }

    public List<Expense> selectExpenseList(Map<String, Object> map) {
        return expenseMapper.selectExpenseList(map);
    }

    public List<Expense> selectExpenseListView(Map<String, Object> map) {
        return expenseMapper.selectExpenseListView(map);
    }
}
