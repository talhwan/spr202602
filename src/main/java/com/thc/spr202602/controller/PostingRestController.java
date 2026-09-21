package com.thc.spr202602.controller;

import com.thc.spr202602.DataPosting;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/posting")
@RestController
public class PostingRestController {

    List<Map<String, Object>> list = new ArrayList<>();
    int id = 0;

    @PostMapping("/create")
    public Map<String, Object> create(
            // @RequestParam String title, @RequestParam String content //하나하나 보낼때!!
        // @RequestParam Map<String, Object> param // 맵으로 보내보기!
            @RequestBody DataPosting param
    ) {
        /*
        1번째 방식
        Map<String, Object> posting = new HashMap<>();
        posting.put("title", title);
        posting.put("content", content);
        posting.put("id", ++id);
        list.add(posting);

        System.out.println(title + "//" + content);
        */

        /*
        Map<String, Object> posting = new HashMap<>();
        posting.put("title", param.get("title"));
        posting.put("content", param.get("content"));
        posting.put("id", ++id);
        System.out.println(posting.get("title") + "//" + posting.get("content"));
        list.add(posting);
*/

        Map<String, Object> posting = new HashMap<>();
        posting.put("title", param.getTitle());
        posting.put("content", param.getContent());
        posting.put("id", ++id);
        System.out.println(param.getTitle() + "//" + param.getContent());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);

        return resultMap;
    }

    @PutMapping("/update")
    public void update(@RequestBody DataPosting param) {
        Integer id = param.getId();
        for(Map<String, Object> map : list) {
            if(id.equals(map.get("id"))) {
                map.put("title", param.getTitle());
                map.put("content", param.getContent());
            }
        }
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody DataPosting param) {
        Integer id = param.getId();
        for(Map<String, Object> map : list) {
            if(id.equals(map.get("id"))) {
                map = null;
            }
        }
    }

    @GetMapping("/detail")
    public Map<String, Object> detail(Integer id) {
        for(Map<String, Object> map : list) {
            if(id.equals(map.get("id"))) {
                return map;
            }
        }
        return null;
    }

    @GetMapping("/list")
    public List<Map<String, Object>> list() {
        return list;
    }

}
