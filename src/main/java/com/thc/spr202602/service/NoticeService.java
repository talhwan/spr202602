package com.thc.spr202602.service;

import com.thc.spr202602.domain.Notice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NoticeService {
    long create(Map<String, Object> param);
    List<Notice> list();
}
