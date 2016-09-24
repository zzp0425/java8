package com.zzp.learn.lambda.third;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/8/9.23:20
 */
public class Question3_highOrder_2 {

    public <T> List<T> filter(Stream<T> stream, Predicate<T> predicate) {
        return stream.reduce(
                new ArrayList<T>(),
                (acc, x) -> {
                    if (predicate.test(x)) {
                        List<T> newList = new ArrayList<>(acc);
                        newList.add(x);
                        return newList;
                    } else {
                        return acc;
                    }
                },
                (List<T> left, List<T> right) -> {
                    List<T> newList = new ArrayList<T>(left);
                    newList.addAll(right);
                    return newList;
                });
    }
}
