package com.spring.prsnexpnmngm.controller;

import com.spring.prsnexpnmngm.model.Expense;
import com.spring.prsnexpnmngm.model.Message;
import com.spring.prsnexpnmngm.service.ExpenseService;
import com.spring.prsnexpnmngm.util.CommonController;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/expense")
public class ExpenseController extends CommonController {

    private final ExpenseService expenseService;

    /** 개인 지출 추가 폼 */
    @GetMapping("/add")
    public String expense(Model model) {
        model.addAttribute("expense", new Expense());
        return "expense/expenseAdd";
    }

    /** 개인 지출 추가 */
    @PostMapping("/add")
    public String addExpense(Expense expense, Model model) {
        Message message;

        expense.setUserId( (Long) getUserInfo().get("userId") );
        int result = expenseService.insertExpense(expense);
        if (result > 0) {
            message = new Message("지출 내역이 추가 되었습니다.", "/expense/add", RequestMethod.GET, null);
        } else {
            message = new Message("지출 내역 추가가 실패 되었습니다.", "/expense/add", RequestMethod.GET, null);
        }

        return showMessageAndRedirect(message, model);
    }

    /** 개인 지출 캘린더 */
    @GetMapping("/view")
    public String viewExpense(Model model) {
        return "expense/expenseView";
    }

    /** 개인 지출 캘린더 조회 */
    @PostMapping("/axios/list")
        public ResponseEntity<?> selectExpenseList(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
        map.put("userId", getUserInfo().get("userId"));

        log.debug(map.toString());

        List<Expense> list = expenseService.selectExpenseList(map);
        return ResponseEntity.ok(list);
    }

    /** 지출 통계 상세 */
    @GetMapping("/axios/list/view/{dateStr}")
    public ResponseEntity<?> selectExpenseListView(@PathVariable("dateStr") String dateStr, HttpSession session) throws Exception {
        log.info(dateStr);
        Map<String, Object> map = new HashMap<>();
        map.put("dateStr", dateStr);
        map.put("userId", getUserInfo().get("userId"));
        List<Expense> list = expenseService.selectExpenseListView(map);

        return ResponseEntity.ok(list);
    }
}
