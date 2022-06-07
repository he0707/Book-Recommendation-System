package com.grape.bookrs.result;

public enum ResponseEnum {
    USER_INFO_NULL(300,"用户信息不能为空"),
    LOGIN_NAME_NULL(301,"登录名不能为空"),
    LOGIN_PWD_NULL(302,"登录密码不能为空"),
    LOGIN_TYPE_NULL(303,"登录类型不能为空"),
    REGISTER_NAME_NULL(304,"注册名不能为空"),
    REGISTER_PWD_NULL(305,"注册密码不能为空"),
    REGISTER_VERIFY_PWD_NULL(306,"注册确认密码不能为空"),
    USER_REGISTER_ERROR(307,"用户注册失败"),
    USERNAME_EXISTS(308,"用户名已存在"),
    USERNAME_NOT_EXISTS(309,"用户名不存在"),
    PASSWORD_ERROR(310,"密码错误"),
    USERTYPE_ERROR(311,"用户类型错误"),
    PARAMETER_NULL(312,"参数为空"),
    LOGIN_NULL(313,"用户未登录");


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
