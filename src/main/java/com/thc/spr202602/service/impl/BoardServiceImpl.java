package com.thc.spr202602.service.impl;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.repository.BoardRepository;
import com.thc.spr202602.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    final BoardRepository boardRepository;

    @Override
    public BoardDto.CreateResDto create(BoardDto.CreateReqDto param) {
        /*String title = (String) param.getTitle();
        String content = (String) param.getContent();
        String author = (String) param.getAuthor();
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);
        board.setDeleted(false);
        board = boardRepository.save(board);
        BoardDto.CreateResDto createResDto = BoardDto.CreateResDto.builder().id(board.getId()).build();
        return createResDto;*/

        // Board board = Board.of(param.getTitle(),  param.getContent(), param.getAuthor());

        /*Board board = param.toEntity();
        board = boardRepository.save(board);
        BoardDto.CreateResDto createResDto = board.toCreateResDto();
        return createResDto;*/

        return boardRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(Map<String, Object> param) {
        Boolean deleted = (Boolean) param.get("deleted");
        String title = (String) param.get("title");
        String content = (String) param.get("content");
        String author = (String) param.get("author");
        Long id = Long.parseLong(param.get("id") + "");

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no data"));
        if(deleted  != null) { board.setDeleted(deleted); }
        if(title  != null) { board.setTitle(title); }
        if(content != null) {  board.setContent(content); }
        if(author != null) { board.setAuthor(author); }
        boardRepository.save(board);
    }
    @Override
    public void delete(Long id) {
        /*Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no data"));

        boardRepository.delete(board);*/
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("deleted", true);
        update(param);
    }
    @Override
    public Board detail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no data"));
        if(board.getDeleted()) {
            board.setTitle("삭제된 게시글 입니다.");
            board.setContent(null);
        }
        return board;
    }
    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }
}
