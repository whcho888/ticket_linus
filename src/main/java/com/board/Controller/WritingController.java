package com.board.controller;

import com.board.common.Common;
import com.board.modelDto.BoardDto;
import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by wonhyuk on 2016. 2. 22..
 */
@Slf4j
@Controller
@RequestMapping("/write")
public class WritingController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private Common common;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String writingPage(@RequestParam(required = false) Long boardSrl, Model model) {
        if (boardSrl != null)
            model.addAttribute("board", boardService.findByBoardSrl(boardSrl));
        return "write";
    }

    @ResponseBody
    @RequestMapping(value="/{boardSrl}", method = RequestMethod.DELETE)
    public Boolean deleteBoard(@PathVariable Long boardSrl, Model model) {
        if (boardSrl != null){
            boardService.delete(boardSrl);
        }
        return true;
    }


    @RequestMapping(value="/", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid BoardDto boardDto, BindingResult result) {
        if(result.hasErrors()){
            System.out.println("잘못된 Post Data 가 전송되었습니다.");
        }
        else{
            if (boardDto.getBoardSrl() == 0) {
                boardService.register(boardDto.convertAsEntity());
            } else {
                boardService.update(boardDto.convertAsEntity());
            }
        }
        return "redirect:/";
    }


}

