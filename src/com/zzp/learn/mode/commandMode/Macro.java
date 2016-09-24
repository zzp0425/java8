package com.zzp.learn.mode.commandMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 * Created by zzp
 * on 2016/9/20.11:06
 */
public class Macro {

    private List<Action> actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::preform);
    }

    public static void main(String[] args) {
        Editor editor = new EditorAction();
        Macro macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);

        macro.run();
    }
}
