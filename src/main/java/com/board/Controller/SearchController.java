package com.board.controller;

import com.board.model.Board;
import com.board.modelDto.SearchDto;
import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.board.common.Common;

import java.util.Date;
import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 23..
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String searchAll(@RequestParam(required = false) int idx, Model model){
        model.addAttribute("boards", Common.trimLongContents(boardService.findByIsPrivateOrderByUpdDttmDesc(false)));
        return "board";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String searchByConditions(@ModelAttribute SearchDto searchDto, Model model){
        String writer = searchDto.getWriter(), password = searchDto.getPassword(), keyword = searchDto.getKeyword();
        List<Board> combinedBoard = null;
        if(searchDto.getIsTimeSearch() != null) {  // 1
            Date startTime = Common.convertHtmlDateToJavaDate(searchDto.getStartTime());
            Date endTime = Common.convertHtmlDateToJavaDate(searchDto.getEndTime());
            combinedBoard = boardService.combine(boardService.findByRegDttmBetweenAndIsPrivate(startTime, endTime, false),
                                            boardService.findByUpdDttmBetweenAndIsPrivate(startTime, endTime, false));
        }
        if(!keyword.equals("")) {    // 2
            combinedBoard = boardService.combine(combinedBoard, boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false));
        }
        if(!writer.equals("")) { // 3
            combinedBoard = boardService.combine(combinedBoard, boardService.findByWriterAndIsPrivate(writer, false));
            if (searchDto.getIsPrivate() != null) { // 3-1
                combinedBoard = boardService.combine(combinedBoard, boardService.findByWriterAndPassword(writer, password));
            }
        }
        model.addAttribute("boards", combinedBoard);
        return "board";
    }

    @RequestMapping(value="/srl", method = RequestMethod.POST)
    public String searchDetailByBoardSrl(@RequestParam Long boardSrl, Model model){
        model.addAttribute("board", (boardService.findByBoardSrl(boardSrl)));
        return "detail";
    }

    @RequestMapping(value="/writer", method = RequestMethod.POST)
    public String searchByWriter(@RequestParam String writer, @RequestParam String password, @RequestParam Boolean isPrivate,
                                 Model model) {
        if(!isPrivate){
            model.addAttribute("boards", Common.trimLongContents(boardService.findByWriterAndIsPrivate(writer, isPrivate)));
        }
        else{
            model.addAttribute("boards", Common.trimLongContents(boardService.findByWriterAndPasswordAndIsPrivate(writer, password, isPrivate)));
        }
        return "board";
    }

}
