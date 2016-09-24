package com.zzp.learn.mode.commandMode;

/**
 * Desc
 * Created by zzp
 * on 2016/9/20.11:02
 */
public class Open implements Action {
    private Editor editor;
    public Open(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void preform() {
        this.editor.open();
    }
}
