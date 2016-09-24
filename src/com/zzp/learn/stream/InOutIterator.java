package com.zzp.learn.stream;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Artist;
import com.zzp.test.TestData;

import java.util.List;

/**
 * Desc
 * Created by zzp
 * on 2016/8/8.9:24
 */
public class InOutIterator {

    public static void main(String[] args) {
        Album album = TestData.manyTrackAlbum;

        List<Artist> allArtist = album.getMusicianList();

        //及早求值
        long count = allArtist.stream().filter(artist -> artist.isFrom("US")).count();
        System.out.println(count);

        //惰性求值
        allArtist
                .stream()
                .filter(
                        artist -> {
                            System.out.println(artist.getName());
                            return artist.isFrom("US");
                        });
        //及早求值
        allArtist
                .stream()
                .filter(
                        artist -> {
                            System.out.println(artist.getName());
                            return artist.isFrom("US");
                        }).count();
    }


}
