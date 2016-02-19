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
    private long boardSrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private Date regDttm;

    @Column(nullable = false)
    private Date updDttm;
}
