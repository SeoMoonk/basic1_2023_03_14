package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @GetMapping("/home/main")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showMain() {
        return "Hello World!";
    }
}
