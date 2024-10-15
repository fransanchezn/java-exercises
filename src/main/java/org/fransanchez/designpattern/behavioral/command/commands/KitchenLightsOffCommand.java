package org.fransanchez.designpattern.behavioral.command.commands;

import org.fransanchez.designpattern.behavioral.command.hardware.KitchenLight;

public class KitchenLightsOffCommand implements Command {
    KitchenLight kitchenLight;

    public KitchenLightsOffCommand(final KitchenLight kitchenLight) {
        this.kitchenLight = kitchenLight;
    }

    @Override
    public void execute() {
        kitchenLight.off();
    }
}
