package com.board.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 25..
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>  {
    Comment findByCommentSrl(Long commentSrl);
    List<Comment> findByBoard(Board board);
}
