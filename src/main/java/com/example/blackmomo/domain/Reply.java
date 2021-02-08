package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Reply {
    private int id;               // 댓글번호
    private String content;       // 댓글내용
    private String writer;        // 작성자
    private LocalDateTime wriDt;  // 작성일
    private LocalDateTime modiDt; // 수정일
    private String deleteYn;      // 삭제여부
    private int boardId;          // 게시판번호
    /*
    댓글
    1. 번호, 내용, 게시판 번호, 작성자, 댓글상태(Y/N), 작성일, 수정일
    */
}
