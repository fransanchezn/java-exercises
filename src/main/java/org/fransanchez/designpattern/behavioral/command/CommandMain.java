package org.fransanchez.designpattern.behavioral.command;

import org.fransanchez.designpattern.behavioral.command.commands.KitchenLightsOffCommand;
import org.fransanchez.designpattern.behavioral.command.commands.KitchenLightsOnCommand;
import org.fransanchez.designpattern.behavioral.command.commands.MusicOffCommand;
import org.fransanchez.designpattern.behavioral.command.commands.MusicOnCommand;
import org.fransanchez.designpattern.behavioral.command.hardware.KitchenLight;
import org.fransanchez.designpattern.behavioral.command.hardware.Music;

public class CommandMain {
    public static void main(String[] args) {
        final var smartDevice = new SmartDevice();
        final var kitchenLight = new KitchenLight();
        final var music = new Music();

        final var klOnCmd = new KitchenLightsOnCommand(kitchenLight);
        final var klOffCmd = new KitchenLightsOffCommand(kitchenLight);
        final var musicOnCmd = new MusicOnCommand(music);
        final var musicOffCmd = new MusicOffCommand(music);

        smartDevice.sendCommand(klOnCmd);
        smartDevice.execute();

        smartDevice.sendCommand(musicOnCmd);
        smartDevice.execute();

        smartDevice.sendCommand(musicOffCmd);
        smartDevice.execute();

        smartDevice.sendCommand(klOffCmd);
        smartDevice.execute();
    }
}
