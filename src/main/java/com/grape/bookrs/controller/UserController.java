package com.grape.bookrs.controller;


import com.alibaba.fastjson.JSON;
import com.grape.bookrs.entity.User;
import com.grape.bookrs.exception.MMallException;
import com.grape.bookrs.form.UserEditForm;
import com.grape.bookrs.form.UserLoginForm;
import com.grape.bookrs.form.UserRegisterForm;
import com.grape.bookrs.result.ResponseEnum;
import com.grape.bookrs.service.BookService;
import com.grape.bookrs.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.grape.bookrs.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    /**
     * 注册
     * @param userRegisterForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()) {
            log.info("【用户注册】用户信息不能为空");
            throw new MMallException(ResponseEnum.USER_INFO_NULL);
        }
        User register = this.userService.register(userRegisterForm);
        if(register == null){
            log.info("【用户注册】注册用户失败");
            throw new MMallException(ResponseEnum.USER_REGISTER_ERROR);
        }
        session.setAttribute("user", register);
        return "redirect:/index";
    }

    /**
     * 登录
     * @param userLoginForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, HttpSession session){
        //非空校验
        if(bindingResult.hasErrors()){
            log.info("【用户登录】用户信息不能为空");
            throw new MMallException(ResponseEnum.USER_INFO_NULL);
        }
        User login = this.userService.login(userLoginForm);
        if (login.getType() != Integer.valueOf(userLoginForm.getLoginType())) {
            log.info("【用户登录】用户类型错误");
            throw new MMallException(ResponseEnum.USERTYPE_ERROR);
        }
        session.setAttribute("user", login);
        if (login.getType() == 0) {
            return "redirect:/index";
        }else {
            return "redirect:/adminMain";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }

    /**
     * 管理员查询用户（包括模糊查询）
     * @param id
     * @param name
     * @param gender
     * @param address
     * @return
     */
    @GetMapping("/adminPage/getAllUserToAdmin")
    @ResponseBody
    public String getAllUserToAdmin(@RequestParam(value = "id", required = false, defaultValue = "")String id,
                                    @RequestParam(value = "name", required = false, defaultValue = "")String name,
                                    @RequestParam(value = "gender", required = false, defaultValue = "")String gender,
                                    @RequestParam(value = "address", required = false, defaultValue = "")String address,
                                    @RequestParam("page") Integer page,
                                    @RequestParam("limit") Integer limit){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("id", id);
        queryMap.put("name", name);
        queryMap.put("gender", gender);
        queryMap.put("address", address);
        queryMap.put("page", page);
        queryMap.put("limit", limit);
        List<User> users = userService.findListByMap(queryMap);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", userService.findListCount(queryMap));
        jsonObject.put("data", users);
        return jsonObject.toJSONString();
    }

    /**
     * 跳转到用户详情页
     * @return
     */
    @GetMapping("/user/userInfo")
    public ModelAndView RedirectUserInfo(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【用户详情】用户未登录");
            throw new MMallException(ResponseEnum.LOGIN_NULL);
        }
        modelAndView.addObject("collectBooks",bookService.getBooksByUserId(user.getId()));
        return modelAndView;
    }

    @PostMapping("/user/edit")
    public String editUser(@Valid UserEditForm userEditForm, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            log.info("【用户修改】用户未登录");
            throw new MMallException(ResponseEnum.LOGIN_NULL);
        }
        user.setName(userEditForm.getName());
        if (MD5Util.getSaltverifyMD5(userEditForm.getPassword(),user.getPassword())) {
            user.setPassword(MD5Util.getSaltMD5(userEditForm.getPassword()));
        }
        user.setGender(userEditForm.getGender());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setBirthday(LocalDate.parse(userEditForm.getBirthday(), fmt));
        user.setAddress(userEditForm.getAddress());
        user.setEmail(userEditForm.getEmail());
        user.setMobile(userEditForm.getMobile());
        if (!userEditForm.getProfile().equals("")) {
            user.setProfile(userEditForm.getProfile());
        }
        userService.updateById(user);
        return "redirect:/user/userInfo";
    }

    /**
     * 管理员添加用户
     * @param name
     * @param password
     * @param gender
     * @param birthday
     * @param address
     * @param email
     * @param mobile
     * @param profile
     * @param type
     * @return
     */
    @PostMapping("/adminPage/user/add/{name}/{password}/{gender}/{birthday}/{address}/{email}/{mobile}/{profile}/{type}")
    @ResponseBody
    public String adminAddUser(@PathVariable("name") String name,
                               @PathVariable("password") String password,
                               @PathVariable("gender") String gender,
                               @PathVariable("birthday") String birthday,
                               @PathVariable("address") String address,
                               @PathVariable("email") String email,
                               @PathVariable("mobile") String mobile,
                               @PathVariable("profile") String profile,
                               @PathVariable("type") String type){
        User user = new User();
        user.setName(name);
        user.setPassword(MD5Util.getSaltMD5(password));
        user.setGender(Integer.valueOf(gender));
        user.setBirthday(LocalDate.parse(birthday));
        user.setAddress(address);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setProfile(profile);
        user.setType(Integer.valueOf(type));
        return userService.adminAddUser(user);
    }

    @RequestMapping("/adminPage/uploadUserProfile")
    @ResponseBody
    public JSON uploadUserProfile(MultipartFile file, HttpServletRequest request){
        JSONObject json = new JSONObject();
        //路径
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/img/userProfile";//上传到这个文件夹
        File file1 = new File(filePath);
        //如果没有的话创建一个
        if (!file1.exists()) {
            file1.mkdirs();
        }

        //路径+文件名
        //文件名：file.getOriginalFilename()
        String finalFilePath = filePath + "/" + file.getOriginalFilename().trim();
        File desFile = new File(finalFilePath);
        if (desFile.exists()) {
            desFile.delete();
        }
        try {
            file.transferTo(desFile);
            json.put("code", 0);
            //将文件名放在msg中，前台提交表单时需要用到
            json.put("msg", file.getOriginalFilename().trim());

            JSONObject json2 = new JSONObject();
            json2.put("src", finalFilePath);
            json2.put("title", "");
            json.put("Data", json2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            json.put("code", 1);
        }
        System.out.println(json);
        return json;
    }

    /**
     * 跳转到管理员修改用户页面
     * @param id
     * @return
     */
    @GetMapping("/adminPage/admin_user_edit.html/{id}")
    public ModelAndView redirectAdminUserEdit(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage/admin_user_edit.html");
        modelAndView.addObject("user", userService.getById(id));
        return modelAndView;
    }

    /**
     * 管理员修改用户
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param birthday
     * @param address
     * @param email
     * @param mobile
     * @param profile
     * @param type
     * @return
     */
    @PostMapping("/adminPage/user/edit/{id}/{name}/{password}/{gender}/{birthday}/{address}/{email}/{mobile}/{profile}/{type}")
    @ResponseBody
    public String adminEditUser(@PathVariable("id") String id,
                                @PathVariable("name") String name,
                                @PathVariable("password") String password,
                                @PathVariable("gender") String gender,
                                @PathVariable("birthday") String birthday,
                                @PathVariable("address") String address,
                                @PathVariable("email") String email,
                                @PathVariable("mobile") String mobile,
                                @PathVariable("profile") String profile,
                                @PathVariable("type") String type){

        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setName(name);
        user.setPassword(MD5Util.getSaltMD5(password));
        user.setGender(Integer.valueOf(gender));
        user.setBirthday(LocalDate.parse(birthday));
        user.setAddress(address);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setProfile(profile);
        user.setType(Integer.valueOf(type));
        return userService.adminEditUser(user);
    }

    /**
     * 管理员删除一个用户
     * @param id
     * @return
     */
    @PostMapping("/adminPage/user/delete/{id}")
    @ResponseBody
    public String adminDeleteUser(@PathVariable("id") String id){
        boolean b = userService.removeById(Integer.valueOf(id));
        if (b == true) {
            return "删除成功";
        } else{
            return "删除失败";
        }
    }

    /**
     * 管理员删除勾选的多个用户
     * @param users
     * @return
     */
    @PostMapping("/adminPage/user/deleteCheck")
    @ResponseBody
    public String adminDeleteCheckUser(@RequestBody User[] users){
        boolean b = false;
        for (User user : users){
            b = userService.removeById(user.getId());
            if (b == false){
                return "删除失败";
            }
        }
        return "删除成功";
    }

    /**
     * 管理员修改密码
     * @param old_password
     * @param new_password
     * @param again_password
     * @param session
     * @return
     */
    @PostMapping("/adminPage/user/changepwd/{old_password}/{new_password}/{again_password}")
    @ResponseBody
    public String adminChangePwd(@PathVariable("old_password") String old_password,
                                 @PathVariable("new_password") String new_password,
                                 @PathVariable("again_password") String again_password,
                                 HttpSession session) {

        if (!new_password.equals(again_password)) {
            return "请输入相同的新的密码";
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.info("【用户修改】用户未登录");
            return "请先登录";
        }
        if (!MD5Util.getSaltverifyMD5(old_password, user.getPassword())) {
            return "请检查旧的密码是否正确";
        }
        user.setPassword(MD5Util.getSaltMD5(new_password));
        boolean b = userService.updateById(user);
        if (b) {
            session.setAttribute("user", user);
            return "修改密码成功";
        } else {
            return "修改密码失败";
        }
    }
}

