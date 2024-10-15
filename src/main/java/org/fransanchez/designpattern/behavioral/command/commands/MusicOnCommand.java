package org.fransanchez.designpattern.behavioral.command.commands;

import org.fransanchez.designpattern.behavioral.command.hardware.Music;

public class MusicOnCommand implements Command {
    Music music;

    public MusicOnCommand(final Music music) {
        this.music = music;
    }

    @Override
    public void execute() {
        music.on();
        music.volume(30);
        music.station(99.5F);
    }
}
