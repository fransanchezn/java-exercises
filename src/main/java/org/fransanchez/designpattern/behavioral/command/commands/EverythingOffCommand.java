package org.fransanchez.designpattern.behavioral.command.commands;

import org.fransanchez.designpattern.behavioral.command.hardware.KitchenLight;
import org.fransanchez.designpattern.behavioral.command.hardware.Music;

public class EverythingOffCommand implements Command {
    KitchenLight kitchenLight;
    Music music;

    public EverythingOffCommand(final KitchenLight kitchenLight, final Music music) {
        this.kitchenLight = kitchenLight;
        this.music = music;
    }

    @Override
    public void execute() {
        music.volume(0);
        music.station(0F);
        music.off();
        kitchenLight.off();
    }
}
