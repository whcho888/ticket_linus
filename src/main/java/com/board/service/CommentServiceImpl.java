package com.board.service;

import com.board.model.Board;
import com.board.model.Comment;
import com.board.model.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 25..
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public long register(Comment comment){
        return commentRepository.save(comment).getCommentSrl();
    }

    @Override
    public long update(Comment comment){
        return commentRepository.save(comment).getCommentSrl();
    }

    @Override
    public boolean delete(Long commentSrl){
        commentRepository.delete(commentSrl);
        return true;
    }


    @Override
    public Comment findByCommentSrl(Long commentSrl){
        return commentRepository.findByCommentSrl(commentSrl);
    }


    @Override
    public List<Comment> findByBoard(Board board){
        return commentRepository.findByBoard(board);
    }


    @Override
    public void delete(Iterable<? extends Comment> entities){
        commentRepository.delete(entities);
    }
}
