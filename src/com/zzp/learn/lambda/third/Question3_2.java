package com.zzp.learn.lambda.third;

import com.zzp.learn.base.bean.Artist;
import com.zzp.test.TestData;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/8/9.15:35
 */
public class Question3_2 {
    private List<Artist> artists = TestData.membersOfTheBeatles;

    /**
     * 外不循环
     */
    public void origin() {
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
    }

    /**
     * 修改后的内部循环
     */
    public void innerIteator() {
        int totalMembers = artists.stream().map(artist -> artist.getMembers().count()).reduce(0L, Long::sum).intValue();
    }

}
