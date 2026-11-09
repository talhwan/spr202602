package com.thc.spr202602.dto;

import com.thc.spr202602.domain.Board;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto{
        String title;
        String content;
        String author;

        Long userId;

        public Board toEntity() {
            return Board.of(getUserId(), getTitle(), getContent(), getAuthor());
        }
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        String title;
        String content;
        String author;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto{
        Long userId;
        String title;
        String content;
        String author;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        Long userId;
        String title;
        String author;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto {
        Boolean deleted;
        Integer callpage; // 요청받은 페이지 넘버
        Integer perpage; // 한번에 볼 페이지 안의 글 수
        Integer offset; // 요청받은 페이지에 가장 첫번째 글 순번

        String orderby; // 정렬 기준
        String orderway; // 정렬 방향

        Long userId;
        String title;
        String author;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListResDto {
        Integer callpage; // 실제 보여주는 페이지 넘버
        Integer perpage; // 실제 보여주는 한번에 보여줄 페이지 안에 글 수
        Integer listsize; // 전체 글 수
        Integer pagesize; // 전체 페이지 수

        List<BoardDto.DetailResDto> list; //실제 목록
    }
}
