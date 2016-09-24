package com.zzp.learn.mode.commandMode;

/**
 * Desc
 * Created by zzp
 * on 2016/9/20.11:03
 */
public class Save implements Action {
    private Editor editor;
    public Save(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void preform() {
        this.editor.save();
    }
}
