package com.grape.bookrs.controller;


import com.alibaba.fastjson.JSONObject;
import com.grape.bookrs.entity.Category;
import com.grape.bookrs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ClientInfoStatus;
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
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 管理员查询图书分类（包括模糊查询）
     * @return
     */
    @GetMapping("/adminPage/getAllCategoryToAdmin")
    @ResponseBody
    public String getAllCategoryToAdmin(@RequestParam(value = "id", required = false, defaultValue = "")String id,
                                        @RequestParam(value = "name", required = false, defaultValue = "")String name,
                                        @RequestParam(value = "parentId", required = false, defaultValue = "")String parentId,
                                        @RequestParam(value = "type", required = false, defaultValue = "")String type,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("limit") Integer limit){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("id", id);
        queryMap.put("name", name);
        queryMap.put("parentId", parentId);
        queryMap.put("type", type);
        queryMap.put("page", page);
        queryMap.put("limit", limit);
        List<Category> categories = categoryService.findListByMap(queryMap);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", categoryService.findListCount(queryMap));
        jsonObject.put("data", categories);
        System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    /**
     * 管理员添加图书分类
     * @param name
     * @param parentId
     * @param type
     * @return
     */
    @PostMapping("/adminPage/category/add/{name}/{parentId}/{type}")
    @ResponseBody
    public String adminAddCategory(@PathVariable("name") String name,
                                   @PathVariable("parentId") String parentId,
                                   @PathVariable("type") String type){

        Category category = new Category();
        category.setName(name);
        category.setParentId(Integer.valueOf(parentId));
        category.setType(Integer.valueOf(type));
        return categoryService.adminAddCategory(category);
    }

    /**
     * 跳转到管理员修改图书分类页面
     * @param id
     * @return
     */
    @GetMapping("/adminPage/admin_category_edit.html/{id}")
    public ModelAndView redirectAdminCategoryEdit(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/admin_category_edit.html");
        modelAndView.addObject("categoryList1", categoryService.findCategoryByType(1));
        modelAndView.addObject("categoryList2", categoryService.findCategoryByType(2));
        modelAndView.addObject("category", categoryService.getById(id));
        return modelAndView;
    }

    /**
     * 管理员修改图书分类
     * @param id
     * @param name
     * @param parentId
     * @param type
     * @return
     */
    @PostMapping("/adminPage/category/edit/{id}/{name}/{parentId}/{type}")
    @ResponseBody
    public String adminEditCategory(@PathVariable("id") String id,
                                    @PathVariable("name") String name,
                                    @PathVariable("parentId") String parentId,
                                    @PathVariable("type") String type){

        Category category = new Category();
        category.setId(Integer.valueOf(id));
        category.setName(name);
        category.setParentId(Integer.valueOf(parentId));
        category.setType(Integer.valueOf(type));
        return categoryService.adminEditCategory(category);
    }

    /**
     * 根据选择的类型获取图书目录
     * @param type
     * @return
     */
    @GetMapping("/adminPage/getCategoryByType/{type}")
    @ResponseBody
    public List<Category> getCategoryByType(@PathVariable("type") Integer type){
        if (type == 1){
            return null;
        }
        return categoryService.findCategoryByType(type-1);
    }

    /**
     * 根据选择的第一分类获取第二分类
     * @param parentId
     * @return
     */
    @GetMapping(value = {"/adminPage/getCategoryByParentId","/adminPage/getCategoryByParentId/{parentId}"})
    @ResponseBody
    public List<Category> getCategoryByarentId(@PathVariable(value = "parentId", required = false) Integer parentId){
        if (parentId == null){
            return null;
        }
        return categoryService.findCategoryByParentId(parentId);
    }


    /**
     * 管理员删除一个图书分类
     * @param id
     * @return
     */
    @PostMapping("/adminPage/category/delete/{id}")
    @ResponseBody
    public String adminDeleteUser(@PathVariable("id") String id){
        boolean b = categoryService.removeById(Integer.valueOf(id));
        if (b == true){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    /**
     * 管理员删除勾选的多个图书分类
     * @param categorys
     * @return
     */
    @PostMapping("/adminPage/category/deleteCheck")
    public String adminDeleteCheckCategory(@RequestBody Category[] categorys){
        boolean b = false;
        for (Category category : categorys){
            b = categoryService.removeById(category.getId());
            if (b == false){
                return "删除失败";
            }
        }
        return "删除成功";
    }
}

