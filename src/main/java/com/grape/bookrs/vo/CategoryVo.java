package com.grape.bookrs.vo;

import com.grape.bookrs.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer type;
    private List<Category> children;
}
