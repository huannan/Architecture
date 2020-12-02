package com.nan.day22_pattern_command.simple2;

public class RightCommand implements Command {
    private TetrisMachine machine;

    public RightCommand(TetrisMachine machine) {
        this.machine = machine;
    }


    @Override
    public void execute() {
        machine.toRight();
    }
}
