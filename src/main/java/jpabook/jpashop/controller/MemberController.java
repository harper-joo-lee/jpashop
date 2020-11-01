package jpabook.jpashop.controller;

import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memeberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        // 빈 화면일 수 있으니 밸리데이션 체크를 해준다.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

}
