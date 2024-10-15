package org.fransanchez.designpattern.behavioral.command.commands;

public class NoOpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("NoOp do nothing");
    }
}
