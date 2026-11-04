package com.thc.spr202602.mapper;

import com.thc.spr202602.dto.UserDto;
import java.util.List;

public interface UserMapper {
	UserDto.DetailResDto detail(Long id);
	List<UserDto.DetailResDto> list(UserDto.ListReqDto param);
}