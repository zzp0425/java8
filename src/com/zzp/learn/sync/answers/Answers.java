package com.zzp.learn.sync.answers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Desc
 * Created by zzp
 * on 2016/9/19.10:59
 */
public class Answers {
    /******************************************************************************************/
    /**
     * 6.1 src
     * @param range
     * @return
     */
    public static int sequentialSumOfSquares(IntStream range) {
        return range.map(x -> x * x).sum();
    }

    /**
     * 6.1 dest
     * @param range
     * @return
     */
    public static int destSequentialSumOfSquares(IntStream range) {
        return range.parallel().map(x -> x * x).sum();
    }

    /******************************************************************************************/
    /**
     * 6.2 src
     * 将列表中数字相乘， 最后结果再乘以5
     * @param linkedListOfNumbers
     * @return
     */
    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream().reduce(5, (acc, x) -> x * acc);
    }

    /**
     * 6.2 dest
     * 将列表中数字相乘， 最后结果再乘以5
     * @param numbers
     * @return
     */
    public static int multiplyThroughParallel(List<Integer> numbers) {
        return numbers.parallelStream().reduce(1, (acc, x) -> x * acc).intValue() * 5;
    }

    /******************************************************************************************/

    /**
     * 6.3 src 求平方和
     * @param numbers
     * @return
     */
    public int slowSumOfSquares(List<Integer> numbers) {
        return numbers.parallelStream().map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }

    /**
     * 6.3 dest 求平方和
     * @param numbers
     * @return
     */
    public int destSlowSumOfSquares(List<Integer> numbers) {

        return numbers.parallelStream().map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }
}
