package com.zzp.learn.lambda.third;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Desc
 * Created by zzp
 * on 2016/8/9.15:58
 */
public class Question3_7 {

    public Optional<String> getMinLowCharCounts(List<String> strs) {
        return strs.stream().max(Comparator.comparing(Question3_6::lowCharCount));
    }

    @Test
    public void getMinLowCharCountsTest() {
        String info = getMinLowCharCounts(Arrays.asList("Hello World", "abcdefghijklmn", "hello123", "he", "zhouzhenping")).get();
        System.out.println(info);
    }
}
