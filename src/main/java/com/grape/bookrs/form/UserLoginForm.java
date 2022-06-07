package com.grape.bookrs.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserLoginForm {
    @NotEmpty(message = "登录名不能为空")
    private String loginName;
    @NotEmpty(message = "登录密码不能为空")
    private String loginPwd;
    @NotEmpty(message = "登录类型不能为空")
    private String loginType;
}
