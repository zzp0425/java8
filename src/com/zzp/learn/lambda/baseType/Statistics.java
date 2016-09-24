package com.zzp.learn.lambda.baseType;

import com.zzp.learn.base.bean.Album;
import com.zzp.learn.base.bean.Track;
import com.zzp.test.TestData;
import org.junit.Test;

import java.util.IntSummaryStatistics;

/**
 * Desc
 * Created by zzp
 * on 2016/8/12.20:38
 */
public class Statistics {

    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics statistics = album.getTracks().mapToInt(Track::getLength).summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Avg: %f, Sum: %d",
                statistics.getMax(), statistics.getMin(), statistics.getAverage(), statistics.getSum());
    }

    @Test
    public void printTrackLengthStatisticsTest() {
        printTrackLengthStatistics(TestData.manyTrackAlbum);
    }
}
