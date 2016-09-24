package com.zzp.learn.sync;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Desc
 * Created by zzp
 * on 2016/9/18.11:08
 */
public class DiceRolls {
    public static final int N = 100_000_000;


    public static void main(String[] args) {
        Map<Integer, Double> maps = parallelDiceRolls();
        maps.entrySet().forEach(System.out::println);
        maps.forEach((key, info) -> System.out.println("点数" + key + ", 概率：" + info));
    }
    public static Map<Integer, Double> parallelDiceRolls() {
        double franction = 1.0 /N;

        return IntStream.range(0, N)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(n -> franction)));
    }

    public static IntFunction<Integer> twoDiceThrows() {
        return i -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return random.nextInt(1, 7) + random.nextInt(1, 7);
        };
    }
}
