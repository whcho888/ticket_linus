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
    List<Board> findOwn(String writer, String password);
    List<Board> findByIsPrivate(Boolean isPrivate);
    Board findByBoardSrl(Long boardSrl);
    List<Board> findByWriter(String writer);
    List<Board> findByWriterAndIsPrivate(String writer, Boolean isPrivate);
    List<Board> findByWriterAndPassword(String writer, String password);
    List<Board> findByWriterLike(String writer);
    List<Board> findByWriterLikeAndPassword(String writer, String password);
    List<Board> findByTitleLikeOrContentsLike(String title, String contents);
}
