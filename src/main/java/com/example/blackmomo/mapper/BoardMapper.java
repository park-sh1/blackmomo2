package com.example.blackmomo.mapper;

import com.example.blackmomo.domain.Board;
import com.example.blackmomo.domain.Paging;
import com.example.blackmomo.domain.Reply;
import com.example.blackmomo.domain.Search;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 조회
    @Autowired
    public List<Board> boardList(Paging page, Search sh) throws Exception;

    public List<Board> boardList2() throws Exception;

    public void boardSave(Board board) throws Exception;

    // 특정 게기물 조회
    public Board selectView(int id) throws Exception;

    // 게시물 등록
    public void insertCount(int id);

    // 특정 수정정보 조회
    public Board selectEdit(int id);


    public void updateEdit(Board board);

    public boolean delete(int id);

    // 조회수 계산
    public int countBoard(Paging page, Search sh);

    // 이전글 정보
    Board prevSelect(int id);

    // 다음글 정보
    Board nextSelect(int id);

    // 댓글 조회
    public List<Reply> replyList(int bno);

    //댓글 수
    public int count(int bno);

    // 댓글 등록
    public void create(Reply dto);

    public void modify(Reply dto);

    // 댓글 수정


    // 댓글 삭제

}
