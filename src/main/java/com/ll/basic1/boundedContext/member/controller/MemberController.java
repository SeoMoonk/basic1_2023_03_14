
package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rq.Rq;
import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;

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
    public String showLogin() {
        return "usr/member/login";
    }

    @GetMapping("/member/dologin")
    @ResponseBody
    public RsData loginV2(String username, String password){

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
            Member member = (Member) rsData.getData();
            rq.setSession("loginedMemberId", member.getId());
        }

        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout() {
        boolean cookieRemoved = rq.removeSession("loginedMemberId");

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe() {

        long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);

        boolean isLogined = loginedMemberId > 0;

        if (isLogined == false) {
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }
        Member member = memberService.findById(loginedMemberId);
        return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUser_id()));

    }

    // 디버깅용 함수
    @GetMapping("/member/session")
    @ResponseBody
    public String showSession() {
        return rq.getSessionDebugContents().replaceAll("\n", "<br>");
    }

}
