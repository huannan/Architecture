package com.nan.day28_pattern_memento.simple1;

public class Client {

    public static void main(String[] args) {
        //打boss前
        GameRole gameRole = new GameRole();
        gameRole.getInitState();
        gameRole.stateDisplay();

        //保存进度
        RoleStateCaretaker caretaker = new RoleStateCaretaker();
        caretaker.setMemento(gameRole.saveState());

        //打boss失败
        gameRole.fight();
        gameRole.stateDisplay();

        //恢复状态
        gameRole.recoveryState(caretaker.getMemento());
        gameRole.stateDisplay();
    }

}