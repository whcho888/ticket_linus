package com.board.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wonhyuk on 2016. 2. 25..
 */
@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentSrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="boardSrl")
    private Board board;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writer;

    @Column(insertable=false)
    private Date regDttm;

    @Column(insertable=false)
    private Date updDttm;

    public Comment(){}

    public Comment(Board board, String contents, String writer){
        this.setBoard(board);
        this.setContents(contents);
        this.setWriter(writer);
    }
}
