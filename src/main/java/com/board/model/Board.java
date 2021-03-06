package com.board.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Data
@Entity
@Table(name="board")
public class Board {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardSrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;

    @Column(insertable=false)
    private Date regDttm;

    @Column(insertable=false)
    private Date updDttm;

    @Column(nullable = false)
    private Boolean isPrivate;

    public Board(){}

    public Board(String title, String contents, String writer, String password){
        this.setTitle(title);
        this.setContents(contents);
        this.setWriter(writer);
        this.setPassword(password);
    }
}
