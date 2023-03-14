package com.ll.basic1.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {

    private static int lastId;

    private int id;
    private int age;
    private String name;


    static {
        lastId = 0;
    }

    Person(int age, String name) {
        this(++lastId, age, name);
    }

}
