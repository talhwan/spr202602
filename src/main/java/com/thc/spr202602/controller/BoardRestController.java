package com.thc.spr202602.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/board")
@RestController // 이거 있으면 여기 있는 메서드는 모두 리스폰스바디!!!
public class BoardRestController {

    List<Map<String, Object>> list = new ArrayList<>();
    int order = 0;

    @RequestMapping("/create")
    public Map<String, Object> create(String title, String content) {
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        Map<String, Object> board = new HashMap<>();
        board.put("title", title);
        board.put("content", content);
        board.put("id", ++order);
        list.add(board);

        Map<String, Object> map = new HashMap<>();
        map.put("resultCode", 200);
        return map;
    }
    @RequestMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode", 200);
        map.put("list", list);
        return map;
    }
    @RequestMapping("/update")
    public Map<String, Object> update(int id, String title, String content) {
        Map<String, Object> board = null;
        for(Map<String, Object> each : list) {
            if(each.get("id").equals(id)) {
                //아이디 일치하는거 확인하기!
                if(title != null){each.put("title", title);}
                if(content != null){each.put("content", content);}
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode", 200);
        return map;
    }
    @RequestMapping("/delete")
    public Map<String, Object> delete(int id) {
        int temp_i = -1;
        for(int i=0;i<list.size();i++) {
            Map<String, Object> each = list.get(i);
            if(each.get("id").equals(id)) {
                // each = null; //1번 방식!
                temp_i = i; // 2번 방식!
            }
        }
        if(temp_i > -1) {
            list.remove(temp_i);// 2번 방식!
        }
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode", 200);
        return map;
    }
    @RequestMapping("/detail")
    public Map<String, Object> detail(int id) {
        Map<String, Object> board = null;
        for(Map<String, Object> each : list) {
            if(each.get("id").equals(id)) {
                board = each;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode", 200);
        map.put("data", board);
        return map;
    }
}
