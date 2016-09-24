package com.zzp.learn.lambda.one;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Predicate;

/**
 * Desc 类型推断
 * Created by zzp
 * on 2016/8/7.12:39
 */
public class TypeInfer {
    public static void main1(String[] args) {
        Map<String, Object> oldWordCounts = new HashMap<String, Object>();

        /**
         * 使用类型推断，仍需声明变量的泛型类型
         */
        Map<String, Object> newWordCounts = new HashMap<>();

        useHashMap(new HashMap<>());

        //报错，类型推断不为String, Integer
        //Map<String, Integer> map = new HashMap<>();
        //useHashMap(map);
    }


    public static void main(String[] args) {
        //检测一个Integer是否大于5
        Predicate<Integer> atLeast5 = x -> x > 5;
        System.out.println(atLeast5.test(10));
    }
    private static void useHashMap(Map<String, String> values) {
        System.out.println("use hash map");
    }
}
