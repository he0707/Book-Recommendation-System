package com.grape.bookrs.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserRegisterForm {
    @NotEmpty(message = "注册名不能为空")
    private String registerName;
    @NotEmpty(message = "注册密码不能为空")
    private String registerPwd;
    @NotEmpty(message = "注册确认密码不能为空")
    private String registerVerifyPwd;
}
