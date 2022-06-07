package com.grape.bookrs.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecoUser {
    public Integer userId;
    public double distance;
    public List<RecoBook> bookList = new ArrayList<>();

    public RecoUser set(String bookId, double rating){
        this.bookList.add(new RecoBook(bookId, rating));
        return this;
    }

    public RecoBook find(String bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId().equals(bookId)) {
                return bookList.get(i);
            }
        }
        return null;
    }
}
