package org.fransanchez.designpattern.creational.adapter;

public class UsbPort {
    private boolean isAvailable = true;

    public boolean plugIn(final UsbCable usbCable) {
        if (isAvailable) {
            isAvailable = false;
            usbCable.plugUsb();
            return true;
        }

        return false;
    }

    public boolean unPlug(final UsbCable usbCable) {
        if (!isAvailable) {
            isAvailable = true;
            usbCable.unPlugUsb();
            return true;
        }

        return false;
    }
}
