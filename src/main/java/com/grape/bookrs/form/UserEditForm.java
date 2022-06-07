package com.grape.bookrs.form;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;

@Data
public class UserEditForm {
    @NotEmpty(message = "用户名不能为空")
    private String name;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private Integer gender;
    private String birthday;
    private String address;
    private String email;
    private String mobile;
    private String profile;
}
