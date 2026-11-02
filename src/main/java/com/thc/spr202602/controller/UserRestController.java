package com.thc.spr202602.controller;

import com.thc.spr202602.dto.UserDto;
import com.thc.spr202602.dto.DefaultDto;
import com.thc.spr202602.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserRestController {

    final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<DefaultDto.CreateResDto> login(@RequestBody UserDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(param));
    }
    @GetMapping("/username")
    public ResponseEntity<UserDto.UsernameResDto> username(UserDto.CreateReqDto param){
        return ResponseEntity.ok(userService.username(param));
    }
    @PostMapping("/signup")
    public ResponseEntity<DefaultDto.CreateResDto> signup(@RequestBody UserDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(param));
    }

    /**/

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody UserDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody UserDto.UpdateReqDto param){
        userService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody UserDto.UpdateReqDto param){
        userService.delete(param);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<UserDto.DetailResDto> get(DefaultDto.DetailReqDto param){
        return ResponseEntity.ok(userService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<UserDto.DetailResDto>> list(UserDto.ListReqDto param){
        return ResponseEntity.ok(userService.list(param));
    }

}
