package com.zzp.learn.base.bean;

/**
 * Desc 专辑中的一支曲目
 * Created by zzp
 * on 2016/8/8.9:18
 */
public class Track {
    private final String name;    //曲目名称
    private final int length;     //歌曲时长

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Track copy() {
        return new Track(name, length);
    }
}
