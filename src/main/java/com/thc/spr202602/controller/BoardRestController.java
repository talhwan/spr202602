package com.thc.spr202602.controller;

import com.thc.spr202602.domain.Board;
import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.dto.DefaultDto;
import com.thc.spr202602.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody BoardDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody BoardDto.UpdateReqDto param){
        boardService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody BoardDto.UpdateReqDto param){
        boardService.delete(param);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<BoardDto.DetailResDto> get(DefaultDto.DetailReqDto param){
        return ResponseEntity.ok(boardService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto.DetailResDto>> list(BoardDto.ListReqDto param){
        return ResponseEntity.ok(boardService.list(param));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(BoardDto.PagedListReqDto param){
        return ResponseEntity.ok(boardService.pagedList(param));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<BoardDto.DetailResDto>> scrollList(BoardDto.ScrollListReqDto param){
        return ResponseEntity.ok(boardService.scrollList(param));
    }

}
