package com.grape.bookrs.mapper;

import com.grape.bookrs.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
public interface BookMapper extends BaseMapper<Book> {
    public List<Book> getNewBooks();
    public List<Book> findListByMap(Map<String, Object> map);
    public Integer findListCount(Map<String, Object> map);
}
