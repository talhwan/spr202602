package com.thc.spr202602;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // 컨트롤러 라는 것을 꼭 스프링에 알려줘야 함! 어노테이션으로~!!
public class TestController {

    @ResponseBody // 이건 일단 무시! 응답을 바로 받는 너낌인데 지금은 몰라도 괜찮음!
    @RequestMapping("/test1") //이 메서드로 찾아올 주소!! 무조건 unique!!
    public String test1(){

        // 변수
        int a = 0; // 정수 //Integer
        double b = 0; // Double
        boolean c = false; // Boolean
        String str = "";

        // 반복문!
        int sum = 0;
        for(int i=0;i<10;i++){
            sum += i;
        }
        sum++;
        ++sum;
        int[] array_int = new int[10];
        int[] aaa = {1,2,3};
        int sum2 = 0;
        for(int i=0;i<aaa.length;i++){
            int each = aaa[i];
            sum2 += each;
        }
        for(int each : aaa){
            sum2 += each;
        }

        //조건문!!
        if(sum==sum2){
            System.out.println(sum);
        } else {
        }
        switch(sum){
            case 1: {
            } break;
            default: break;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("aaa",aaa);
        map.get("aaa1");
        map.remove("aaa");

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.get(0);
        list.remove(0);

        return "테스트입니다!";
    }

}
