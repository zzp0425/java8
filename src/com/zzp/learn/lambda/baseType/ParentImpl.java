package com.zzp.learn.lambda.baseType;

import org.junit.Test;

/**
 * Desc
 * Created by zzp
 * on 2016/8/12.21:10
 */
public class ParentImpl implements Parent {
    @Override
    public void message(String body) {
        System.out.println(body);
    }

    @Test
    public void welcomeTest() {
        Parent parent = new ParentImpl();
        parent.welcome();
    }
}
