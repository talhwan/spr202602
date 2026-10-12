package com.thc.spr202602.dto;

import com.thc.spr202602.domain.Board;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class BoardDto {

    @Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto{
        String title;
        String content;
        String author;

        public Board toEntity() {
            return Board.of(getTitle(), getContent(), getAuthor());
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
        String title;
        String content;
        String author;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        String title;
        String author;
    }
}
