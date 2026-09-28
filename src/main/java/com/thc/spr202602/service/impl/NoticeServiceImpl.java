package com.thc.spr202602.service.impl;

import com.thc.spr202602.domain.Notice;
import com.thc.spr202602.repository.NoticeRepository;
import com.thc.spr202602.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
/*

    List<Map<String, Object>> noticeList = new ArrayList<>();
    int order = 0;
*/

    final NoticeRepository noticeRepository;
    //int order = 0;

    @Override
    public long create(Map<String, Object> param) {
        /*
        int id = ++order;
        param.put("id", id);
        noticeList.add(param);*/

        String title = param.get("title") + "";
        String content = param.get("content") + "";
        String author = param.get("author") + "";

        Notice notice = new Notice();
        //notice.setId((long) ++order);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setAuthor(author);

        notice = noticeRepository.save(notice);

        return notice.getId();
    }

    @Override
    public List<Notice> list() {
        return noticeRepository.findAll();
    }
}
