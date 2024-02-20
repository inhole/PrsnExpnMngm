package com.spring.prsnexpnmngm.service;

import com.spring.prsnexpnmngm.model.Category;
import com.spring.prsnexpnmngm.repository.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> selectAllCategory() {
        return categoryMapper.selectAllCategory();
    }

    public List<Category> selectCategory(String categoryCd) {
        return categoryMapper.selectCategory(categoryCd);
    }

    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    public int deleteCategory(Long id) {
        return categoryMapper.deleteCategory(id);
    }
}
