package com.zzp.learn.lambda.collections;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Artist;
import com.zzp.test.TestData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/8/13.19:05
 */
public class CollectTest {

    @Test
    public void testCollect() {
        List<String> lists = Arrays.asList("hello", "world", "xmm", "zzp", "test");

        List<String> toList = lists.stream().collect(Collectors.toList());
        Set<String> toSet = lists.stream().collect(Collectors.toSet());
        TreeSet<String> toTreeSet = lists.stream().collect(Collectors.toCollection(TreeSet::new));
        toList.forEach(System.out::print);
        System.out.println();
        toSet.forEach(System.out::print);
        System.out.println();
        toTreeSet.forEach(System.out::print);
    }


    @Test
    public void getBiggestGroup() {
        List<String> strs = Arrays.asList("abc", "abd", "bca", "avc", "bcd", "def",
                "dcf", "aaaa", "dba", "bda", "ef", "ef");
        Map<String, List<Integer>> map = strs.stream()
                .collect(
                        Collectors.groupingBy(str -> "" + str.charAt(0),
                                Collectors.mapping(str -> str.length(), Collectors.toList())));


        Optional<Artist> artists = biggestGroup(TestData.membersOfTheBeatles.stream());
        System.out.println(artists.get().getName());
    }

    /**
     * 找出成员最多的乐队
     * @param artists
     * @return
     */
    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        return artists.collect(Collectors.maxBy(Comparator.comparing(artist -> artist.getMembers().count())));
    }

    /**
     * 找出一组专辑上曲目的平均数
     * @param albums
     * @return
     */
    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream().collect(Collectors.averagingInt(album -> album.getTrackList().size()));
    }

    /**
     * 按是否是乐队还是个人分组
     * @param artists
     * @return
     */
    public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        //return artists.collect(Collectors.partitioningBy(artist -> artist.isSolo()));

        return artists.collect(Collectors.partitioningBy(Artist::isSolo));
    }

    /**
     * 按主唱分专辑
     */
    public Map<Artist, List<Album>> groupByMainMusican(Stream<Album> albums) {

        return albums.collect(Collectors.groupingBy(Album::getMainMusician));
    }


    /**
     * 格式化字符串
     * @param artists
     * @return [a, b, c]格式
     */
    public String formatNames(Stream<Artist> artists) {

        return artists.map(Artist::getName).collect(Collectors.joining(", ", "[", "]"));
    }

    /**
     * 使用收集器计算每个艺术家的专辑数
     * @param albums
     * @return
     */
    public Map<Artist, Long> numbersOfAlbums(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getMainMusician, Collectors.counting()));
    }

    /**
     * 使用收集器求每个艺术家的专辑名
     * @param albums
     * @return
     */
    public Map<Artist, List<String>> getAlbumNames(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getMainMusician, Collectors.mapping(Album::getName, Collectors.toList())));
    }
}
