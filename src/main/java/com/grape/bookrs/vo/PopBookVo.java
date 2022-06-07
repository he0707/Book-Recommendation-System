package com.grape.bookrs.vo;

import lombok.Data;

@Data
public class PopBookVo {
    private String isbn;
    private String title;
    private String author;
    private Integer categorylevelone;
    private Integer categoryleveltwo;
    private String image;
    private Integer ratingNum;
}
