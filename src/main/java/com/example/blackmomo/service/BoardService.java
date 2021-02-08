package com.example.blackmomo.service;

import com.example.blackmomo.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {

    List<Board> findList();
}
