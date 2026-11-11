package com.thc.spr202602.service;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.dto.DefaultDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BoardService {
    DefaultDto.CreateResDto create(BoardDto.CreateReqDto param);
    void update(BoardDto.UpdateReqDto param);
    void delete(BoardDto.UpdateReqDto param);
    BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(BoardDto.PagedListReqDto param);
    List<BoardDto.DetailResDto> scrollList(BoardDto.ScrollListReqDto param);
}
