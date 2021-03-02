package com.example.blackmomo.service;

import com.example.blackmomo.domain.Board;
import com.example.blackmomo.domain.Paging;
import com.example.blackmomo.domain.Reply;
import com.example.blackmomo.domain.Search;
import com.example.blackmomo.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl {

    @Autowired
    BoardMapper boardMapper;

    public int countBoard(Paging vo, Search sh) {
        return boardMapper.countBoard(vo, sh);
    }
    /**
     *
     * @return
     */

    // 게시글 목록
    public List<Board> findList(Paging page, Search search) throws Exception{
        List<Board> boardList = boardMapper.boardList(page, search);
        return boardList;
    }

    // 게시글 등록
    public void findSave(Board board) throws Exception { boardMapper.boardSave(board); }

    // 지정된 게시글 조회
    public Board findView(int id) throws Exception { return boardMapper.selectView(id); }

    // 게시글 수 조회
    public void boardCount(int id) {
        boardMapper.insertCount(id);
    }

    // 지정된 게시글 조회
    public Board findSelectEdit(int id) { return boardMapper.selectEdit(id); }

    // 게시글 수정
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateAll(Board board) { boardMapper.updateEdit(board); }

    // 게시글 삭제
    public boolean findDel(int id) {
        boolean isDeleted = boardMapper.delete(id);
        return false;
    }

    // 이전글 조회
    public Board prevSelect(int id) {
        return boardMapper.prevSelect(id);
    }

    // 다음글 조회
    public Board nextSelect(int id) {
        return boardMapper.nextSelect(id);
    }

    /* ============================================================================================================= */

    // 댓글 목록
    public List<Reply> list(int bno) { return boardMapper.replyList(bno); }

    // 댓글 수
    public int count(int bno){ return boardMapper.count(bno); }

    // 댓글 쓰기
    public void create(Reply dto) { boardMapper.create(dto); }

    public void modify(Reply dto) { boardMapper.modify(dto); }
}
