package org.fransanchez.designpattern.behavioral.command.commands;

import org.fransanchez.designpattern.behavioral.command.hardware.KitchenLight;

public class KitchenLightsOnCommand implements Command {
    KitchenLight kitchenLight;

    public KitchenLightsOnCommand(final KitchenLight kitchenLight) {
        this.kitchenLight = kitchenLight;
    }

    @Override
    public void execute() {
        kitchenLight.on();
    }
}
