package org.fransanchez.designpattern.behavioral.command.hardware;

public class Music {
    private int volume;
    private float station;

    public int volume() {
        return volume;
    }

    public void volume(final int volume) {
        System.out.println("Setting volume to: " + volume);
        this.volume = volume;
    }

    public float station() {
        return station;
    }

    public void station(final float station) {
        System.out.println("Setting station to: " + station);
        this.station = station;
    }

    public void on() {
        System.out.println("Turning on music");
    }

    public void off() {
        System.out.println("Turning off music");
    }
}
