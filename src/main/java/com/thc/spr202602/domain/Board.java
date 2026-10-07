package com.thc.spr202602.domain;

import com.thc.spr202602.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Board extends AuditingFileds {
    @Setter String title;
    @Setter String content;
    @Setter String author;

    //생성자를 어디서도 못쓰게 막아버리고 싶은데, private는 안되어서, protected로!!
    protected Board(){}
    private Board(Boolean deleted, String title, String content, String author) {
        this.deleted = deleted;
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //이것만 열어둬서, 테이블에 데이터 넣을려면 꼭 이곳을 통해야만 함..
    public static Board of(String title, String content, String author) {
        return new Board(false, title, content, author);
    }
    public BoardDto.CreateResDto toCreateResDto() {
        return BoardDto.CreateResDto.builder().id(getId()).build();
    }
}
