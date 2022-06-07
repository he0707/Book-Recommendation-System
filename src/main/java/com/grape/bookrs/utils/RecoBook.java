package com.grape.bookrs.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoBook implements Comparable<RecoBook> {
    public String bookId;
    public double rating;


    @Override
    public int compareTo(RecoBook o) {
        return rating > o.rating ? -1 : 1;
    }
}
