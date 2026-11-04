package com.thc.spr202602.service.impl;

import com.thc.spr202602.domain.User;
import com.thc.spr202602.dto.UserDto;
import com.thc.spr202602.dto.DefaultDto;
import com.thc.spr202602.mapper.UserMapper;
import com.thc.spr202602.repository.UserRepository;
import com.thc.spr202602.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;

    @Override
    public DefaultDto.CreateResDto login(UserDto.CreateReqDto param) {
        User user = userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if(user == null || user.getDeleted()){
            return DefaultDto.CreateResDto.builder().id((long) -100).build();
        } else {
            return DefaultDto.CreateResDto.builder().id(user.getId()).build();
        }
    }

    @Override
    public UserDto.UsernameResDto username(UserDto.CreateReqDto param) {
        boolean exist = true;
        User user = userRepository.findByUsername(param.getUsername());
        if(user == null){
            exist = false;
        }
        return UserDto.UsernameResDto.builder().exist(exist).build();
    }

    @Override
    public DefaultDto.CreateResDto signup(UserDto.CreateReqDto param) {
        /*User user = userRepository.findByUsername(param.getUsername());
        if(user == null){
            return create(param);
        } else {
            return DefaultDto.CreateResDto.builder().id((long) -100).build(); // 아이디가 이미 있는 경우..
        }*/
        if(username(param).getExist()){
            return DefaultDto.CreateResDto.builder().id((long) -100).build();
        } else {
            return create(param);
        }
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param) {
        return userRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(UserDto.UpdateReqDto param) {
        User user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        user.update(param);
        userRepository.save(user);
    }
    @Override
    public void delete(UserDto.UpdateReqDto param) {
        update(UserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }
    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        /*User user = userRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("no data"));
        UserDto.DetailResDto res = UserDto.DetailResDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .birth(user.getBirth())
                .deleted(user.getDeleted())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();

        if(res.getDeleted()){
            return UserDto.DetailResDto.builder().id(user.getId()).deleted(true).build();
        }*/
        UserDto.DetailResDto res = userMapper.detail(param.getId());
        if(res.getDeleted()){
            res.setUsername(null);
            res.setName(null);
        }
        return res;
    }
    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto param) {
        /*System.out.println("name : " + param.getName());
        List<UserDto.DetailResDto> list = new ArrayList<>();
        List<User> users = userRepository.findAll();*/
        List<UserDto.DetailResDto> newlist = new ArrayList<>();
        List<UserDto.DetailResDto> list = userMapper.list(param);
        for(UserDto.DetailResDto each : list){
            newlist.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newlist;
    }
}
