package com.thc.spr202602.service.impl;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.dto.DefaultDto;
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
        // 업데이트에게 부탁해야지! 지워달라고! => deleted를 true로!!
        update(BoardDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
        /*Long id = param.getId();
        Board board = boardRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("no data"));
        board.setDeleted(true);
        boardRepository.save(board);*/
    }
    @Override
    public BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        Board board = boardRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("no data"));
        BoardDto.DetailResDto res = BoardDto.DetailResDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .deleted(board.getDeleted())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .build();

        if(res.getDeleted()){
            res.setTitle("삭제된 게시글입니다!");
            res.setContent(null);
            res.setAuthor(null);
        }
        return res;
    }
    @Override
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param) {

        System.out.println("title : " + param.getTitle());
        List<BoardDto.DetailResDto> list = new ArrayList<>();
        List<Board> boards = boardRepository.findAll();
        for (Board each : boards) {
            boolean needToAdd = true;
            // each를 통해서 비교해볼까?
            if(param.getDeleted() != null && !param.getDeleted().equals(each.getDeleted()) ){ needToAdd = false; }
            String title = param.getTitle();
            if(title != null && !title.isEmpty() && !each.getTitle().contains(title)){ needToAdd = false; }
            if(param.getAuthor() != null && !param.getAuthor().equals(each.getAuthor()) ){ needToAdd = false; }
            if(needToAdd){
                list.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
            }
        }
        return list;
    }
}
