package com.nan.day22_pattern_command.simple1;

/**
 * 命令的接收（执行者）
 */
public class TetrisMachine {
    public void toLeft() {
        System.out.println("toLeft");
    }

    public void toRight() {
        System.out.println("toRight");
    }

    public void fastToBottom() {
        System.out.println("fastToBottom");
    }

    public void transform() {
        System.out.println("transform");
    }
}
