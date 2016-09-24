package com.zzp.learn.mode.commandMode;

/**
 * Desc
 * Created by zzp
 * on 2016/9/20.11:56
 */
public class EditorAction implements Editor {
    @Override
    public void open() {
        System.out.println("open");
    }

    @Override
    public void save() {
        System.out.println("save");
    }

    @Override
    public void close() {
        System.out.println("close");
    }
}
