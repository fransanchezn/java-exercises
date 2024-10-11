package org.fransanchez.designpattern.creational.adapter;

public class MicroUsbCableAdapter extends UsbCable {
    private final MicroUsbCable microUsbCable;

    public MicroUsbCableAdapter(final MicroUsbCable microUsbCable) {
        this.microUsbCable = microUsbCable;
    }

    @Override
    public void unPlugUsb() {
        microUsbCable.plugUsb();
    }

    @Override
    public void plugUsb() {
        microUsbCable.unPlugUsb();
    }
}
