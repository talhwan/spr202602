package com.thc.spr202602.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/posting")
@Controller
public class PostingController {
    /*
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(){
        return "posting/create";
    }
    @GetMapping("/detail") //리퀘스트매핑에 메서드 지정한 것과 동일!!
    public String detail(){
        return "posting/detail";
    }
    */
    @GetMapping("/{page}")
    public String page(@PathVariable String page) {
        return "/posting/" + page;
    }
}
