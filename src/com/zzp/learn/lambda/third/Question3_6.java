package com.zzp.learn.lambda.third;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/8/9.15:49
 */
public class Question3_6 {

    @Test
    public void lowCharCountTest() {
        Stream.of("Hello World", "Hello123").forEach(str -> System.out.println(lowCharCount(str)));
    }

    public static long lowCharCount(String info) {
        return info.chars().filter(Character::isLowerCase).count();
    }
}
