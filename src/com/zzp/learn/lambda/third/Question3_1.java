package com.zzp.learn.lambda.third;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Artist;
import com.zzp.test.TestData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/** 3.9节练习题
 * Desc
 * Created by zzp
 * on 2016/8/9.14:54
 */
public class Question3_1 {

    /**
     * 1、a 求和函数，计算流中所有数之和
     */
    @Test
    public void addUpTest() {
        int sum = addUp(Stream.of(3, 5, 9, 7));
        System.out.println(sum);
    }

    /**
     * 计算流中所有数之和
     * @param numbers  流
     * @return 流中所有数之和
     */
    public int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, element) -> acc + element).intValue();
    }


    /**
     * 1 b
     */
    @Test
    public void getNameAndNationTest() {
        List<String> artists = getNameAndNation(TestData.membersOfTheBeatles);
        artists.forEach(System.out::println);
    }

    /**
     *
     * @param artists 艺术家列表
     * @return 包含艺术家姓名跟国籍的列表
     */
    public List<String> getNameAndNation(List<Artist> artists) {
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality())).collect(toList());
    }

    @Test
    public void getAlbumTest() {
        getAlbum(Arrays.asList(TestData.aLoveSupreme, TestData.manyTrackAlbum, TestData.sampleShortAlbum)).stream().forEach(album -> System.out.println(album.getName()));
    }

    /**
     *
     * @param albums 专辑列表
     * @return 专辑中曲目数少于3首的专辑列表
     */
    public List<Album> getAlbum(List<Album> albums) {
        return albums.stream().filter(album -> album.getTrackList().size() <= 3).collect(toList());
    }

}
