package com.example.blackmomo.service;

import com.example.blackmomo.domain.Board;
import com.example.blackmomo.domain.Paging;
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
    public List<Board> findList(Paging page, Search search) throws Exception{

        System.out.println("page, search 확인 :::" + page + search);

        List<Board> boardList = boardMapper.boardList(page, search);
        System.out.println("boardList 확인 :::" + boardList);
        return boardList;
    }

    public void findSave(Board board) throws Exception {

        boardMapper.boardSave(board);
    }

    public Board findView(int id) throws Exception {


        return boardMapper.selectView(id);
    }

    public void boardCount(int id) {
        boardMapper.insertCount(id);
    }

    public Board findSelectEdit(int id) {

        return boardMapper.selectEdit(id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void updateAll(Board board) {

        boardMapper.updateEdit(board);
    }

    public boolean findDel(int id) {
        boolean isDeleted = boardMapper.delete(id);
        return false;
    }

    // 이전글 정보
    public Board prevSelect(int id) {
        return boardMapper.prevSelect(id);
    }

    public Board nextSelect(int id) {
        return boardMapper.nextSelect(id);
    }
}
