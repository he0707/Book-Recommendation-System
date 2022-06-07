package com.grape.bookrs.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grape.bookrs.entity.Book;
import com.grape.bookrs.entity.Collect;
import com.grape.bookrs.entity.Rating;
import com.grape.bookrs.mapper.BookMapper;
import com.grape.bookrs.mapper.CollectMapper;
import com.grape.bookrs.mapper.RatingMapper;
import com.grape.bookrs.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grape.bookrs.vo.PopBookVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public List<Book> getNewBooks(){
        return bookMapper.getNewBooks();
    }

    @Override
    public List<PopBookVo> getPopBooks(){
        List<PopBookVo> popBookVoList = new ArrayList<PopBookVo>();
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        List<Book> books = bookMapper.selectList(queryWrapper);
        for (Book book : books) {
            QueryWrapper<Rating> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("isbn", book.getIsbn());
            int num = ratingMapper.selectList(queryWrapper1).size();
            PopBookVo popBookVo = new PopBookVo();
            popBookVo.setIsbn(book.getIsbn());
            popBookVo.setTitle(book.getTitle());
            popBookVo.setAuthor(book.getAuthor());
            popBookVo.setCategorylevelone(book.getCategorylevelone());
            popBookVo.setCategoryleveltwo(book.getCategoryleveltwo());
            popBookVo.setImage(book.getImage());
            popBookVo.setRatingNum(num);
            popBookVoList.add(popBookVo);
        }
        popBookVoList.sort((y, x) -> Integer.compare(x.getRatingNum(), y.getRatingNum()));
        return popBookVoList.subList(0, 8);
    }

    @Override
    public List<Book> getBooksByUserId(Integer userId){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Collect> collects = collectMapper.selectList(queryWrapper);
        List<Book> collectBooks = new ArrayList<>();
        for (Collect collect : collects) {
            String isbn = collect.getIsbn();
            QueryWrapper<Book> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("isbn", isbn);
            Book book = bookMapper.selectOne(queryWrapper1);
            collectBooks.add(book);
        }
        return collectBooks;
    }

    @Override
    public List<Book> getBooksByCategory(Integer level, Integer id) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (level.equals(1)) {
            queryWrapper.eq("categorylevelone", id);
        } else {
            queryWrapper.eq("categoryleveltwo", id);
        }
        return bookMapper.selectList(queryWrapper);
    }

    @Override
    public List<Book> findListByMap(Map<String, Object> queryMap) {
        return bookMapper.findListByMap(queryMap);
    }

    @Override
    public Integer findListCount(Map<String, Object> queryMap) {
        return bookMapper.findListCount(queryMap);
    }

    @Override
    public String adminAddBook(Book book) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isbn", book.getIsbn());
        Book one = bookMapper.selectOne(queryWrapper);
        if(one != null){
            return "图书已存在";
        }
        int insert = bookMapper.insert(book);
        if(insert != 1){
            return "添加图书失败";
        }
        return "添加图书成功";
    }

    @Override
    public String adminEditBook(Book book){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isbn", book.getIsbn());
        Book one = bookMapper.selectOne(queryWrapper);
        if(one == null){
            return "图书不存在";
        }
        int row = bookMapper.update(book, queryWrapper);
        if (row == 1){
            return "修改图书成功";
        }else{
            return "修改图书失败";
        }
    }
}
