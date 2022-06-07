package com.grape.bookrs.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "isbn")
    private String isbn;

    private String title;

    private String author;

    private Integer yearOfPublication;

    private String publisher;

    private String description;

    private Integer categorylevelone;

    private Integer categoryleveltwo;

    private String image;


}
