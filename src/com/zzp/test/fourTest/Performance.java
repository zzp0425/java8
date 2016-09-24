package com.zzp.test.fourTest;

import com.zzp.learn.base.bean.Artist;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc 艺术家的演出--专辑或演唱会
 * Created by zzp
 * on 2016/8/12.21:43
 */
public interface Performance {

    String getName();

    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> Stream.concat(Stream.of(artist), artist.getMembers()));
    }
}
