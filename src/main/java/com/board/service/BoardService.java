package com.board.service;

import com.board.model.Board;
import com.board.modelDto.BoardDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
public interface BoardService {
    long register(Board board);
    long update(Board board);
    boolean delete(Long boardSrl);
    List<Board> combine(List<Board>... list);
    List<Board> findAll();
    List<Board> findOwn(String writer, String password);
    List<Board> findByIsPrivate(Boolean isPrivate);
    List<Board> findByIsPrivateOrderByUpdDttmDesc(Boolean isPrivate);
    Board findByBoardSrl(Long boardSrl);
    List<Board> findByWriter(String writer);
    List<Board> findByWriterAndIsPrivate(String writer, Boolean isPrivate);
    List<Board> findByWriterAndPassword(String writer, String password);
    List<Board> findByWriterAndPasswordAndIsPrivate(String writer, String password, Boolean isPrivate);
    List<Board> findByRegDttmBetween(Date start, Date end);
    List<Board> findByUpdDttmBetween(Date start, Date end);
    List<Board> findByWriterLike(String writer);
    List<Board> findByWriterLikeAndPassword(String writer, String password);
    List<Board> findByTitleLikeOrContentsLike(String title, String contents);
    List<Board> findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCase(String title, String contents);

    List<Board> findByRegDttmBetweenAndIsPrivate(Date start, Date end, Boolean isPrivate);
    List<Board> findByUpdDttmBetweenAndIsPrivate(Date start, Date end, Boolean isPrivate);
    List<Board> findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(String title, String contents, Boolean isPrivate);
}
