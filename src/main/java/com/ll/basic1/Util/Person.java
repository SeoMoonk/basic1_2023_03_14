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

    private final int id;
    private final int age;
    private final String name;


    static {
        lastId = 0;
    }

    Person(int age, String name) {
        this(++lastId, age, name);
    }

}
