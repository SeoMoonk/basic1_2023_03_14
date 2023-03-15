package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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
    public RsData loginV2(String username, String password, HttpServletResponse resp){

        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }

        //컨트롤러는 단순 명령(리모콘) 만 하고, 자잘한 일들은 Service에서 처리하도록 한다.
        RsData rsData = memberService.tryLogin(username, password);

        //1. 로그인을 시도 했을 때  성공할 경우 쿠키를 생성해서 저장해준다.
        //2. 내 정보 보기를 시도했을 경우 쿠키에 저장된 user_id를 가지고
        // memberrepository의 findbyusername 메소드를 실행시킨다.

        if(rsData.isSuccess()) {
            long memberId = (long) rsData.getData();
            resp.addCookie(new Cookie("loginedMemberId", memberId + ""));
        }

        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);
        rq.removeCookie("loginedMemberId");

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe(HttpServletRequest req) {
        long loginedMemberId = 0;

        if (req.getCookies() != null) {
            loginedMemberId = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        boolean isLogined = loginedMemberId > 0;

        if (isLogined == false)
            return RsData.of("F-1", "로그인 후 이용해주세요.");

        Member member = memberService.findById(loginedMemberId);

        return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUser_id()));

    }
}
