package com.example.blackmomo.service;

import com.example.blackmomo.domain.Board;
import com.example.blackmomo.domain.Paging;
import com.example.blackmomo.domain.Reply;
import com.example.blackmomo.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {

    @Autowired
    BoardServiceImpl boardServiceImpl = new BoardServiceImpl();

    // 게시판 총수
    int countBoard(Paging vo, Search search) throws Exception;

    // 게시판 목록조회
    List<Board> findList(Paging vo, Search search) throws Exception;

    // 게시물 등록
    void findSave(Board board) throws Exception;

    // 상세게시물 조회
    void boardCount(int id);

    Board findView(int id) throws Exception;

    // 이전글 조회
    Board prevSelect(int id);

    // 다음글 조회
    Board nextSelect(int id);

    // 수정화면 정보 조회
    Board findSelectEdit(int id);
    // 수정처리
    void updateAll(Board board);
    // 게시물 삭제
    boolean findDel(int id);

    // 댓글 수
    int count(int bno);
    // 댓글 목록조회
    List<Reply> list(int bno);
    // 댓글 등록
    void create(Reply dto);
    // 댓글 수정
    void modify(Reply dto);
    // 댓글 삭제
    void replyDelete(Reply dto);
}
