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
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    @Override
    public int countBoard(Paging vo, Search search) throws Exception{

        System.out.println("도착 확인~~~~~~~~~~" + vo + " 확인 2 ::: " + search);

        return boardMapper.countBoard(vo, search);
    }
    /**
     *
     * @return
     */

    // 게시글 목록
    @Override
    public List<Board> findList(Paging page, Search search) throws Exception{
        List<Board> boardList = boardMapper.boardList(page, search);
        return boardList;
    }

    // 게시글 등록
    @Override
    public void findSave(Board board) throws Exception { boardMapper.boardSave(board); }

    // 지정된 게시글 조회
    @Override
    public Board findView(int id) throws Exception { return boardMapper.selectView(id); }

    // 게시글 수 조회
    @Override
    public void boardCount(int id) {
        boardMapper.insertCount(id);
    }

    // 지정된 게시글 조회
    @Override
    public Board findSelectEdit(int id) { return boardMapper.selectEdit(id); }

    // 게시글 수정
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateAll(Board board) { boardMapper.updateEdit(board); }

    // 게시글 삭제
    @Override
    public boolean findDel(int id) {
        boolean isDeleted = boardMapper.delete(id);
        return false;
    }

    // 이전글 조회
    @Override
    public Board prevSelect(int id) {
        return boardMapper.prevSelect(id);
    }

    // 다음글 조회
    @Override
    public Board nextSelect(int id) {
        return boardMapper.nextSelect(id);
    }

    /* ============================================================================================================= */

    // 댓글 목록
    @Override
    public List<Reply> list(int bno) { return boardMapper.replyList(bno); }

    // 댓글 수
    @Override
    public int count(int bno){ return boardMapper.count(bno); }

    // 댓글 쓰기
    @Override
    public void create(Reply dto) { boardMapper.create(dto); }

    @Override
    public void modify(Reply dto) { boardMapper.modify(dto); }

    // 댓글 삭제
    @Override
    public void replyDelete(Reply dto) {
        boardMapper.replyDelete(dto);
    }

    /* ============================================================================= */


}
