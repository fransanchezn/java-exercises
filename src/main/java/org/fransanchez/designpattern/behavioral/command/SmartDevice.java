package org.fransanchez.designpattern.behavioral.command;

import org.fransanchez.designpattern.behavioral.command.commands.Command;

public class SmartDevice {
    private Command command;

    public void sendCommand(final Command command) {
        this.command = command;
    }

    public void execute() {
        this.command.execute();
    }
}
