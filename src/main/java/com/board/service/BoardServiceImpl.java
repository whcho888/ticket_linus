package com.board.service;

import com.board.model.Board;
import com.board.model.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Board> combine(List<Board>... list){
        Set<Board> set = new HashSet<>(list[0]);
        for(List<Board> l : list){
            set.retainAll(l);
        }
        return new ArrayList<>(set);
    }


    @Override
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    @Override
    public List<Board> findOwn(String writer, String password) {    // old version
        return combine(findByWriterAndIsPrivate(writer, false), findByWriterAndPassword(writer, password));
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
    public List<Board> findByWriterAndPasswordAndIsPrivate(String writer, String password, Boolean isPrivate){
        return boardRepository.findByWriterAndPasswordAndIsPrivate(writer, password, isPrivate);
    }

    @Override
    public List<Board> findByRegDttmBetween(Date start, Date end){
        return boardRepository.findByRegDttmBetween(start, end);
    }

    @Override
    public List<Board> findByUpdDttmBetween(Date start, Date end){
        return boardRepository.findByUpdDttmBetween(start, end);
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

    @Override
    public List<Board> findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCase(String title, String contents){
        return boardRepository.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCase(title, contents);
    }

    @Override
    public List<Board> findByRegDttmBetweenAndIsPrivate(Date start, Date end, Boolean isPrivate){
        return boardRepository.findByRegDttmBetweenAndIsPrivate(start, end, isPrivate);
    }

    @Override
    public List<Board> findByUpdDttmBetweenAndIsPrivate(Date start, Date end, Boolean isPrivate){
        return boardRepository.findByUpdDttmBetweenAndIsPrivate(start, end, isPrivate);
    }

    @Override
    public List<Board> findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(String title, String contents, Boolean isPrivate){
        return boardRepository.findByTitleContainingIgnoreCaseOrContentsContainingIgnoreCaseAndIsPrivate(title, contents, isPrivate);
    }
}
