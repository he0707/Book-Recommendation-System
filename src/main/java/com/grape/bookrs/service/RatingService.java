package com.grape.bookrs.service;

import com.grape.bookrs.entity.Rating;
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
public interface RatingService extends IService<Rating> {
    public String addRating(Rating rating);
    public List<Rating> findListByMap(Map<String, Object> queryMap);
    public Integer findListCount(Map<String, Object> queryMap);
    public String adminEditRating(Rating rating);
}
