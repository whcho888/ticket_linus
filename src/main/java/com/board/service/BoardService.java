package com.board.service;

import com.board.model.Board;

import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
public interface BoardService {
    long register(Board board);
    long update(Board board);
    boolean delete(Long boardSrl);
    List<Board> findAll();
}
