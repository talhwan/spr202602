package com.thc.spr202602.service;

import com.thc.spr202602.dto.UserDto;
import com.thc.spr202602.dto.DefaultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    DefaultDto.CreateResDto login(UserDto.CreateReqDto param);
    UserDto.UsernameResDto username(UserDto.CreateReqDto param);
    DefaultDto.CreateResDto signup(UserDto.CreateReqDto param);
    /**/
    DefaultDto.CreateResDto create(UserDto.CreateReqDto param);
    void update(UserDto.UpdateReqDto param);
    void delete(UserDto.UpdateReqDto param);
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto param);
}
