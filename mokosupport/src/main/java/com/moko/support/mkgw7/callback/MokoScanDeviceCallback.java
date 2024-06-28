package com.moko.support.mkgw7.callback;

import com.moko.support.mkgw7.entity.DeviceInfo;

public interface MokoScanDeviceCallback {
    void onStartScan();

    void onScanDevice(DeviceInfo device);

    void onStopScan();
}
