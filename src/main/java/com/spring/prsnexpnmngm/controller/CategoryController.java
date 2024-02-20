package com.spring.prsnexpnmngm.controller;

import com.spring.prsnexpnmngm.model.Category;
import com.spring.prsnexpnmngm.model.Message;
import com.spring.prsnexpnmngm.service.CategoryService;
import com.spring.prsnexpnmngm.util.CommonController;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CategoryController extends CommonController {

    private final CategoryService categoryService;

    /** 카테고리 폼 */
    @GetMapping("/category")
    public String categoryMain(Model model) {

        List<Category> list = categoryService.selectAllCategory();

        model.addAttribute("categoryList", list);
        model.addAttribute("category", new Category());

        return "category/category";
    }

    /** 카테고리 추가 */
    @PostMapping("/addCategory")
    public String addCategory(Category category, Model model) {

        Message message;

        int result = categoryService.insertCategory(category);
        if (result > 0) {
            message = new Message("카테고리가 추가 되었습니다.", "/category", RequestMethod.GET, null);
        } else {
            message = new Message("카테고리 추가가 실패하였습니다.", "/category", RequestMethod.GET, null);
        }

        return showMessageAndRedirect(message, model);
    }

    /** 카테고리 삭제 */
    @GetMapping("/delCategory/{id}")
    public String delCategory(@PathVariable("id") Long id, Model model) {

        Message message;

        int result = categoryService.deleteCategory(id);
        if (result > 0) {
            message = new Message("카테고리가 삭제 되었습니다.", "/category", RequestMethod.GET, null);
        } else {
            message = new Message("카테고리 삭제가 실패하였습니다.", "/category", RequestMethod.GET, null);
        }

        return showMessageAndRedirect(message, model);
    }

    @GetMapping("/axios/category/{categoryCd}")
    public ResponseEntity<?> selectCategory(@PathVariable("categoryCd") String categoryCd, HttpSession session) throws Exception {
        List<Category> list = categoryService.selectCategory(categoryCd);

        return ResponseEntity.ok(list);
    }
}
