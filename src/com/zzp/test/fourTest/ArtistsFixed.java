package com.zzp.test.fourTest;

import com.zzp.learn.base.bean.Artist;

import java.util.List;
import java.util.Optional;

/**
 * Desc
 * Created by zzp
 * on 2016/8/13.12:22
 */
public class ArtistsFixed {

    private List<Artist> artists;

    public ArtistsFixed(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= this.artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + "doesn't correpond to an Artist");
    }

    public String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);

        return artist.map(Artist::getName).orElse("unknow");
    }
}
