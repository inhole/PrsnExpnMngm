package com.spring.prsnexpnmngm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Expense {
    private long expenseId;
    private long userId;
    private long categoryId;
    private long amount;
    private String description;
    private String delYn;
    private Date expenseDt;

//    private Category category;
    private String categoryCd;
    private String categoryName;
}
