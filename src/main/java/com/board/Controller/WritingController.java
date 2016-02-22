package com.board.Controller;

import com.board.modelDto.BoardDto;
import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String writingPage() {
        return "write";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String regiseter(@ModelAttribute @Valid BoardDto boardDto, BindingResult result) {
        if(result.hasErrors()){
            System.out.println("잘못된 Post Data 가 전송되었습니다.");
        }
        else{
            boardService.register(boardDto.convertAsEntity());
        }
        return "redirect:/";
    }
}

