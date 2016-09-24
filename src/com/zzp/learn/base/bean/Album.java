package com.zzp.learn.base.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc 专辑，由若干曲目组成
 * Created by zzp
 * on 2016/8/8.9:19
 */
public class Album {
    private String name;                //专辑名
    private List<Track> tracks;         //专辑上所有曲目的列表
    private List<Artist> musicians;     //参与创作本专辑的艺术家列表

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(tracks);
        Objects.requireNonNull(musicians);

        this.name = name;
        this.tracks = new ArrayList<>(tracks);
        this.musicians = new ArrayList<>(musicians);
    }


    public String getName() {
        return name;
    }

    public Stream<Track> getTracks() {
        return tracks.stream();
    }

    public List<Track> getTrackList() {
        return Collections.unmodifiableList(tracks);
    }


    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    public List<Artist> getMusicianList() {
        return Collections.unmodifiableList(musicians);
    }

    public Artist getMainMusician() {
        return musicians.get(0);
    }

    public Album copy() {
        List<Track> tracks = getTracks().map(Track::copy).collect(Collectors.toList());
        List<Artist> musicians = getMusicians().map(Artist::copy).collect(Collectors.toList());
        return new Album(name, tracks, musicians);
    }
}
