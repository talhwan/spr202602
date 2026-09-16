package com.thc.spr202602.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
