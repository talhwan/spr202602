package com.thc.spr202602;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DataPosting {
    Integer id;
    String title;
    String content;

    /*public void setId(Integer id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }*/
}
