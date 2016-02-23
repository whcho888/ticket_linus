package com.board.controller;

import com.board.model.Board;
import com.board.modelDto.BoardDto;
import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.board.common.Common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wonhyuk on 2016. 2. 23..
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private Common common;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String searchAll(@RequestParam(required = false) int idx, Model model){
        model.addAttribute("boards", common.trimLongContents(boardService.findByIsPrivate(false)));
        return "board";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String searchByConditions(@ModelAttribute BoardDto boardDto, Model model){
        model.addAttribute("boards", common.trimLongContents(boardService.findAll()));
        return "board";
    }

    @RequestMapping(value="/srl", method = RequestMethod.POST)
    public String searchDetailByBoardSrl(@RequestParam Long boardSrl, Model model){
        model.addAttribute("board", (boardService.findByBoardSrl(boardSrl)));
        return "detail";
    }

    @RequestMapping(value="/writer", method = RequestMethod.POST)
    public String searchByWriter(@RequestParam String writer, @RequestParam String password, Model model) {
        if(password == ""){
            model.addAttribute("boards", common.trimLongContents(boardService.findByWriterAndIsPrivate(writer, false)));
        }
        else{
            model.addAttribute("boards", common.trimLongContents(boardService.findOwn(writer, password)));
        }
        return "board";
    }
}
