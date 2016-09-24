package com.zzp.learn.mode;

import java.util.Calendar;
import java.util.stream.IntStream;

/**
 * Desc
 * Created by zzp
 * on 2016/9/21.15:06
 */
public class IsPrime {

    public long countPerime(int upTo) {
        return IntStream.range(1, upTo)
                .parallel()
                .filter(this::isPerime)
                .count();
    }

    public boolean isPerime(int numbers) {
        return IntStream.range(2, numbers)
//                .parallel()
                .allMatch(x -> (numbers % x) != 0);
    }

    public static void main(String[] args) {
        IsPrime isPrime = new IsPrime();
        long start = Calendar.getInstance().getTimeInMillis();
        long a = isPrime.countPerime(30000);
        System.out.println(Calendar.getInstance().getTimeInMillis() - start);
        System.out.println(a);
    }
}
