package com.zzp.learn.lambda.collections.customization;

import com.zzp.learn.base.bean.Artist;
import com.zzp.test.TestData;
import org.junit.Test;

import java.util.List;

/**
 * Desc
 * Created by zzp
 * on 2016/8/16.19:07
 */
public class ToString {
    private List<Artist> artists = TestData.membersOfTheBeatles;
    private StringBuilder sb = new StringBuilder("[");
    /**
     * 原始的java格式化字符串
     */
    @Test
    public void origin() {
        for (Artist artist : artists) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(artist.getName());
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * 先通过map将对象列表转为name列表
     */
    @Test
    public void originTransForEach() {
        artists.stream()
                .map(Artist::getName)
                .forEach(name -> {
                    if (sb.length() > 1) {
                        sb.append(", ");
                    }
                    sb.append(name);
                });
        sb.append("]");
        System.out.println(sb.toString());
    }

    @Test
    @Deprecated
    public void originTransReduce() {
        StringBuilder reduce = artists.stream()
                .map(Artist::getName)
                .reduce(new StringBuilder(),
                        (builder, name) -> {
                            if (builder.length() > 1) {
                                builder.append(", ");
                            }
                            builder.append(name);
                            return builder;
                        }, (left, right) -> right);
        reduce.insert(0, "[");
        reduce.append("]");
        System.out.println(reduce.toString());
    }

    @Test
    public void originTransReduce2() {
        StringCombiner combiner = artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(", ", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge);
        System.out.println(combiner.toString());

    }

    @Test
    public void collecotr() {
        String str = artists.stream()
                .map(Artist::getName)
                .collect(new StringCollectors(", ", "[", "]"));
        System.out.println(str);
    }
}
