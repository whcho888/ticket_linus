package com.board.service;

import com.board.model.Board;
import com.board.model.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    @Override
    public List<Board> findOwn(String writer, String password) {
        Set<Board> set = new HashSet<Board>(findByWriter(writer));
        set.addAll(findByWriterAndPassword(writer, password));
        return new ArrayList<Board>(set);
    }

    @Override
    public List<Board> findByIsPrivate(Boolean isPrivate){
        return boardRepository.findByIsPrivate(isPrivate);
    }

    @Override
    public Board findByBoardSrl(Long boardSrl){
        return boardRepository.findByBoardSrl(boardSrl);
    }

    @Override
    public List<Board> findByWriter(String writer){
        return boardRepository.findByWriter(writer);
    }

    @Override
    public List<Board> findByWriterAndIsPrivate(String writer, Boolean isPrivate){
        return boardRepository.findByWriterAndIsPrivate(writer, isPrivate);
    }

    @Override
    public List<Board> findByWriterAndPassword(String writer, String password){
        return boardRepository.findByWriterAndPassword(writer, password);
    }

    @Override
    public List<Board> findByWriterLike(String writer){
        return boardRepository.findByWriterLike(writer);
    }

    @Override
    public List<Board> findByWriterLikeAndPassword(String writer, String password){
        return boardRepository.findByWriterLikeAndPassword(writer, password);
    }

    @Override
    public List<Board> findByTitleLikeOrContentsLike(String title, String contents){
        return boardRepository.findByTitleLikeOrContentsLike(title, contents);
    }
}
