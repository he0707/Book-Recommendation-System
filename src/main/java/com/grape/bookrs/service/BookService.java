package com.grape.bookrs.service;

import com.grape.bookrs.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grape.bookrs.vo.PopBookVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
public interface BookService extends IService<Book> {
    public List<Book> getNewBooks();
    public List<PopBookVo> getPopBooks();
    public List<Book> getBooksByCategory(Integer level, Integer id);
    public List<Book> findListByMap(Map<String, Object> queryMap);
    public Integer findListCount(Map<String, Object> queryMap);
    public String adminAddBook(Book book);
    public String adminEditBook(Book book);
    public List<Book> getBooksByUserId(Integer userId);
}
