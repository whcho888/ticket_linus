package com.board.model;

import org.springframework.data.repository.CrudRepository;
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

    List<Board> findByContents(String contents);

    List<Board> findByWriter(String writer);

    List<Board> findByRegDttm(Date start, Date end);

    List<Board> findByUpdDttm(Date start, Date end);

}
