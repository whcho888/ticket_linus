package com.board.modelDto;

/**
 * Created by wonhyuk on 2016. 2. 24..
 */
import lombok.Data;


@Data
public class SearchDto {
    public SearchDto(){}

    private String writer;
    private String password;
    private String keyword;
    private String startTime;
    private String endTime;
    private String isPrivate;
    private String isTimeSearch;

}
