package com.thc.spr202602.service.impl;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.repository.BoardRepository;
import com.thc.spr202602.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    final BoardRepository boardRepository;

    @Override
    public Long create(Map<String, Object> param) {
        String title = (String) param.get("title");
        String content = (String) param.get("content");
        String author = (String) param.get("author");
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);
        board = boardRepository.save(board);
        return board.getId();
    }
    @Override
    public void update(Map<String, Object> param) {
        String title = (String) param.get("title");
        String content = (String) param.get("content");
        String author = (String) param.get("author");
        Long id = Long.parseLong(param.get("id") + "");

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no data"));
        if(title  != null) { board.setTitle(title); }
        if(content != null) {  board.setContent(content); }
        if(author != null) { board.setAuthor(author); }
        boardRepository.save(board);
    }
    @Override
    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no data"));
        boardRepository.delete(board);
    }
    @Override
    public Board detail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no data"));
        return board;
    }
    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }
}
