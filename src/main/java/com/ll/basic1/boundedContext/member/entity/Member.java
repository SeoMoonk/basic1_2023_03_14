package com.ll.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private static long count;

    private final long id;
    private final String user_id;
    private final String user_pwd;

    static {
        count = 1;
    }

    public Member(String user_id, String user_pwd) {
        this.id = count++;
        this.user_id = user_id;
        this.user_pwd = user_pwd;
    }


}
