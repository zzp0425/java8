package com.zzp.learn.reconsitution;

import com.zzp.learn.base.bean.Album;

import java.util.List;
import java.util.function.ToLongFunction;

/**
 * Desc
 * Created by zzp
 * on 2016/9/19.20:48
 */
public class Order {
    private List<Album> albums;

    public Order(List<Album> albums) {
        this.albums = albums;
    }

    public long countFeature(ToLongFunction<Album> function) {
        return albums.stream().mapToLong(function).sum();
    }

    public long countTracks() {
        return countFeature(album -> album.getTracks().count());
    }

    public long countRuntime() {
        return countFeature(album -> album.getTracks().mapToLong(track -> track.getLength()).sum());
    }
}
