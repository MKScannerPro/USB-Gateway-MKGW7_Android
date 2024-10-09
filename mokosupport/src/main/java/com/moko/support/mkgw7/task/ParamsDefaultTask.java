package com.moko.support.mkgw7.task;

import com.moko.ble.lib.task.OrderTask;
import com.moko.support.mkgw7.entity.OrderCHAR;
import com.moko.support.mkgw7.entity.ParamsKeyEnum;

/**
 * @author: jun.liu
 * @date: 2024/10/9 14:43
 * @des:
 */
public class ParamsDefaultTask extends OrderTask {
    public byte[] data;
    public ParamsDefaultTask() {
        super(OrderCHAR.CHAR_PARAMS_DEFAULT, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void enterUsbMode() {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_ENTER_USB_MODE.getParamsKey(),
                (byte) 0x00
        };
    }
}
