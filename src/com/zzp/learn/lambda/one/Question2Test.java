package com.zzp.learn.lambda.one;

import java.util.Calendar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Desc
 * Created by zzp
 * on 2016/8/7.13:38
 */
public class Question2Test {

    @Test
    public void explameDateFomatter() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JANUARY, 10);
        String format = Question2.formatterThreadLocal.get().getFormat().format(calendar.getTime());
        System.out.println(format);
        assertEquals("10-一月-2016", format);
    }
}
