package com.zzp.learn.lambda.third;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/8/9.16:13
 */
public class Question3_highOrder_1 {
    /**
     * 只用reduce和lambda表达式写出实现Stream上的map操作的代码
     * @return
     */
    public <T, R> List<R> map(Stream<T> stream, Function<T, R> function) {

        /**
         * reduce三个参数 ：identity, accumulator, combiner(聚合)
         * identity : the identity value for the combine function，即该参数是给第三个参数合并用的
         * accumulator : function for incorporating(合并) an additional(额外的) element into a result，即合并额外的元素到结果集
         * combiner : function for combining two values, which must be compatible with the accumulator function ，即合并两个参数，但是必须是兼容accumulator的函数的
         */


        return stream.reduce(new ArrayList<R>(),
                (acc, x) -> {
                    List<R> newList = new ArrayList<>(acc);
                    newList.add(function.apply(x));
                    return newList;
                }, 
                (List<R> left, List<R> right) -> {
                    List<R> list = new ArrayList<>(left);
                    list.addAll(right);
                    return list;
                });
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("123", "345", "abc");
        strs.forEach(System.out::println);
    }
}
