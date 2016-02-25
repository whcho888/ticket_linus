package com.board.controller;

import com.board.modelDto.SearchDto;
import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.board.common.Common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
        model.addAttribute("boards", Common.trimLongContents(boardService.findByIsPrivate(false)));
        return "board";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String searchByConditions(@ModelAttribute SearchDto searchDto, Model model){
        String writer = searchDto.getWriter(), password = searchDto.getPassword(), keyword = searchDto.getKeyword();
        if(searchDto.getIsTimeSearch() != null){  // 1
            Date startTime = Common.convertHtmlDateToJavaDate(searchDto.getStartTime());
            Date endTime = Common.convertHtmlDateToJavaDate(searchDto.getEndTime());
            if(!keyword.equals("")){ // 1, 2
                if(!writer.equals("")){  // 1, 2, 3
                    if(searchDto.getIsPrivate() != null){   // 1, 2, 3, 3-1 //
                        model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                                boardService.findByRegDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                                boardService.findByUpdDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                                boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false), // 2
                                boardService.findByWriterAndIsPrivate(writer, false), // 3
                                boardService.findByWriterAndPassword(writer, password)  // 3-1
                        )));
                    } else {    // 1, 2, 3 //
                        model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                                boardService.findByRegDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                                boardService.findByUpdDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                                boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false), // 2
                                boardService.findByWriterAndIsPrivate(writer, false)
                        ))); // 3
                    }
                } else {    // 1, 2 //
                    model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                            boardService.findByRegDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                            boardService.findByUpdDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                            boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false)
                    ))); // 2
                }
            } else {    // 1 //
                model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                        boardService.findByRegDttmBetweenAndIsPrivate(startTime, endTime, false),  // 1
                        boardService.findByUpdDttmBetweenAndIsPrivate(startTime, endTime, false)  // 1
                )));
            }
        } else if(!keyword.equals("")){ // 2
           if(!writer.equals("")){   // 2, 3
               if(searchDto.getIsPrivate() != null) { // 2, 3, 3-1 //
                   model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                           boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false), // 2
                           boardService.findByWriterAndIsPrivate(writer, false), // 3
                           boardService.findByWriterAndPassword(writer, password)  // 3-1
                   )));
               } else { // 2, 3 //
                   model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                           boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false), // 2
                           boardService.findByWriterAndIsPrivate(writer, false) // 3
                   )));
               }
           } else { // 2 //
               model.addAttribute("boards", Common.trimLongContents(
                       boardService.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(keyword, keyword, false) // 2
               ));
           }
        } else if (!writer.equals("")) { // 3
           if(searchDto.getIsPrivate() != null) { // 3, 3-1 //
               model.addAttribute("boards", Common.trimLongContents(boardService.combine(
                       boardService.findByWriterAndIsPrivate(writer, false), // 3
                       boardService.findByWriterAndPassword(writer, password)  // 3-1
               )));
           } else { // 3 //
               model.addAttribute("boards", Common.trimLongContents(
                       boardService.findByWriterAndIsPrivate(writer, false) // 3
               ));
           }
        }
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
