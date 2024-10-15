package org.fransanchez.designpattern.behavioral.command.commands;

import org.fransanchez.designpattern.behavioral.command.hardware.KitchenLight;
import org.fransanchez.designpattern.behavioral.command.hardware.Music;

public class EverythingOnCommand implements Command {
    KitchenLight kitchenLight;
    Music music;

    public EverythingOnCommand(final KitchenLight kitchenLight, final Music music) {
        this.kitchenLight = kitchenLight;
        this.music = music;
    }

    @Override
    public void execute() {
        kitchenLight.on();
        music.on();
        music.volume(50);
        music.station(54.3F);
    }
}
