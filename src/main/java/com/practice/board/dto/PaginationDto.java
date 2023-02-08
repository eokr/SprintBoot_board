package com.practice.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDto {

    private int page;           //현재 페이지 번호
    private int totalPage;      //전체 페이지 갯수
    private int recodeSize;     //페이지당 출력할 데이터 개수
    private int startPage;      //화면 하단에 출력할 시작 페이지 번호
    private int lastPage;       //화면 하단에 출력할 마지막 페이지 번호
    private int pageSize;       //화면 하단에 출력할 페이지 사이즈
}
