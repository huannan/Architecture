package com.nan.day22_pattern_command.simple1;

/**
 * 命令的发送者
 */
public class Buttons {
    LeftCommand leftCommand;

    RightCommand rightCommand;


    public void setLeftCommand(LeftCommand leftCommand) {
        this.leftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        this.rightCommand = rightCommand;
    }

    public void toLeft() {
        leftCommand.execute();
    }

    public void toRight() {
        rightCommand.execute();
    }
}
