package com.ll.basic1;

import lombok.Getter;

public class Person {

    @Getter
    String name;
    @Getter
    String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
