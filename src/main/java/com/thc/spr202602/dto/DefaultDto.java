package com.thc.spr202602.dto;

import com.thc.spr202602.domain.Board;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class DefaultDto {

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto{
        Long id;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto{
        Long id;
        Boolean deleted;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto{
        Long id;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto{
        Long id;
        Boolean deleted;

        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto{
        Boolean deleted;
    }
}
