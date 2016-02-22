package com.board.service;

import com.board.model.Board;
import com.board.model.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public long register(Board board){
        return boardRepository.save(board).getBoardSrl();
    }

    @Override
    public long update(Board board){
        return boardRepository.save(board).getBoardSrl();
    }

    @Override
    public boolean delete(Long boardSrl){
        boardRepository.delete(boardSrl);
        return true;
    }

    @Autowired
    public List<Board> findAll(){
        return boardRepository.findAll();
    }
}
