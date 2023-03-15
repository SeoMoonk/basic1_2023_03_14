package com.ll.basic1.boundedContext.member.service;


import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Component : 아래 클래스의 객체는 Ioc 컨테이너에 의해 생사소멸 관리된다.
// @Service : @Component 와 같은 의미, 가독성 때문에 이렇게 표기
@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService() {
        memberRepository = new MemberRepository();
    }

    public RsData tryLogin(String username, String password) {

        Member member = memberRepository.findByUsername(username);



        if (member == null) {
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if (!member.getUser_pwd().equals(password)) {
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        }


        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username));
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

}