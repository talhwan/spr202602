package com.thc.spr202602.domain;

import com.thc.spr202602.dto.UserDto;
import com.thc.spr202602.dto.DefaultDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class User extends AuditingFileds{
    @Column(nullable = false, unique = true) String username;
    @Setter @Column(nullable = false) String password;
    @Setter String email;
    @Setter String name;
    @Setter LocalDate birth;

    protected User(){}
    private User(Boolean deleted, String username, String password, String email, String name, LocalDate birth) {
        this.deleted = deleted;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
    }
    public static User of(String username, String password, String email, String name, LocalDate birth) {
        return new User(false, username, password, email, name, birth);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(UserDto.UpdateReqDto param) {
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getPassword() != null){ setPassword(param.getPassword()); }
        if(param.getEmail() != null){ setEmail(param.getEmail()); }
    }
}
