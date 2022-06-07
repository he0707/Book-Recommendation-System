package com.grape.bookrs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grape.bookrs.entity.Category;
import com.grape.bookrs.mapper.CategoryMapper;
import com.grape.bookrs.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> findListByMap(Map<String, Object> queryMap) {
        return categoryMapper.findListByMap(queryMap);
    }

    @Override
    public Integer findListCount(Map<String, Object> queryMap) {
        return categoryMapper.findListCount(queryMap);
    }

    @Override
    public List<Category> findCategoryByType(Integer type) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        queryWrapper.eq("type", type.intValue());
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<Category> findCategoryByParentId(Integer parentId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        queryWrapper.eq("parent_id", parentId.intValue());
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public String adminEditCategory(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", category.getId());
        Category one = categoryMapper.selectOne(queryWrapper);
        if(one == null){
            return "图书分类不存在";
        }
        int row = categoryMapper.update(category, queryWrapper);
        if (row == 1){
            return "修改图书分类成功";
        }else{
            return "修改图书分类失败";
        }
    }

    @Override
    public String adminAddCategory(Category category) {
        int insert = categoryMapper.insert(category);
        if(insert != 1){
            return "添加图书分类失败";
        }
        return "添加图书分类成功";
    }
}
