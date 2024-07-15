package com.moko.support.mkgw7.task;

import com.moko.ble.lib.task.OrderTask;
import com.moko.support.mkgw7.entity.OrderCHAR;

public class SetPasswordTask extends OrderTask {
    public byte[] data;

    public SetPasswordTask() {
        super(OrderCHAR.CHAR_PASSWORD, OrderTask.RESPONSE_TYPE_WRITE);
    }

    public void setData(String password) {
        byte[] passwordBytes = password.getBytes();
        int length = passwordBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) 0x01;
        data[3] = (byte) length;
        System.arraycopy(passwordBytes, 0, data, 4, length);
    }

    @Override
    public byte[] assemble() {
        return data;
    }
}
