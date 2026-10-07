package com.thc.spr202602.dto;

import com.thc.spr202602.domain.Board;
import lombok.*;

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
    @Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto{
        Long id;
    }
}
