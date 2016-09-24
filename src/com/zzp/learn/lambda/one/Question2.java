package com.zzp.learn.lambda.one;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;

/**
 * Desc
 * Created by zzp
 * on 2016/8/7.13:03
 */
public class Question2 {
    //first
    //T -> Function -> R

    //second
    //T*T T + T

    //thrid
    //a

    public static ThreadLocal<DateFormatter> formatterThreadLocal =
            ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));
}
