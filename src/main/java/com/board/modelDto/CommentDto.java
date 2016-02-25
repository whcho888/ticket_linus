package com.board.modelDto;

import com.board.model.Board;
import com.board.model.Comment;
import lombok.Data;

import java.util.Date;

/**
 * Created by wonhyuk on 2016. 2. 25..
 */
@Data
public class CommentDto implements DtoInterface<CommentDto, Comment> {
    private Long commentSrl;
    private Board board;
    private String contents;
    private String writer;
    private Date regDttm;
    private Date updDttm;

    public CommentDto() {}

    public CommentDto(Board board, String contents, String writer){
        this.setBoard(board);
        this.setContents(contents);
        this.setWriter(writer);
    }

    public boolean isSrlNull(){
        return commentSrl == null;
    }

    @Override
    public CommentDto convertAsDto(Comment comment) {
        this.setCommentSrl(comment.getCommentSrl());
        this.setContents(comment.getContents());
        this.setWriter(comment.getWriter());
        this.setRegDttm(comment.getRegDttm());
        this.setUpdDttm(comment.getUpdDttm());
        return this;
    }

    @Override
    public Comment convertAsEntity(){
        Comment comment = new Comment();
        comment.setCommentSrl(this.getCommentSrl());
        comment.setContents(this.getContents());
        comment.setWriter(this.getWriter());
        comment.setRegDttm(this.getRegDttm());
        comment.setUpdDttm(this.getUpdDttm());
        return comment;
    }

    @Override
    public Comment convertAsNewEntity(){
        return new Comment(this.getBoard(), this.getContents(), this.getWriter());
    }
}
