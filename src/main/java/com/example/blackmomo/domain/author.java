package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class author {

    private int    authorNo;// 저자일련번호
    private String authorNm;// 저자명
    private String debut;// 데뷔
    private String education;// 학력
    private String introduction;// 저자소개
}

