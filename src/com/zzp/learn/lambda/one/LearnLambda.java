package com.zzp.learn.lambda.one;

import java.awt.event.ActionListener;
import java.util.function.*;

/**
 * Desc
 * Created by zzp
 * on 2016/8/7.11:20
 */
public class LearnLambda {

    public static void main(String[] args) {
        /**
         * 不包含参数
         */
        Runnable noArguments = () -> System.out.println("Hello World");
        noArguments.run();

        /**
         * 一个参数
         */
        ActionListener listener = event -> System.out.println("button clicked");
        listener.actionPerformed(null);

        /**
         * 主体为代码块
         */
        Runnable multiStatement = () -> {
            System.out.println("Hello");
            System.out.println("World");
        };
        multiStatement.run();

        /**
         * 多个参数
         */
        BinaryOperator<Long> add = (x, y) -> x + y;
        System.out.println(add.apply(10l, 20l));

        /**
         * 多个参数指定参数类型
         */
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;


    }
}
