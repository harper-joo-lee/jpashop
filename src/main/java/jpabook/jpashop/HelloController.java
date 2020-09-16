package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")

    //Model: model에 데이터를 넣어서 뷰로 넘길 수이가 있다.
    public String hello(Model model) {
        model.addAttribute("data", "hello");

        //return 은 화면 이름이다.
        return "hello";
    }
}
