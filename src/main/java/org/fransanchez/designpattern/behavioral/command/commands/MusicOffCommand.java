package org.fransanchez.designpattern.behavioral.command.commands;

import org.fransanchez.designpattern.behavioral.command.hardware.Music;

public class MusicOffCommand implements Command {
    Music music;

    public MusicOffCommand(final Music music) {
        this.music = music;
    }

    @Override
    public void execute() {
        music.volume(0);
        music.station(0F);
        music.off();
    }
}
