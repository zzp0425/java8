package com.zzp.learn.stream;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Track;
import com.zzp.test.TestData;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Arrays.asList;

/**
 * Desc 重构jdk1.8版本之前的代码
 * Created by zzp
 * on 2016/8/8.23:25
 */
public class Resconsitution {
    Set<String> trackNames = new HashSet<String>();
    List<Album> albums = asList(TestData.aLoveSupreme, TestData.manyTrackAlbum, TestData.sampleShortAlbum);
    /**
     * 原Jdk1.6代码
     * 找出长度大于1分钟的曲目
     */
    @Test
    public void origin() {

        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    trackNames.add(track.getName());
                }
            }
        }

        trackNames.forEach(s -> System.out.println(s));
    }

    /**
     * 第一步重构
     * 将for循环改为Stream的forEach
     */
    @Test
    public void foreachResconsitution() {
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .forEach(track -> {
                                if (track.getLength() > 60) {
                                    trackNames.add(track.getName());
                                }
                            });
                });
        trackNames.forEach(s -> System.out.println(s));
    }

    /**
     * 第二步重构
     * 1、将if判断改为过滤filter
     * 2、用map转换track到track.name的新的流
     * 3、循环set专辑的曲目
     */
    @Test
    public void filterResconsitution() {
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                        .filter(track -> track.getLength() > 60)
                        .map(track -> track.getName())
                        .forEach(name -> trackNames.add(name));
                });
        trackNames.forEach(s -> System.out.println(s));
    }

    /**
     * 第三步重构
     * 用flatMap合并所有专辑的曲目
     */
    @Test
    public void flatMapResconsitution() {
        albums.stream()
                .flatMap(album -> album.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .forEach(name -> trackNames.add(name));
        trackNames.forEach(s -> System.out.println(s));
    }

    /**
     * 用collect代替forEach中的add操作
     */
    @Test
    public void collecResconsitution() {
        trackNames = albums.stream().flatMap(album -> album.getTracks()).filter(track -> track.getLength() > 60).map(track -> track.getName()).collect(Collectors.toSet());
        trackNames.forEach(s -> System.out.println(s));
    }
}
