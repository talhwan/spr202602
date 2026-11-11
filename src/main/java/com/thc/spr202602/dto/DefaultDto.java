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

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto {
        Integer callpage; // 요청받은 페이지 넘버
        Integer perpage; // 한번에 볼 페이지 안의 글 수
        Integer offset; // 요청받은 페이지에 가장 첫번째 글 순번

        String orderby; // 정렬 기준
        String orderway; // 정렬 방향

        Boolean deleted;

        public DefaultDto.PagedListResDto init(int listsize){
            int perpage = 10;
            if(getPerpage() != null){
                perpage = getPerpage();
            }
            if(perpage <= 0){
                perpage = 10;
            }
            Integer pagesize = listsize / perpage; // 12 / 5 => 2?!
            if(listsize % perpage > 0){
                pagesize++;
            }
            int callpage = 1;
            if(getCallpage() != null){
                callpage = getCallpage();
            }
            if(callpage <= 0){
                callpage = 1;
            }
            if(callpage > pagesize){
                callpage = pagesize;
            }
            int offset = (callpage - 1) * perpage;
            setOffset(offset);
            setPerpage(perpage);

            String orderby = "id";
            if(getOrderby() != null){
                orderby = getOrderby();
            }
            String orderway = "DESC";
            if(getOrderway() != null){
                orderway = getOrderway();
            }
            setOrderway(orderway);
            setOrderby(orderby);

            return DefaultDto.PagedListResDto.builder().callpage(callpage)
                    .perpage(perpage)
                    .listsize(listsize)
                    .pagesize(pagesize).build();
        }
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListResDto {
        Integer callpage; // 실제 보여주는 페이지 넘버
        Integer perpage; // 실제 보여주는 한번에 보여줄 페이지 안에 글 수
        Integer listsize; // 전체 글 수
        Integer pagesize; // 전체 페이지 수

        Object list; //실제 목록
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto {
        Long cursor;
        String mark;
        Integer perpage; // 한번에 볼 페이지 안의 글 수
        String orderby; // 정렬 기준
        String orderway; // 정렬 방향

        Boolean deleted;

        public void init(){
            int perpage = 10;
            if(getPerpage() != null){
                perpage = getPerpage();
            }
            if(perpage <= 0){
                perpage = 10;
            }
            setPerpage(perpage);

            String orderby = "id";
            if(getOrderby() != null){
                orderby = getOrderby();
            }
            String orderway = "DESC";
            if(getOrderway() != null){
                orderway = getOrderway();
            }
            setOrderway(orderway);
            setOrderby(orderby);

        }
    }
}
