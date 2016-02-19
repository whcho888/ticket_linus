package com.board.modelDto;

import com.board.model.Board;
import lombok.Data;

import java.util.Date;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Data
public class BoardDto implements DtoInterface<BoardDto, Board> {
    private long boardSrl;
    private String title;
    private String contents;
    private String writer;
    private Date regDttm;
    private Date updDttm;

    public BoardDto(String title, String contents, String writer){
        this.setTitle(title);
        this.setContents(contents);
        this.setWriter(writer);
    }

    @Override
    public BoardDto convertAsDto(Board board) {
        this.setBoardSrl(board.getBoardSrl());
        this.setTitle(board.getTitle());
        this.setContents(board.getContents());
        this.setWriter(board.getWriter());
        this.setRegDttm(board.getRegDttm());
        this.setUpdDttm(board.getUpdDttm());
        return this;
    }

    @Override
    public Board convertAsEntity(){
        Board board = new Board();
        board.setBoardSrl(this.getBoardSrl());
        board.setTitle(this.getTitle());
        board.setContents(this.getContents());
        board.setWriter(this.getWriter());
        board.setRegDttm(this.getRegDttm());
        board.setUpdDttm(this.getUpdDttm());
        return board;
    }
}
