package com.board.modelDto;

/**
 * Created by wonhyuk on 2016. 2. 19..
 */
public interface DtoInterface<T, S> {
    T convertAsDto(S model);
    S convertAsEntity();
}
