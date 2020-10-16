package com.java.example.design.adapt;

public class Adapter implements TriplePin {

    private DualPin dualPinDevice;


    public Adapter(DualPin dualPinDevice) {
        this.dualPinDevice = dualPinDevice;
    }

    @Override
    public void electrify(String live, String zero, String earth) {
        dualPinDevice.electrify(live, zero);
    }

}
