package com.grape.bookrs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grape.bookrs.entity.Collect;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
public interface CollectService extends IService<Collect> {
    public String addCollect(Collect collect);
    public String deleteCollect(Collect collect);
}
