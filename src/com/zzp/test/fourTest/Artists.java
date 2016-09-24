package com.zzp.test.fourTest;

import com.zzp.learn.base.bean.Artist;

import java.util.List;

/**
 * Desc
 * Created by zzp
 * on 2016/8/13.12:15
 */
public class Artists {

    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Artist getArtist(int index) {
        if (index < 0 || index >= this.artists.size()) {
            indexException(index);
        }
        return this.artists.get(index);
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + "doesn't correpond to an Artist");
    }

    public String getArtistName(int index) {
        try {
            Artist artist = getArtist(index);
            return artist.getName();
        } catch (IllegalArgumentException e) {
            return "unknow";
        }
    }
}
