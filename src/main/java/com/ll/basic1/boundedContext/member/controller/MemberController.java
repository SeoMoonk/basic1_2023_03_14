package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    @GetMapping("/member/login_1")
    @ResponseBody
    public String login(String username, String password){

//        Map<String, Object> reData = new LinkedHashMap<>(){{
//            put("resultCode", "S-1");
//            put("msg", "%s 님 환영합니다.".formatted(username));
//        }};

        return "구식 로그인";
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData loginV2(String username, String password){

        return memberService.tryLogin(username, password);
    }
}
