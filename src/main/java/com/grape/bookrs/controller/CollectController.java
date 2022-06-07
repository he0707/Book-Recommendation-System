package com.grape.bookrs.controller;

import com.grape.bookrs.entity.Collect;
import com.grape.bookrs.entity.User;
import com.grape.bookrs.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
public class CollectController {
    @Autowired
    CollectService collectService;

    @GetMapping("/collect/add/{isbn}")
    @ResponseBody
    public String addCollect(@PathVariable("isbn") String isbn,
                             HttpSession session){
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【添加收藏】用户未登录");
            return "请先登录";
        }
        Integer userId = user.getId();
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setIsbn(isbn);
        return collectService.addCollect(collect);
    }

    @GetMapping("/collect/delete/{isbn}")
    @ResponseBody
    public String deleteCollect(@PathVariable("isbn") String isbn,
                                HttpSession session){
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【删除收藏】用户未登录");
            return "请先登录";
        }
        Integer userId = user.getId();
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setIsbn(isbn);
        return collectService.deleteCollect(collect);
    }
}
