package com.thc.spr202602.domain;

import com.thc.spr202602.dto.BoardDto;
import com.thc.spr202602.dto.DefaultDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Board extends AuditingFileds {
    @Setter Long userId;
    @Setter String title;
    @Setter String content;
    @Setter String author;

    protected Board(){}
    private Board(Boolean deleted, Long userId, String title, String content, String author) {
        this.deleted = deleted;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //이것만 열어둬서, 테이블에 데이터 넣을려면 꼭 이곳을 통해야만 함..
    public static Board of(Long userId, String title, String content, String author) {
        return new Board(false, userId, title, content, author);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(BoardDto.UpdateReqDto param) {
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getTitle() != null){ setTitle(param.getTitle()); }
        if(param.getContent() != null){ setContent(param.getContent()); }
        if(param.getAuthor() != null){ setAuthor(param.getAuthor()); }
    }

}
