package com.grape.bookrs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.grape.bookrs.entity.Book;
import com.grape.bookrs.entity.Rating;
import com.grape.bookrs.entity.User;
import com.grape.bookrs.exception.MMallException;
import com.grape.bookrs.form.UserLoginForm;
import com.grape.bookrs.form.UserRegisterForm;
import com.grape.bookrs.mapper.RatingMapper;
import com.grape.bookrs.mapper.UserMapper;
import com.grape.bookrs.result.ResponseEnum;
import com.grape.bookrs.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grape.bookrs.utils.MD5Util;
import com.grape.bookrs.vo.PopUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public User register(UserRegisterForm userRegisterForm) {
        //用户名是否可用
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userRegisterForm.getRegisterName());
        User one = this.userMapper.selectOne(queryWrapper);
        if(one != null){
            log.info("【用户注册】用户名已存在");
            throw new MMallException(ResponseEnum.USERNAME_EXISTS);
        }
        //存储数据
        User user = new User();
        user.setName(userRegisterForm.getRegisterName());
        user.setPassword(MD5Util.getSaltMD5(userRegisterForm.getRegisterPwd()));
        user.setGender(0);
        user.setType(0);
        user.setProfile("akarin.jpg");
        int insert = this.userMapper.insert(user);
        if(insert != 1){
            log.info("【用户注册】注册用户失败");
            throw new MMallException(ResponseEnum.USER_REGISTER_ERROR);
        }
        return user;
    }

    @Override
    public User login(UserLoginForm userLoginForm) {
        //判断用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userLoginForm.getLoginName());
        User user = this.userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("【用户登录】用户名不存在");
            throw new MMallException(ResponseEnum.USERNAME_NOT_EXISTS);
        }
        //判断密码是否正确
        boolean saltverifyMD5 = MD5Util.getSaltverifyMD5(userLoginForm.getLoginPwd(), user.getPassword());
        if(!saltverifyMD5){
            log.info("【用户登录】密码错误");
            throw new MMallException(ResponseEnum.PASSWORD_ERROR);
        }
        return user;
    }


    public List<PopUserVo> getPopUsers() {
        List<PopUserVo> popUserVoList = new ArrayList<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            QueryWrapper<Rating> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("user_id", user.getId());
            int num = ratingMapper.selectList(queryWrapper1).size();
            PopUserVo popUserVo = new PopUserVo();
            popUserVo.setId(user.getId());
            popUserVo.setName(user.getName());
            popUserVo.setGender(user.getGender());
            popUserVo.setProfile(user.getProfile());
            popUserVo.setRatingNum(num);
            popUserVoList.add(popUserVo);
        }
        popUserVoList.sort((y, x) -> Integer.compare(x.getRatingNum(), y.getRatingNum()));
        return popUserVoList.subList(0, 8);
    }

    /**
     * 管理员添加用户
     * @param user
     * @return
     */
    @Override
    public String adminAddUser(User user) {
        //用户名是否可用
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        User one = userMapper.selectOne(queryWrapper);
        if(one != null){
            return "用户名已存在";
        }
        int insert = userMapper.insert(user);
        if(insert != 1){
            return "添加用户失败";
        }
        return "添加用户成功";
    }

    /**
     * 管理员修改用户
     * @param user
     * @return
     */
    @Override
    public String adminEditUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        User one = userMapper.selectOne(queryWrapper);
        if(one == null){
            return "用户不存在";
        }
        int row = userMapper.update(user, queryWrapper);
        if (row == 1){
            return "修改用户成功";
        }else{
            return "修改用户失败";
        }
    }

    /**
     * 管理员查询用户（包括模糊查询）
     * @param queryMap
     * @return
     */
    @Override
    public List<User> findListByMap(Map<String, Object> queryMap) {
        return userMapper.findListByMap(queryMap);
    }

    @Override
    public Integer findListCount(Map<String, Object> queryMap) {
        return userMapper.findListCount(queryMap);
    }
}
