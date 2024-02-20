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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("startDate", "20240217");
        model.addAttribute("endDate", "20240217");
        return "main/viewMain";
    }

    @PostMapping("/axios/viewMain")
        public ResponseEntity<?> selectExpenseList(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
        map.put("userId", getUserInfo().get("userId"));

        log.debug(map.toString());

        List<Expense> list = mainService.selectExpenseList(map);
        return ResponseEntity.ok(list);
    }
}
