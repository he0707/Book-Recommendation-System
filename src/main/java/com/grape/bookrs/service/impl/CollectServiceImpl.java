package com.grape.bookrs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grape.bookrs.entity.Collect;
import com.grape.bookrs.mapper.CollectMapper;
import com.grape.bookrs.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    CollectMapper collectMapper;

    @Override
    public String addCollect(Collect collect){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("isbn", collect.getIsbn());
        Collect one = collectMapper.selectOne(queryWrapper);
        if(one != null){
            return "已收藏";
        }
        int insert = collectMapper.insert(collect);
        if(insert != 1){
            return "收藏失败";
        }
        return "收藏成功";
    }

    @Override
    public String deleteCollect(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("isbn", collect.getIsbn());
        Collect one = collectMapper.selectOne(queryWrapper);
        if(one == null){
            return "未收藏";
        }
        int delete = collectMapper.deleteById(one.getId());
        if(delete != 1){
            return "取消失败";
        }
        return "取消成功";
    }
}
