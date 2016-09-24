package com.zzp.learn.sync;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/9/18.16:13
 */
public class SimpleTest {
    /**
     * 并行求和
     * @param values
     * @return
     */
    public int addIntegers(List<Integer> values) {
        return values.parallelStream()
                .mapToInt(i -> i)
                .sum();
    }

    /**
     * 使用并行化数组操作初始化数组
     *
     * @param size
     * @return
     */
    public double[] parallelinitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }

    /**
     * 以3年滑动平均值举例：
     * 有1、2、3、4、5共5个数，计算过程为：（1+2+3）/3=2，（2+3+4）/3=3，（3+4+5）/3=4，则3年滑动平均值=（2+3+4）/3=3。
     * parallelPrefix : 当前值与上一个值运算
     */

    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    System.out.println(i);
                    double prefix = (i == start ? 0 : sums[i - n]);
                    return (sums[i] - prefix) / n;
                }).toArray();
    }

    @Test
    public void testSimpleMovingAverage() {
        double[] values = new double[]{1.0, 2.0, 3, 4, 5};
        double[] items = simpleMovingAverage(values, 3);
        for (double i : items) {
            System.out.println(i);
        }
    }

}
