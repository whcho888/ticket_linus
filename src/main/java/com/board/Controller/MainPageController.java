package com.board.controller;

import com.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Slf4j
@Controller
public class MainPageController {
    @Autowired
    private BoardService boardService;


    @RequestMapping("/")
    public String main() {
        return "main";
    }
}
