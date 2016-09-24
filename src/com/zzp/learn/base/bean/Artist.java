package com.zzp.learn.base.bean;

import com.zzp.learn.base.util.ValidateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc 创作音乐的个人或团队
 * Created by zzp
 * on 2016/8/8.9:16
 */
public class Artist {
    private String name;            //艺术家的名字
    private List<Artist> members;   //乐队成员
    private String nationality;          //乐队来自哪里

    public Artist(String name, String nationality) {
        this(name, Collections.emptyList(), nationality);
    }
    public Artist(String name, List<Artist> artists, String nationality) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(artists);
        Objects.requireNonNull(nationality);
        this.name = name;
        this.members = new ArrayList<>(artists);
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }


    public Stream<Artist> getMembers() {
        return members.stream();
    }


    public String getNationality() {
        return nationality;
    }

    /**
     * 判断是否来自nationality
     * @param nationality
     * @return
     */
    public boolean isFrom(String nationality) {
        return this.nationality.equals(nationality);
    }

    /**
     * 判断是否是独奏
     * @return
     */
    public boolean isSolo() {
        return members.isEmpty();
    }

    @Override
    public String toString() {
        return getName();
    }

    public Artist copy() {
        List<Artist> members = getMembers().map(Artist::copy).collect(Collectors.toList());
        return new Artist(name, members, nationality);
    }
}
