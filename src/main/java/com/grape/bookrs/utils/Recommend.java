package com.grape.bookrs.utils;

import com.grape.bookrs.entity.Book;
import com.grape.bookrs.entity.Rating;
import com.grape.bookrs.entity.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Recommend {
    /**
     * 最近邻读者
     * @param userId
     * @param users
     * @return
     */
    private static Map<Double, Integer> computeNearestNeighbor(Integer userId, List<RecoUser> users) {
        Map<Double, Integer> distances = new TreeMap<>();

        RecoUser user1 = new RecoUser();
        for (RecoUser user : users) {
            if (userId.compareTo(user.getUserId()) == 0) {
                user1 = user;
            }
        }

        for (int i = 0; i < users.size(); i++) {
            RecoUser user2 = users.get(i);
            if (user2.bookList.size() == 0) {
                continue;
            }
            if (userId.compareTo(user2.getUserId()) != 0) {
                double distance = cosineSimilarity(user1.bookList, user2.bookList);
                distances.put(distance, user2.getUserId());
            }
        }
        System.out.println("当前用户与其他用户的余弦相似度：" + distances);
        return distances;
    }

    private static double cosineSimilarity(List<RecoBook> rating1, List<RecoBook> rating2) {
        double n1 = 0;
        double n2 = 0;
        double distance = 0;
        for (RecoBook recoBook2 : rating2) {
            for (RecoBook recoBook1 : rating1) {
                if (recoBook1.getBookId().equals(recoBook2.getBookId())) {
                    distance += recoBook1.getRating()*recoBook2.getRating();
                }
            }
        }
        for (int i = 0; i < rating1.size(); i++) {
            n1 += rating1.get(i).getRating() * rating1.get(i).getRating();
        }
        for (int j = 0; j < rating2.size(); j++) {
            n2 += rating2.get(j).getRating() * rating2.get(j).getRating();
        }
        distance = distance / (Math.sqrt(n1)*Math.sqrt(n2));
        return distance;
    }

    /**
     * 推荐算法 协同过滤-基于用户
     * @param userId
     * @param users
     * @param books
     * @param ratings
     * @return
     */
    public static List<Book> recommend(Integer userId, List<User> users, List<Book> books, List<Rating> ratings) {
        List<RecoUser> recoUserList = new ArrayList<>();
        for (User user : users) {
            RecoUser recoUser = new RecoUser();
            recoUser.setUserId(user.getId());
            for (Rating rating : ratings) {
                if (rating.getUserId().equals(user.getId())) {
                    recoUser.set(rating.getIsbn(), rating.getRating());
                }
            }
            recoUserList.add(recoUser);
        }
        System.out.println(recoUserList);

        //邻读者
        Map<Double, Integer> distances = computeNearestNeighbor(userId, recoUserList);
        List<RecoUser> neighborUsers = new ArrayList<>();
        for (Double key : distances.keySet()) {
            if (key.compareTo(0.1) > 0) {
                RecoUser neighborUser = new RecoUser();
                neighborUser.setUserId(distances.get(key));
                neighborUser.setDistance(key);
                for (int i = 0; i < recoUserList.size(); i++) {
                    if (recoUserList.get(i).getUserId().compareTo(distances.get(key)) == 0) {
                        neighborUser.setBookList(recoUserList.get(i).getBookList());
                    }
                }
                neighborUsers.add(neighborUser);
            }
        }
        System.out.println("邻读者："+neighborUsers);

        //邻读者评价过而读者没评价过的图书
        List<RecoBook> recommendationAllBooks = new ArrayList<>();
        for (int i = 0; i < neighborUsers.size(); i++) {
            for (int j = 0; j < neighborUsers.get(i).getBookList().size(); j++) {
                recommendationAllBooks.add(new RecoBook(neighborUsers.get(i).getBookList().get(j).getBookId(), 0));
            }
        }
        Collections.sort(recommendationAllBooks);
        RecoUser user1 = new RecoUser();
        for (int i = 0; i < recoUserList.size(); i++) {
            if (userId.compareTo(recoUserList.get(i).getUserId()) == 0) {
                user1 = recoUserList.get(i);
            }
        }
        System.out.println(recommendationAllBooks);
        for (int i = 0; i < recommendationAllBooks.size(); i++) {
            if (user1.find(recommendationAllBooks.get(i).getBookId()) != null) {
                recommendationAllBooks.remove(i);
            }
        }

        //平均加权方法计算当前读者的预测评分
        double rating1 = 0;
        double rating2 = 0;
        for (int i = 0; i < recommendationAllBooks.size(); i++) {
            for (int j = 0; j < neighborUsers.size(); j++) {
                if (neighborUsers.get(j).find(recommendationAllBooks.get(i).getBookId()) != null) {
                    rating1 += neighborUsers.get(j).distance * neighborUsers.get(j).find(recommendationAllBooks.get(i).getBookId()).getRating();
                    rating2 += neighborUsers.get(j).distance;
                }
            }
            recommendationAllBooks.get(i).setRating(rating1/rating2);
        }
        Collections.sort(recommendationAllBooks);
        System.out.println("推荐图书："+recommendationAllBooks);

        //转换成Book
        List<Book> recommendationBooks = new ArrayList<>();
        for (int j = 0; j < recommendationAllBooks.size(); j++) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getIsbn().equals(recommendationAllBooks.get(j).getBookId())) {
                    recommendationBooks.add(books.get(i));
                }
            }
        }
        if (recommendationBooks.size() > 8) {
            recommendationBooks = recommendationBooks.subList(0, 8);
        }

        return recommendationBooks;
    }
}
