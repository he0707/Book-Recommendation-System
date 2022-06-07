package com.grape.bookrs.controller;


import com.alibaba.fastjson.JSONObject;
import com.grape.bookrs.entity.Rating;
import com.grape.bookrs.entity.User;
import com.grape.bookrs.exception.MMallException;
import com.grape.bookrs.result.ResponseEnum;
import com.grape.bookrs.service.BookService;
import com.grape.bookrs.service.RatingService;
import com.grape.bookrs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
public class RatingController {
    @Autowired
    RatingService ratingService;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @RequestMapping("/rating/add/{isbn}/{rating}/{remark}")
    public String addRating(@PathVariable(value = "isbn", required = false)String isbn,
                            @PathVariable(value = "rating", required = false)String rating,
                            @PathVariable(value = "remark", required = false)String remark,
                            HttpSession session){
        System.out.println(rating);
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【添加评分】用户未登录");
            return "redirect:/loginAndRegister";
        }
        Integer userId = user.getId();
        Rating rating1 = new Rating();
        rating1.setUserId(userId);
        rating1.setIsbn(isbn);
        rating1.setRating(Integer.valueOf(rating));
        rating1.setRemark(remark);
        ratingService.addRating(rating1);
        return "redirect:/book/bookDetail/"+isbn;
    }

    @RequestMapping("/rating/edit/{id}/{isbn}/{rating}/{remark}")
    public String editRating(@PathVariable(value = "id", required = false)String id,
                             @PathVariable(value = "isbn", required = false)String isbn,
                             @PathVariable(value = "rating", required = false)String rating,
                             @PathVariable(value = "remark", required = false)String remark,
                             HttpSession session){
        System.out.println(rating);
        ModelAndView modelAndView = new ModelAndView();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【修改评分】用户未登录");
            return "redirect:/loginAndRegister";
        }
        Integer userId = user.getId();
        Rating rating1 = new Rating();
        rating1.setId(Integer.valueOf(id));
        rating1.setUserId(userId);
        rating1.setIsbn(isbn);
        rating1.setRating(Integer.valueOf(rating));
        rating1.setRemark(remark);
        ratingService.updateById(rating1);
        return "redirect:/book/bookDetail/"+isbn;
    }

    @GetMapping("/adminPage/getAllRatingToAdmin")
    @ResponseBody
    public String getAllRatingToAdmin(@RequestParam(value = "id", required = false, defaultValue = "")String id,
                                      @RequestParam(value = "userId", required = false, defaultValue = "")String userId,
                                      @RequestParam(value = "isbn", required = false, defaultValue = "")String isbn,
                                      @RequestParam(value = "rating", required = false, defaultValue = "")String rating,
                                      @RequestParam("page") Integer page,
                                      @RequestParam("limit") Integer limit) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("id", id);
        queryMap.put("userId", userId);
        queryMap.put("isbn", isbn);
        queryMap.put("rating", rating);
        queryMap.put("page", page);
        queryMap.put("limit", limit);
        List<Rating> ratings = ratingService.findListByMap(queryMap);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", ratingService.findListCount(queryMap));
        jsonObject.put("data", ratings);
        return jsonObject.toJSONString();
    }


    @GetMapping("/adminPage/admin_rating_edit.html/{id}")
    public ModelAndView redirectAdminBookEdit(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/admin_rating_edit.html");
        Rating rating = ratingService.getById(id);
        modelAndView.addObject("rating", rating);
        modelAndView.addObject("user", userService.getById(rating.getUserId()));
        modelAndView.addObject("book", bookService.getById(rating.getIsbn()));
        return modelAndView;
    }

    @PostMapping("/adminPage/rating/edit/{id}/{rating}/{remark}")
    @ResponseBody
    public String adminEditRating(@PathVariable("id") String id,
                                  @PathVariable("rating") String rating,
                                  @PathVariable("remark") String remark){
        Rating rating1 = ratingService.getById(id);
        rating1.setRating(Integer.valueOf(rating));
        rating1.setRemark(remark);
        return ratingService.adminEditRating(rating1);
    }

    @PostMapping("/adminPage/rating/delete/{id}")
    @ResponseBody
    public String adminDeleteRating(@PathVariable("id") String id){
        boolean b = ratingService.removeById(id);
        if (b == true){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    @PostMapping("/adminPage/rating/deleteCheck")
    @ResponseBody
    public String adminDeleteCheckRating(@RequestBody Rating[] ratings){
        boolean b = false;
        for (Rating rating : ratings){
            b = ratingService.removeById(rating.getId());
            if (b == false){
                return "删除失败";
            }
        }
        return "删除成功";
    }
}

