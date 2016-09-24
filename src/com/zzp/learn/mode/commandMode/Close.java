package com.zzp.learn.mode.commandMode;

/**
 * Desc
 * Created by zzp
 * on 2016/9/20.11:04
 */
public class Close implements Action {

    private Editor editor;

    public Close(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void preform() {
        this.editor.close();
    }
}
