package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {

    private String orderBy;    // 정렬 방식
    private String orderType;  // 정렬 순서
    private String searchType; // 검색 타입
    private String keyword;    // 검색 키워드
}
