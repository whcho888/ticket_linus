package com.board.controller;

import com.board.modelDto.BoardDto;
import com.board.modelDto.CommentDto;
import com.board.service.BoardService;
import com.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by wonhyuk on 2016. 2. 25..
 */
@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/writer", method = RequestMethod.GET)
    public String getCommentWriter(@RequestParam Long boardSrl, Model model){
        model.addAttribute("boardSrl", boardSrl);
        return "commentWriter";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute @Valid CommentDto commentDto, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println("잘못된 Comment Post Data 가 전송되었습니다.");
        }
        else{
            if (commentDto.isSrlNull()) {
                commentService.register(commentDto.convertAsNewEntity());
            } else {
                commentService.update(commentDto.convertAsEntity());
            }
        }
        model.addAttribute("comments", commentService.findByBoard(commentDto.getBoard()));
        return "comment";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getComment(@RequestParam Long boardSrl, Model model){
        model.addAttribute("comments", commentService.findByBoard(boardService.findByBoardSrl(boardSrl)));
        return "comment";
    }

    @RequestMapping(value="/{commentSrl}/{boardSrl}", method = RequestMethod.DELETE)
    public String deleteBoard(@PathVariable Long commentSrl, @PathVariable Long boardSrl, Model model) {
        if (commentSrl != null){
            commentService.delete(commentSrl);
        }
        model.addAttribute("comments", commentService.findByBoard(boardService.findByBoardSrl(boardSrl)));
        return "comment";
    }

}


