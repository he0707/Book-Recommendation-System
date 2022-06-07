package com.grape.bookrs.service;

import com.grape.bookrs.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grape.bookrs.form.UserLoginForm;
import com.grape.bookrs.form.UserRegisterForm;
import com.grape.bookrs.vo.PopBookVo;
import com.grape.bookrs.vo.PopUserVo;

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
public interface UserService extends IService<User> {
    public String adminAddUser(User user);
    public String adminEditUser(User user);
    public List<User> findListByMap(Map<String, Object> queryMap);
    public Integer findListCount(Map<String, Object> queryMap);
    public User register(UserRegisterForm userRegisterForm);
    public User login(UserLoginForm userLoginForm);
    public List<PopUserVo> getPopUsers();
}
