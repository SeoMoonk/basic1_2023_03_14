package com.ll.basic1.boundedContext.home.Controller;

import com.ll.basic1.Util.CarV2;
import com.ll.basic1.Util.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {

    private int count;

    public HomeController() {
        count = -1;
    }

    List<Person> peoples = new ArrayList<Person>();

    int p_count = 1;


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


    @GetMapping("/home/addPerson")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String addPerson(@RequestParam String name, @RequestParam int age) {

        peoples.add(new Person(p_count, age, name));

        return Integer.toString(p_count++) + " 번 사람이 추가되었습니다.";
    }

    //리스트를 출력한 경우
    @GetMapping("/home/people")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public List<Person> showPeoples() {
        return peoples;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam int id)
    {

        // person -> person.getId() == id
        // 위 함수가 참인 엘리먼트(요소) 가 존재한다면, 해당 요소를 삭제한다.
        //removed 에는 삭제 수행 여부(T/F)가 저장된다.

        //peoples.remove(new Person(id, age, name));

        if(peoples.removeIf(person -> person.getId() == id))
        {
            return "id : " + Integer.toString(id) + "번 사용자가 삭제되었습니다.";
        }
        else
        {
            return "삭제할 id가 유효하지 않습니다.";
        }

        //스트림 안쓰는 경우(유효 O)
//        for ( Person p : people ) {
//            if ( p.getId() == id ) people.remove(p);
//        }
    }

    @GetMapping("/home/modifyPerson")       //이러한 요청이 오면
    @ResponseBody                   //아래의 매소드를 실행한 후, 그 리턴값을 응답으로 처리해줘
    public String modifyPerson(int id, String name, int age) {

        Person found = peoples
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);


        if(found == null)
        {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        found.setName(name);
        found.setAge(age);

        return "%d 번 사람이 수정되었습니다.".formatted(id);
    }

    // 이 함수와 아래 showReqAndRespV2 함수는 똑같이 작동한다.
    @GetMapping("/home/reqAndResp")
    @ResponseBody
    public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int age = Integer.parseInt(req.getParameter("age").replaceAll(" ", ""));
        resp.getWriter().append("Hello, you're %d years old.".formatted(age));
    }

    // 이 방식이 가능한 이유는 스프링부트가 배후에서 처리를 해주기 때문이다.(이 방식이 코딩하기 더 편하다.)
    @GetMapping("/home/reqAndRespV2")
    @ResponseBody
    public String showReqAndRespV2(int age) {
        return "Hello, you're %d years old.".formatted(age);
    }


    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 리턴되는 int 값은 String 화 되어서 고객(브라우저)에게 전달된다.

        int countInCookie = 0;

        //고객이 가져온 쿠폰에서 count 쿠폰을 찾고, 그 쿠폰의 값을 가져온다.
        //전의 쿠키 값이 없다면? -> 0에서 1을 더한 새로운 쿠키를 넣어 반환해준다.
        if(req.getCookies() != null)
        {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))     //쿠키들 중에 count 라는 친구가 있으면
                    .map(Cookie::getValue)                                  //그 쿠기의 값을 얻어 오고
                    .mapToInt(Integer::parseInt)                            //int형으로 변환해준다.
                    .findFirst()                                            //finrFirse() -> filter 조건에 일치하는 element 1개를 Optional로 리턴.
                    .orElse(0);                                      //조건에 일치하는게 없다면 0 반환.
        }

        int newCountInCookie = countInCookie +1;

        //고객이 가져온 count 쿠폰 값에 1을 더한 쿠폰을 만들어서 고객에게 보낸다.
        //쉽게 말하면 브라우저(고객)에 저장되어 있는 count 쿠폰의 값을 1 증가시킨다.
        //일렇게 브라우저의 쿠키값을 변경하면 재방문시에 스프링부트가 다시 그 값을 받게 되어 있다.
        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        //응답하는 내용
        return newCountInCookie;
    }

}

