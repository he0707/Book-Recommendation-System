package com.grape.bookrs.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grape.bookrs.entity.Book;
import com.grape.bookrs.entity.Category;
import com.grape.bookrs.entity.Rating;
import com.grape.bookrs.entity.User;
import com.grape.bookrs.exception.MMallException;
import com.grape.bookrs.result.ResponseEnum;
import com.grape.bookrs.service.BookService;
import com.grape.bookrs.service.CategoryService;
import com.grape.bookrs.service.RatingService;
import com.grape.bookrs.service.UserService;
import com.grape.bookrs.utils.Recommend;
import com.grape.bookrs.vo.CategoryVo;
import com.grape.bookrs.vo.RatingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
@Controller
@Slf4j
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RatingService ratingService;
    @Autowired
    UserService userService;

    /**
     * 图书推荐 协同过滤-基于用户
     * @param session
     * @return
     */
    @GetMapping("/recBookList")
    public ModelAndView recBookList(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recBookList");
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【图书推荐】用户未登录");
            throw new MMallException(ResponseEnum.LOGIN_NULL);
        }
        Boolean isRating = false;
        for (Rating rating : ratingService.list()) {
            if (rating.getUserId().equals(user.getId())) {
                isRating = true;
            }
        }
        if (!isRating) {
            modelAndView.addObject("isRating", 0);
            modelAndView.addObject("recommendationBooks", null);
            return modelAndView;
        }
        modelAndView.addObject("isRating", 1);
        List<Book> recommendBooks = Recommend.recommend(user.getId(), userService.list(), bookService.list(), ratingService.list());
        modelAndView.addObject("recommendationBooks", recommendBooks);
        return modelAndView;
    }

    /**
     * 分类查找书籍
     * @param level
     * @param id
     * @return
     */
    @GetMapping("/bookList/{level}/{id}")
    public ModelAndView redirectBookList(@PathVariable("level") String level,
                                         @PathVariable("id") String id){
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
        modelAndView.addObject("Books", bookService.getBooksByCategory(Integer.valueOf(level),Integer.valueOf(id)));
        return modelAndView;
    }

    /**
     * 搜索图书
     * @param keyWord
     * @return
     */
    @GetMapping("/book/search")
    public ModelAndView redirectBookList(String keyWord){
        if(keyWord == null){
            log.info("【商品搜索】参数为空");
            throw new MMallException(ResponseEnum.PARAMETER_NULL);
        }
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
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", keyWord);
        modelAndView.addObject("Books", bookService.list(queryWrapper));
        return modelAndView;
    }

    @GetMapping("/book/bookDetail/{isbn}")
    public ModelAndView RedirectBookDetail(@PathVariable("isbn") String isbn, HttpSession session){
        String pattern = "yyyy年MM月dd日 HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookDetail");
        modelAndView.addObject("book", bookService.getById(isbn));
        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("isbn", isbn);
        List<Rating> ratingList = ratingService.list(queryWrapper);

        User loginUser = (User) session.getAttribute("user");
        List<RatingVo> ratingVoList = new ArrayList<>();
        int isRating = 0;
        for (Rating rating : ratingList) {
            if (loginUser != null && rating.getUserId().equals(loginUser.getId())){
                isRating = 1;
            }
            RatingVo ratingVo = new RatingVo();
            ratingVo.setId(rating.getId());
            ratingVo.setUserId(rating.getUserId());
            User user = userService.getById(rating.getUserId());
            ratingVo.setUserName(user.getName());
            ratingVo.setUserProfile(user.getProfile());
            ratingVo.setIsbn(rating.getIsbn());
            ratingVo.setRating(rating.getRating());
            ratingVo.setRemark(rating.getRemark());
            ratingVo.setCreateTime(rating.getCreateTime().format(dateTimeFormatter));
            ratingVo.setUpdateTime(rating.getUpdateTime().format(dateTimeFormatter));
            ratingVoList.add(ratingVo);
        }
        //判断用户是否评分过
        modelAndView.addObject("isRating", isRating);
        modelAndView.addObject("ratingVoList", ratingVoList);
        return modelAndView;
    }

    /**
     * 管理员查询图书（包括模糊查询）
     * @param isbn
     * @param title
     * @param author
     * @param publisher
     * @param categorylevelone
     * @param categoryleveltwo
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/adminPage/getAllBookToAdmin")
    @ResponseBody
    public String getAllBookToAdmin(@RequestParam(value = "isbn", required = false, defaultValue = "")String isbn,
                                       @RequestParam(value = "title", required = false, defaultValue = "")String title,
                                       @RequestParam(value = "author", required = false, defaultValue = "")String author,
                                       @RequestParam(value = "publisher", required = false, defaultValue = "")String publisher,
                                       @RequestParam(value = "categorylevelone", required = false, defaultValue = "")String categorylevelone,
                                       @RequestParam(value = "categoryleveltwo", required = false, defaultValue = "")String categoryleveltwo,
                                       @RequestParam("page") Integer page,
                                       @RequestParam("limit") Integer limit){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("isbn", isbn);
        queryMap.put("title", title);
        queryMap.put("author", author);
        queryMap.put("publisher", publisher);
        queryMap.put("categorylevelone", categorylevelone);
        queryMap.put("categoryleveltwo", categoryleveltwo);
        queryMap.put("page", page);
        queryMap.put("limit", limit);
        List<Book> books = bookService.findListByMap(queryMap);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", bookService.findListCount(queryMap));
        jsonObject.put("data", books);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/adminPage/uploadBookImage")
    @ResponseBody
    public JSON uploadUserProfile(MultipartFile file, HttpServletRequest request){
        JSONObject json = new JSONObject();
        //路径
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/img/bookImage";//上传到这个文件夹
        File file1 = new File(filePath);
        //如果没有的话创建一个
        if (!file1.exists()) {
            file1.mkdirs();
        }

        //路径+文件名
        //文件名：file.getOriginalFilename()
        String finalFilePath = filePath + "/" + file.getOriginalFilename().trim();
        File desFile = new File(finalFilePath);
        if (desFile.exists()) {
            desFile.delete();
        }
        try {
            file.transferTo(desFile);
            json.put("code", 0);
            //将文件名放在msg中，前台提交表单时需要用到
            json.put("msg", file.getOriginalFilename().trim());

            JSONObject json2 = new JSONObject();
            json2.put("src", finalFilePath);
            json2.put("title", "");
            json.put("Data", json2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            json.put("code", 1);
        }
        System.out.println(json);
        return json;
    }

    /**
     * 跳转到管理员添加图书页面
     * @return
     */
    @GetMapping("/adminPage/admin_book_add.html")
    public ModelAndView redirectAdminBookAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/admin_book_add.html");
        modelAndView.addObject("categorylevelone", categoryService.findCategoryByType(1));
        return modelAndView;
    }

    /**
     * 管理员添加图书
     * @param isbn
     * @param title
     * @param author
     * @param yearOfPublication
     * @param publisher
     * @param description
     * @param categorylevelone
     * @param categoryleveltwo
     * @param image
     * @return
     */
    @PostMapping("/adminPage/book/add/{isbn}/{title}/{author}/{yearOfPublication}/{publisher}/{description}/{categorylevelone}/{categoryleveltwo}/{image}")
    @ResponseBody
    public String adminAddBook(@PathVariable("isbn") String isbn,
                               @PathVariable("title") String title,
                               @PathVariable("author") String author,
                               @PathVariable("yearOfPublication") String yearOfPublication,
                               @PathVariable("publisher") String publisher,
                               @PathVariable("description") String description,
                               @PathVariable("categorylevelone") String categorylevelone,
                               @PathVariable("categoryleveltwo") String categoryleveltwo,
                               @PathVariable("image") String image){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(Integer.valueOf(yearOfPublication));
        book.setPublisher(publisher);
        book.setDescription(description);
        book.setCategorylevelone(Integer.valueOf(categorylevelone));
        book.setCategoryleveltwo(Integer.valueOf(categoryleveltwo));
        book.setImage(image);
        return bookService.adminAddBook(book);
    }

    /**
     * 跳转到管理员修改图书页面
     * @param isbn
     * @return
     */
    @GetMapping("/adminPage/admin_book_edit.html/{isbn}")
    public ModelAndView redirectAdminBookEdit(@PathVariable("isbn") String isbn){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/admin_book_edit.html");
        modelAndView.addObject("categorylevelone", categoryService.findCategoryByType(1));
        Book book = bookService.getById(isbn);
        modelAndView.addObject("book", book);
        modelAndView.addObject("categoryleveltwo", categoryService.findCategoryByParentId(book.getCategorylevelone()));
        return modelAndView;
    }

    /**
     * 管理员修改图书
     * @param isbn
     * @param title
     * @param author
     * @param yearOfPublication
     * @param publisher
     * @param description
     * @param categorylevelone
     * @param categoryleveltwo
     * @param image
     * @return
     */
    @PostMapping("/adminPage/book/edit/{isbn}/{title}/{author}/{yearOfPublication}/{publisher}/{description}/{categorylevelone}/{categoryleveltwo}/{image}")
    @ResponseBody
    public String adminEditBook(@PathVariable("isbn") String isbn,
                               @PathVariable("title") String title,
                               @PathVariable("author") String author,
                               @PathVariable("yearOfPublication") String yearOfPublication,
                               @PathVariable("publisher") String publisher,
                               @PathVariable("description") String description,
                               @PathVariable("categorylevelone") String categorylevelone,
                               @PathVariable("categoryleveltwo") String categoryleveltwo,
                               @PathVariable("image") String image){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(Integer.valueOf(yearOfPublication));
        book.setPublisher(publisher);
        book.setDescription(description);
        book.setCategorylevelone(Integer.valueOf(categorylevelone));
        book.setCategoryleveltwo(Integer.valueOf(categoryleveltwo));
        book.setImage(image);
        return bookService.adminEditBook(book);
    }

    /**
     * 管理员删除一本图书
     * @param isbn
     * @return
     */
    @PostMapping("/adminPage/book/delete/{isbn}")
    @ResponseBody
    public String adminDeleteBook(@PathVariable("isbn") String isbn){
        boolean b = bookService.removeById(isbn);
        if (b == false){
            return "删除失败";
        }else{
            return "删除失败";
        }
    }

    /**
     * 管理员删除勾选的多本图书
     * @param books
     * @return
     */
    @PostMapping("/adminPage/book/deleteCheck")
    @ResponseBody
    public String adminDeleteCheckBook(@RequestBody Book[] books){
        boolean b = false;
        for (Book book : books){
            b = bookService.removeById(book.getIsbn());
            if (b == false){
                return "删除失败";
            }
        }
        return "删除成功";
    }

}

