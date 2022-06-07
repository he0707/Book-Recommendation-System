package com.grape.bookrs.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingVo {
    private Integer id;
    private Integer userId;
    private String userName;
    private String userProfile;
    private String isbn;
    private Integer rating;
    private String remark;
    private String createTime;
    private String updateTime;
}
