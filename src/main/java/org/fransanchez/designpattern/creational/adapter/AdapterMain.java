package org.fransanchez.designpattern.creational.adapter;

public class AdapterMain {
    public static void main(String[] args) {
        final var port = new UsbPort();
        final var usbCable = new UsbCable();
        final var microUsbCable = new MicroUsbCable();
        final var microUsbCableAdapter = new MicroUsbCableAdapter(microUsbCable);

        port.plugIn(usbCable);
        port.unPlug(usbCable);

        port.plugIn(microUsbCableAdapter);
        port.unPlug(microUsbCableAdapter);
    }
}
