package com.board.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
    Board findByBoardSrl(Long boardSrl);

    List<Board> findAll();

    List<Board> findByTitle(String title);

    List<Board> findByIsPrivate(Boolean isPrivate);

    List<Board> findByIsPrivateOrderByUpdDttmDesc(Boolean isPrivate);

    List<Board> findByWriter(String writer);

    List<Board> findByWriterAndIsPrivate(String writer, Boolean isPrivate);

    List<Board> findByWriterLike(String writer);

    List<Board> findByRegDttmBetween(Date start, Date end);

    List<Board> findByUpdDttmBetween(Date start, Date end);

    List<Board> findByWriterAndPassword(String wrtier, String password);

    List<Board> findByWriterAndPasswordAndIsPrivate(String writer, String password, Boolean isPrivate);

    List<Board> findByWriterLikeAndPassword(String wrtier, String password);

    List<Board> findByTitleLikeOrContentsLike(String title, String contents);

    List<Board> findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCase(String title, String contents);

    List<Board> findByRegDttmBetweenAndIsPrivate(Date start, Date end, Boolean isPrivate);

    List<Board> findByUpdDttmBetweenAndIsPrivate(Date start, Date end, Boolean isPrivate);

    List<Board> findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(String title, String contents, Boolean isPrivate);
}
