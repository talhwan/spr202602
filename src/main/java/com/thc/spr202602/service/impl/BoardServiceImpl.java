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
    public List<BoardDto.DetailResDto> detailList(List<BoardDto.DetailResDto> list) {
        List<BoardDto.DetailResDto> newlist = new ArrayList<>();
        for(BoardDto.DetailResDto each : list){
            newlist.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newlist;
    }
    @Override
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param) {
        return detailList(boardMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(BoardDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(boardMapper.listCount(param));
        res.setList(detailList(boardMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<BoardDto.DetailResDto> scrollList(BoardDto.ScrollListReqDto param) {
        int perpage = 10;
        if(param.getPerpage() != null){
            perpage = param.getPerpage();
        }
        param.setPerpage(perpage);

        if(param.getOrderby() != null && !param.getOrderby().equals("id")){
            if(param.getCursor() != null){
                Board board = boardRepository.findById(param.getCursor()).orElseThrow(() -> new RuntimeException("no data"));
                String mark = board.getTitle() + "_" + board.getId();
                param.setMark(mark);
            }
        }

        return detailList(boardMapper.scrollList(param));
    }
}
