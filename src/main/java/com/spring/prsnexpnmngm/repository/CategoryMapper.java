package com.spring.prsnexpnmngm.repository;

import com.spring.prsnexpnmngm.model.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM CATEGORY WHERE 1=1")
    List<Category> selectAllCategory();

    @Select("SELECT * FROM CATEGORY WHERE 1=1 AND CATEGORY_CD = '${categoryCd}'")
    List<Category> selectCategory(String categoryCd);

    @Insert(
        "INSERT INTO CATEGORY (" +
            "CATEGORY_NAME" +
            ", CATEGORY_CD" +
        ") VALUES (" +
            "'${categoryName}'" +
            ", '${categoryCd}'" +
        ")"
    )
    int insertCategory(Category category);

    @Delete("DELETE FROM CATEGORY WHERE CATEGORY_ID = ${categoryId}")
    int deleteCategory(Long category);
}
