package com.thc.spr202602.controller;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    final BoardService boardService;

    @PostMapping("")
    public BoardDto.CreateResDto create(@RequestBody BoardDto.CreateReqDto param){
        BoardDto.CreateResDto resDto = boardService.create(param);
        return resDto;
    }
    @PutMapping("")
    public void update(@RequestBody Map<String, Object> param){
        boardService.update(param);
    }
    @DeleteMapping("")
    public void delete(@RequestBody Long id){
        boardService.delete(id);
    }
    @GetMapping("")
    public Board get(@RequestParam Long id){
        return boardService.detail(id);
    }
    @GetMapping("/list")
    public List<Board> list(){
        return boardService.list();
    }

}
