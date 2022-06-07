package com.grape.bookrs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.grape.bookrs.mapper")
public class BookrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookrsApplication.class, args);
    }

}
