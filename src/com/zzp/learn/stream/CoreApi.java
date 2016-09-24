package com.zzp.learn.stream;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Track;
import com.zzp.test.TestData;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static java.util.Arrays.asList;

/**
 * Desc
 * Created by zzp
 * on 2016/8/8.16:21
 */
public class CoreApi {

    /**
     * collect(toList())方法由Stream里的值生成一个列表，是一个及早求值操作
     *
     */
    @Test
    public void collection() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        assertEquals(asList("a", "b", "c"), collected);
    }

    /**
     * 将一个流中的值转换成一个新的流
     * T -> Function -> R
     */
    @Test
    public void map() {
        List<String> collected = Stream.of("a", "b", "c").map(string -> string.toUpperCase()).collect(Collectors.toList());
        assertEquals(asList("A", "B", "C"), collected);
    }

    /**
     * 遍历并检查其中的元素
     * T -> Predicate -> boolean
     */
    @Test
    public void filter() {
        List<String> beginningWithNumbers = Stream.of("abc", "1abc", "abc1").filter(value -> isDigit(value.charAt(0))).collect(Collectors.toList());
        assertEquals(beginningWithNumbers, asList("1abc"));
    }

    public boolean isDigit(char value) {
        return (value + "").matches("\\d");
    }

    /**
     * flatMap方法可以用stream替换值，然后将多个stream连接成一个stream
     * T -> Function -> R
     */
    @Test
    public void flatMap() {
        List<Integer> togeter = Stream.of(asList(1, 2), asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        assertEquals(asList(1, 2, 3, 4), togeter);
    }

    /**
     * stream上最常用的操作之一就是求最大值和最小值，
     * Stream API中的max和Min操作足以解决这一个问题
     * Comparator.comparing(T -> Function -> R)
     */
    @Test
    public void min() {
        List<Track> tracks = asList(
                new Track("乌克丽丽", 425),
                new Track("红尘客栈", 356),
                new Track("明明就", 534));
        Track track = tracks.stream().min(Comparator.comparing(minTrack -> minTrack.getLength())).get();
        assertEquals(tracks.get(1), track);
    }

    /**
     * reduce操作可以实现从一组值中生成一个值
     */
    @Test
    public void reduceAdd() {
        /**
         * reduce(0, (acc, elememt) -> acc + elememt)
         * 第一次 入参acc : 0 , element值 : 1 ，返回element : 1
         * 第二次 入参acc : 1,  element值 : 2 ，返回element : 3
         * 第三次 入参acc : 3 , element值 : 3 ，返回element : 6
         */
        int count = Stream.of(1, 2, 3).reduce(0, (acc, elememt) -> acc + elememt);

        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;

        count = accumulator.apply(
                    accumulator.apply(
                            accumulator.apply(0, 1), 2),
                3);

        assertEquals(6, count);
    }

    /**
     * 假设乐队名称以"The"开头
     */
    @Test
    public void getNation() {
        Album album = TestData.sampleShortAlbum;
        Set<String> nations = album.getMusicians().
                filter(artist -> artist.getName().startsWith("The")).
                map(artist -> artist.getNationality()).collect(Collectors.toSet());
        assertEquals(nations.iterator().next(), "UK");
    }
}
