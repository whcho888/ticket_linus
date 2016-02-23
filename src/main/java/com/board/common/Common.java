package com.board.common;

import com.board.model.Board;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 23..
 */
@Component
public class Common {
    public List<Board> trimLongContents(List<Board> boardList){
        for(Board board : boardList){
            if(board.getContents().length() >= 100){
                board.setContents(board.getContents().substring(0, 27)+"...");
            }
        }
        return boardList;
    }
}
