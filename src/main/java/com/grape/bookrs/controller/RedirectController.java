package com.grape.bookrs.controller;


import com.grape.bookrs.entity.Category;
import com.grape.bookrs.service.BookService;
import com.grape.bookrs.service.CategoryService;
import com.grape.bookrs.service.RatingService;
import com.grape.bookrs.service.UserService;
import com.grape.bookrs.vo.CategoryVo;
import com.grape.bookrs.vo.PopBookVo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class RedirectController {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转到图书首页
     * @param session
     * @return
     */
    @GetMapping("/index")
    public ModelAndView redirectIndex(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("newBooks", bookService.getNewBooks());
        modelAndView.addObject("popBooks", bookService.getPopBooks());
        return modelAndView;
    }

    @GetMapping("/adminPage/pop_book.html")
    public ModelAndView redirectPopbook() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/pop_book");
        modelAndView.addObject("popBooks", bookService.getPopBooks());
        return modelAndView;
    }

    @GetMapping("/adminPage/pop_user.html")
    public ModelAndView redirectPopuser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/pop_user");
        modelAndView.addObject("popUsers", userService.getPopUsers());
        return modelAndView;
    }

    @GetMapping("/bookList")
    public ModelAndView redirectBookList(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookList");
        List<Category> categoryLevelOneList = categoryService.findCategoryByType(1);
        List<CategoryVo> categoryVoList = new ArrayList<>();

        for (Category category : categoryLevelOneList) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(category.getId());
            categoryVo.setName(category.getName());
            categoryVo.setParentId(category.getParentId());
            categoryVo.setType(category.getType());
            categoryVo.setChildren(categoryService.findCategoryByParentId(category.getId()));
            categoryVoList.add(categoryVo);
        }
        modelAndView.addObject("categoryVoList", categoryVoList);
        modelAndView.addObject("Books", bookService.list());
        return modelAndView;
    }



    @GetMapping("/adminPage/home.html")
    public ModelAndView redirectAdmin(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/home");
        modelAndView.addObject("userSum", userService.count());
        modelAndView.addObject("ratingSum", ratingService.count());
        modelAndView.addObject("bookSum", bookService.count());
        modelAndView.addObject("categorySum", categoryService.count());
        return modelAndView;
    }

    @GetMapping("/adminPage/{url}")
    public ModelAndView redirectAdmin(@PathVariable("url") String url, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/"+url);
        return modelAndView;
    }

    @GetMapping("/{url}")
    public ModelAndView redirect(@PathVariable("url") String url, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        return modelAndView;
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/index";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

}