package com.zzp.test;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Artist;
import com.zzp.learn.base.bean.Track;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Desc
 * Created by zzp
 * on 2016/8/8.9:25
 */
public class TestData {
    public static final Artist johnColtrane = new Artist("John Coltrane", "US");

    public static final Artist johnLennon = new Artist("John Lennon", "UK");
    public static final Artist paulMcCartney = new Artist("Paul McCartney", "UK");
    public static final Artist georgeHarrison = new Artist("George Harrison", "UK");
    public static final Artist ringoStarr = new Artist("Ringo Starr", "UK");

    public static final List<Artist> membersOfTheBeatles = Arrays.asList(johnLennon, paulMcCartney, georgeHarrison, ringoStarr);

    public static final Artist theBeatles = new Artist("The Beatles", membersOfTheBeatles, "UK");

    public static final Album aLoveSupreme = new Album("A Love Supreme", asList(new Track("Acknowledgement", 467), new Track("Resolution", 442)), asList(johnColtrane));

    public static final Album sampleShortAlbum = new Album("sample Short Album", asList(new Track("short track", 30)), asList(johnColtrane, theBeatles, ringoStarr));

    public static final Album manyTrackAlbum = new Album("sample Short Album", asList(new Track("short track", 100), new Track("short track 2", 70), new Track("short track 3", 30), new Track("short track 4", 10), new Track("short track 5", 300)), asList(johnColtrane));


}
