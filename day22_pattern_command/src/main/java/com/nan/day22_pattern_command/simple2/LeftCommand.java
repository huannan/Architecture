package com.nan.day22_pattern_command.simple2;

public class LeftCommand implements Command {
    private TetrisMachine machine;

    public LeftCommand(TetrisMachine machine) {
        this.machine = machine;
    }


    @Override
    public void execute() {
        machine.toLeft();
    }
}
