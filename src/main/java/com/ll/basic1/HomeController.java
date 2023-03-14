package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

@Controller
public class HomeController {

    private int count;

    public HomeController() {
        count = -1;
    }


    @GetMapping("/home/main")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String showMain() {
        return "혜정아 빌드 되고있냐고 묻잖아 알았으면 끄덕여";
    }

    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean showReturnBoolean() {
        return true;
    }

    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double showReturnDouble() {
        return Math.PI;
    }

    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> showReturnIntList() {
        List<Integer> list = new ArrayList<>() {{
            add(10);
            add(20);
            add(30);
        }};

        return list;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        return map;
    }

    @GetMapping("/home/returnCarV2")
    @ResponseBody
    public CarV2 showReturnCarV2() {
        CarV2 car = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }

    @GetMapping("/home/returnCarMapList")
    @ResponseBody
    public List<Map<String, Object>> showReturnCarMapList() {
        Map<String, Object> carMap1 = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        Map<String, Object> carMap2 = new LinkedHashMap<>() {{
            put("id", 2);
            put("speed", 200);
            put("name", "포르쉐");
            put("relatedIds", new ArrayList<>() {{
                add(5);
                add(6);
                add(7);
            }});
        }};

        List<Map<String, Object>> list = new ArrayList<>();

        list.add(carMap1);
        list.add(carMap2);

        return list;
    }

    @GetMapping("/home/returnCarList")
    @ResponseBody
    public List<CarV2> showReturnCarList() {
        CarV2 car1 = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        CarV2 car2 = new CarV2(2, 200, "포르쉐", new ArrayList<>() {{
            add(5);
            add(6);
            add(7);
        }});

        List<CarV2> list = new ArrayList<>();

        list.add(car1);
        list.add(car2);

        return list;
    }


    @GetMapping("/home/returnIntArray")
    @ResponseBody
    public int[] showReturnIntArray() {
        int[] arr = new int[]{10, 20, 30};

        return arr;
    }

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

