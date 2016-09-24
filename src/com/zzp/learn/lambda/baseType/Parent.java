package com.zzp.learn.lambda.baseType;

/**
 * Desc
 * Created by zzp
 * on 2016/8/12.21:09
 */
public interface Parent {

    void message(String body);

    default void welcome() {
        message("parent : hi");
    }

}
