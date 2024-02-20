package com.spring.prsnexpnmngm.service;

import com.spring.prsnexpnmngm.model.Expense;
import com.spring.prsnexpnmngm.repository.MainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MainService {

    private final MainMapper mainMapper;

    public int insertExpense(Expense expense) {
        return mainMapper.insertExpense(expense);
    }

    public List<Expense> selectExpenseList(Map<String, Object> map) {
        return mainMapper.selectExpenseList(map);
    }
}
