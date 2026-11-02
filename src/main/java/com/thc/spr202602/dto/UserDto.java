package com.thc.spr202602.dto;

import com.thc.spr202602.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

public class UserDto {

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UsernameResDto {
        Boolean exist;
    }

    /**/

    @Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto{
        String username;
        String password;
        String email;
        String name;
        LocalDate birth;

        public User toEntity() {
            return User.of(getUsername(), getPassword(), getEmail(), getName(), getBirth());
        }
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        String password;
        String email;
        String name;
        LocalDate birth;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto{
        String username;
        String password;
        String email;
        String name;
        LocalDate birth;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        String name;
        LocalDate birth;
    }
}
