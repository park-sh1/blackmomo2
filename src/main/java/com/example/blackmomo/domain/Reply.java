package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Reply {
    private int rno;              // 댓글번호
    private int bno;              // 게시판번호
    private String replyText;       // 댓글내용

    private String replyer;       // 작성자 id
    private String name;          // 작성자 이름

    private LocalDateTime wriDt;  // 작성일
    private LocalDateTime modiDt; // 수정일

    private String secretReply;  // 비밀댓글 여부
    private String deleteYn;      // 삭제여부
    /*
    댓글
    1. 번호, 내용, 게시판 번호, 작성자, 댓글상태(Y/N), 작성일, 수정일
    */
}
