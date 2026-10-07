package com.thc.spr202602.service;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BoardService {
    BoardDto.CreateResDto create(BoardDto.CreateReqDto param);
    void update(Map<String, Object> param);
    void delete(Long id);
    Board detail(Long id);
    List<Board> list();
}
