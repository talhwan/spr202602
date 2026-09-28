package com.thc.spr202602.controller;

import com.thc.spr202602.domain.Notice;
import com.thc.spr202602.service.NoticeService;
import com.thc.spr202602.service.impl.NoticeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor //3번 방법
@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {

    // @Autowired 1번 방법
    final private NoticeService noticeService;
    /*
    public NoticeRestController(NoticeService noticeService) {
        this.noticeService = noticeService;
    } //생성자 방식이 2번방법!
    */

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, Object> param) {

        long id = noticeService.create(param);

        Map<String, Object> result_map = new HashMap<>();
        result_map.put("status","success");
        result_map.put("id",id);
        return result_map;
    }

    @GetMapping("/list")
    public List<Notice> list() {
        return noticeService.list();
    }
}
