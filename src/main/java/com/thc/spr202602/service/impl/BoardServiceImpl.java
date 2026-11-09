package com.thc.spr202602.service.impl;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.dto.DefaultDto;
import com.thc.spr202602.mapper.BoardMapper;
import com.thc.spr202602.repository.BoardRepository;
import com.thc.spr202602.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;

    @Override
    public DefaultDto.CreateResDto create(BoardDto.CreateReqDto param) {
        return boardRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(BoardDto.UpdateReqDto param) {
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        board.update(param);
        boardRepository.save(board);
    }
    @Override
    public void delete(BoardDto.UpdateReqDto param) {
        update(BoardDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }
    @Override
    public BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        BoardDto.DetailResDto res = boardMapper.detail(param.getId());
        return res;
    }
    @Override
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param) {
        List<BoardDto.DetailResDto> newlist = new ArrayList<>();
        List<BoardDto.DetailResDto> list = boardMapper.list(param);
        for(BoardDto.DetailResDto each : list){
            newlist.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newlist;
    }
    @Override
    public BoardDto.PagedListResDto pagedList(BoardDto.PagedListReqDto param) {
        int perpage = 10;
        if(param.getPerpage() != null){
            perpage = param.getPerpage();
        }
        if(perpage <= 0){
            perpage = 10;
        }
        Integer listsize = boardMapper.listCount(param); //총 등록된 글 수..
        Integer pagesize = listsize / perpage; // 12 / 5 => 2?!
        if(listsize % perpage > 0){
            pagesize++;
        }
        int callpage = 1;
        if(param.getCallpage() != null){
            callpage = param.getCallpage();
        }
        if(callpage <= 0){
            callpage = 1;
        }
        if(callpage > pagesize){
            callpage = pagesize;
        }
        int offset = (callpage - 1) * perpage;
        param.setOffset(offset);
        param.setPerpage(perpage);

        String orderby = "id";
        if(param.getOrderby() != null){
            orderby = param.getOrderby();
        }
        String orderway = "DESC";
        if(param.getOrderway() != null){
            orderway = param.getOrderway();
        }


        List<BoardDto.DetailResDto> newlist = new ArrayList<>();
        List<BoardDto.DetailResDto> list = boardMapper.pagedList(param);
        for(BoardDto.DetailResDto each : list){
            newlist.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }

        BoardDto.PagedListResDto pagedlist = BoardDto.PagedListResDto.builder()
                .callpage(callpage)
                .perpage(perpage)
                .listsize(listsize)
                .pagesize(pagesize)
                .list(newlist)
                .build();
        return pagedlist;
    }
}
