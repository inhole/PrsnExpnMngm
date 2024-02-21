package com.spring.prsnexpnmngm.controller;

import com.spring.prsnexpnmngm.model.Expense;
import com.spring.prsnexpnmngm.model.Message;
import com.spring.prsnexpnmngm.service.MainService;
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
public class MainController extends CommonController {

    private final MainService mainService;

    /** 메인 폼 */
    @GetMapping("/main")
    public String mainHome(Model model) {
        model.addAttribute("expense", new Expense());
        return "main/main";
    }

    /** 지출 추가 */
    @PostMapping("/addExpense")
    public String addExpense(Expense expense, Model model) {
        Message message;

        expense.setUserId( (Long) getUserInfo().get("userId") );
        int result = mainService.insertExpense(expense);
        if (result > 0) {
            message = new Message("지출 내역이 추가 되었습니다.", "/main", RequestMethod.GET, null);
        } else {
            message = new Message("지출 내역 추가가 실패 되었습니다.", "/main", RequestMethod.GET, null);
        }

        return showMessageAndRedirect(message, model);
    }

    /** 지출 통계 폼 */
    @GetMapping("/viewMain")
    public String viewMain(Model model) {
        return "main/viewMain";
    }

    /** 지출 통계 */
    @PostMapping("/axios/viewMain")
        public ResponseEntity<?> selectExpenseList(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
        map.put("userId", getUserInfo().get("userId"));

        log.debug(map.toString());

        List<Expense> list = mainService.selectExpenseList(map);
        return ResponseEntity.ok(list);
    }

    /** 지출 통계 상세 */
    @GetMapping("/axios/viewMain/{dateStr}")
    public ResponseEntity<?> selectExpenseViewList(@PathVariable("dateStr") String dateStr, HttpSession session) throws Exception {
        log.info(dateStr);
        Map<String, Object> map = new HashMap<>();
        map.put("dateStr", dateStr);
        map.put("userId", getUserInfo().get("userId"));
        List<Expense> list = mainService.selectExpenseViewList(map);

        return ResponseEntity.ok(list);
    }
}
