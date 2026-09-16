package com.thc.spr202602.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"", "/"})
    public String empty() {
        return "index";
    }
    @RequestMapping("/index")
    public String index(){
        return "redirect:/";
    }
    @RequestMapping("/param/{abc}")
    public String param(@PathVariable String abc){
        System.out.println(abc);
        return "param";
    }
}
