package com.thc.spr202602.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {
    @RequestMapping("/page1")
    public String page1(){
        System.out.println("page1!!!!");
        return "page1"; // 여기 스트링 값에 해당하는 ~~.html 파일을 찾아감!!
        // resources/templates 이 아래에 있어요!!
    }
    @RequestMapping("/add")
    public String add(@RequestParam int a, @RequestParam int b, Model model){
        System.out.println("add!!!! : " + a + "//" + b);
        int sum = 0;
        sum = a + b;
        System.out.println("sum : " + sum);
        model.addAttribute("sum", sum);
        model.addAttribute("a", a);
        model.addAttribute("b", b);

        Map<String, Object> map = new HashMap<>();
        map.put("a", a);
        map.put("b", b);
        map.put("sum", sum);
        model.addAttribute("map", map);
        return "add";
    }

    @RequestMapping("/addString")
    public String addString(String a, String b, Model model){
        System.out.println("addString!!!! : " + a + "//" + b);
        String c = a + b;
        model.addAttribute("c", c);
        return "string";
    }

    //Rest Controller
    @ResponseBody //이거를 붙이면 REST CTRL 이 되는거!! 페이지 이동 없음!!
    @RequestMapping("/add2")
    public Map<String, Object> add2(int a, int b){
        Map<String, Object> map = new HashMap<>();
        int sum = a + b;
        map.put("sum", sum);
        map.put("a", a);
        map.put("b", b);
        return map;
    }

    @ResponseBody
    @RequestMapping("/multiple")
    public Map<String, Object> multiple(@RequestParam int a, @RequestParam int b){
        Map<String, Object> map = new HashMap<>();
        int result = a * b;
        map.put("result", result);
        return map;
    }
}
