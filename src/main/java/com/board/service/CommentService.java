package com.board.service;

import com.board.model.Board;
import com.board.model.Comment;

import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 25..
 */
public interface CommentService {
    long register(Comment comment);
    long update(Comment comment);
    boolean delete(Long commentSrl);
    Comment findByCommentSrl(Long commentSrl);
    List<Comment> findByBoard(Board board);
}
