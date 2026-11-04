package com.thc.spr202602.mapper;

import com.thc.spr202602.dto.BoardDto;
import java.util.List;

public interface BoardMapper {
	BoardDto.DetailResDto detail(Long id);
	List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param);
}