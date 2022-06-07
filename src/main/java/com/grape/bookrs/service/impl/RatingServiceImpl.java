package com.grape.bookrs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grape.bookrs.entity.Rating;
import com.grape.bookrs.mapper.RatingMapper;
import com.grape.bookrs.service.RatingService;
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
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating> implements RatingService {
    @Autowired
    RatingMapper ratingMapper;

    @Override
    public String addRating(Rating rating) {
        int insert = ratingMapper.insert(rating);
        if(insert != 1){
            return "添加评分失败";
        }
        return "添加评分成功";
    }

    @Override
    public List<Rating> findListByMap(Map<String, Object> queryMap) {
        return ratingMapper.findListByMap(queryMap);
    }

    @Override
    public Integer findListCount(Map<String, Object> queryMap) {
        return ratingMapper.findListCount(queryMap);
    }

    @Override
    public String adminEditRating(Rating rating) {
        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", rating.getId());
        Rating one = ratingMapper.selectOne(queryWrapper);
        if(one == null){
            return "评分不存在";
        }
        int row = ratingMapper.update(rating, queryWrapper);
        if (row == 1){
            return "修改评分成功";
        }else{
            return "修改评分失败";
        }
    }
}
