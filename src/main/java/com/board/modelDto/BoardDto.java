package com.board.modelDto;

import com.board.model.Board;
import lombok.Data;

import java.util.Date;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Data
public class BoardDto implements DtoInterface<BoardDto, Board> {
    private Long boardSrl;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private Date regDttm;
    private Date updDttm;
    private Boolean isPrivate;

    public BoardDto(){}

    public BoardDto(String title, String contents, String writer, String password){
        this.setTitle(title);
        this.setContents(contents);
        this.setWriter(writer);
        this.setPassword(password);
    }

    public boolean isSrlNull(){
        return boardSrl == null;
    }

    @Override
    public BoardDto convertAsDto(Board board) {
        this.setBoardSrl(board.getBoardSrl());
        this.setTitle(board.getTitle());
        this.setContents(board.getContents());
        this.setWriter(board.getWriter());
        this.setPassword(board.getPassword());
        this.setRegDttm(board.getRegDttm());
        this.setUpdDttm(board.getUpdDttm());
        this.setIsPrivate(board.getIsPrivate());
        return this;
    }

    @Override
    public Board convertAsEntity(){
        Board board = new Board();
        board.setBoardSrl(this.getBoardSrl());
        board.setTitle(this.getTitle());
        board.setContents(this.getContents());
        board.setWriter(this.getWriter());
        board.setPassword(this.getPassword());
        board.setRegDttm(this.getRegDttm());
        board.setUpdDttm(this.getUpdDttm());
        board.setIsPrivate(this.getIsPrivate());
        return board;
    }

    @Override
    public Board convertAsNewEntity(){
        return new Board(this.getTitle(), this.getContents(), this.getWriter(), this.getPassword());
    }
}
