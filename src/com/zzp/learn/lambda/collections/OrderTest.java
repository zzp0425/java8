package com.zzp.learn.lambda.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Desc
 * Created by zzp
 * on 2016/8/13.12:27
 */
public class OrderTest {

    /**
     * 顺序相同
     */
    @Test
    public void listOrder() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream().collect(toList());
        assertEquals(numbers, sameOrder);
    }

    /**
     * 顺序有时会不同
     */
    @Test
    public void setOrder() {
        Set<Integer> numbers = new HashSet<>(asList(1, 2, 3, 4, 8, 5));
        List<Integer> sameOrder = numbers.stream().collect(toList());
        assertEquals(asList(1, 2, 3, 4, 8, 5), sameOrder);
    }


    /**
     * 排序
     */
    @Test
    public void sorderNumbers() {
        List<Integer> numbers = asList(1, 2, 3, 4, 8, 5);
        List<Integer> sameOrder = numbers.stream().sorted().collect(toList());
        assertEquals(asList(1, 2, 3, 4, 5, 8), sameOrder);
    }

    public void hasItems() {
        List<Integer> numbers = asList(1, 2, 4, 3);
        List<Integer> stillOrderd = numbers.stream().map(x -> x + 1).collect(toList());
        assertEquals(asList(2, 3, 5, 4), stillOrderd);

        Set<Integer> unOrder = new HashSet<>(numbers);
        List<Integer> setStillOrderd = unOrder.stream().map(x -> x + 1).collect(toList());
    }
}
