package com.spring.prsnexpnmngm.repository;

import com.spring.prsnexpnmngm.model.Expense;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MainMapper {

    @Insert(
        "INSERT INTO EXPENSE (" +
            "USER_ID" +
            ", CATEGORY_ID" +
            ", AMOUNT" +
            ", DESCRIPTION" +
        ") VALUES (" +
            "${userId}" +
            ", ${categoryId}" +
            ", '${amount}'" +
            ", '${description}'" +
        ")"
    )
    int insertExpense(Expense expense);

    @Select(
        "SELECT A.*, B.CATEGORY_CD, B.CATEGORY_NAME " +
        "FROM EXPENSE A, CATEGORY B " +
        "WHERE B.CATEGORY_ID = A.CATEGORY_ID " +
        "AND A.USER_ID = ${userId} " +
        "AND A.EXPENSE_DT BETWEEN '${start}' AND '${end}' " +
        "ORDER BY EXPENSE_DT"
    )
    List<Expense> selectExpenseList(Map<String, Object> map);
}
