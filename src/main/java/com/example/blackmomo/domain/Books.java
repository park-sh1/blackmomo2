package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

// 도서
@Setter
@Getter
public class Books {
    private int no; // 일련번호
    private String category; // 카테고리
    private String name; // 이름
    private String authorName; // 저자
    private int authorNo; // 저자일련번호
    private String publicationYear; // 출판연도
    private String price; // 가격

    private String bestYn; // 베스트여부

    private String exStDate; // 노출시작일
    private String exStTime; // 노출시작시간
    private String exEndDate; // 노출종료일
    private String exEndTime; // 노출종료시간

    private String exposureYn; // 노출여부
    private String useYn; // 사용여부
    private String deleteYn; // 삭제여부
}
