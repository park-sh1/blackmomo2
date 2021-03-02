package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class Board {

    private int    id; // 일련번호

    private String boardType;  // 게시판 타입 / 게시판형 / 웹디자인형 / 액자형

    private String align;   // 정렬 ( 최신순/조회순)
    private String alignYn; // 정렬차순

    private int    boardCount; // 조회수
    private String content;    // 내용
    private String title;      // 제목

    /*private String exposeStartDay;// 노출시작일
    private String exposeStartTime; // 노출시작시간
    private String exposeEndDay;    // 노출종료일
    private String exposeEndTime;   // 노출종료시간*/

    private String        registerer;   // 작성자
    private LocalDateTime registerDate;
    private String        modifier;     // 수정자
    private LocalDateTime modifiedDate; // 수정일
    private int           fileId;       // 파일 일련번호

    private String        exposeYn; // 노출여부
    private String        useYn;    // 사용여부
    private String        deleteYn; // 삭제여부
    private int           recnt;    // 게시글의 댓글수

    /*    private String author; // 작성자*/
}
