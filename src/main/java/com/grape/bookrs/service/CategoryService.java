package com.grape.bookrs.service;

import com.grape.bookrs.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface CategoryService extends IService<Category> {
    public List<Category> findListByMap(Map<String, Object> queryMap);
    public Integer findListCount(Map<String, Object> queryMap);
    public List<Category> findCategoryByType(Integer type);
    public List<Category> findCategoryByParentId(Integer parentId);
    public String adminEditCategory(Category category);
    public String adminAddCategory(Category category);
}
