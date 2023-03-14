package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Controller
public class HomeController {

    private int count;

    public HomeController() {
        count = -1;
    }


    @GetMapping("/home/main")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showMain() {
        return "빌드야 되고있냐고 묻잖아 혜정아 알았으면 끄덕여";
    }

    @GetMapping("/home/main2")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showMain2() {
        return "메인2 입니다";
    }

    @GetMapping("/home/main3")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showMain3() {
        return "메인3 입니다";
    }

    int i = 0;

    @GetMapping("/home/increase")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showIncrease() {
        count ++;
        return Integer.toString(count);
    }

    @GetMapping("/home/plus")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
                           //RequestParam -> 쿼리스트링에서 a와 b의 값을 얻어옴(생략 가능)
                           //파라미터를 받기로 했으면 전부 받아야 하지만, defaultValue를 통해 받지 않을수도 있다.


        return Integer.toString(a + b);
    }


    ArrayList<Person> peoples = new ArrayList<Person>();

    int p_count = 0;

    @GetMapping("/home/addPerson")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showPerson(@RequestParam String name, @RequestParam String age) {

        peoples.add(new Person(name, age));

        p_count ++;

        return Integer.toString(p_count) + " 번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/people")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public ArrayList<Person> showPeoples() {

        return peoples;
    }

}


