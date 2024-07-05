package com.moko.mkgw7.activity;

import android.view.View;

import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.ble.lib.utils.MokoUtils;
import com.moko.mkgw7.base.BaseActivity;
import com.moko.mkgw7.databinding.ActivityDeviceInfoMkgw7Binding;
import com.moko.support.mkgw7.MokoSupport;
import com.moko.support.mkgw7.OrderTaskAssembler;
import com.moko.support.mkgw7.entity.OrderCHAR;
import com.moko.support.mkgw7.entity.ParamsKeyEnum;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeviceInformationActivity extends BaseActivity<ActivityDeviceInfoMkgw7Binding> {
    @Override
    protected void onCreate() {
        showLoadingProgressDialog();
        List<OrderTask> orderTasks = new ArrayList<>(8);
        orderTasks.add(OrderTaskAssembler.getDeviceName());
        orderTasks.add(OrderTaskAssembler.getProductModel());
        orderTasks.add(OrderTaskAssembler.getManufacturer());
        orderTasks.add(OrderTaskAssembler.getFirmwareVersion());
        orderTasks.add(OrderTaskAssembler.getSoftwareVersion());
        orderTasks.add(OrderTaskAssembler.getHardwareVersion());
        orderTasks.add(OrderTaskAssembler.getWifiMac());
        orderTasks.add(OrderTaskAssembler.getBleMac());
        MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }

    @Override
    protected ActivityDeviceInfoMkgw7Binding getViewBinding() {
        return ActivityDeviceInfoMkgw7Binding.inflate(getLayoutInflater());
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 100)
    public void onConnectStatusEvent(ConnectStatusEvent event) {
        String action = event.getAction();
        if (MokoConstants.ACTION_DISCONNECTED.equals(action)) {
            runOnUiThread(() -> {
                dismissLoadingProgressDialog();
                finish();
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderTaskResponseEvent(OrderTaskResponseEvent event) {
        final String action = event.getAction();
        if (MokoConstants.ACTION_ORDER_FINISH.equals(action)) {
            dismissLoadingProgressDialog();
        }
        if (MokoConstants.ACTION_ORDER_RESULT.equals(action)) {
            OrderTaskResponse response = event.getResponse();
            OrderCHAR orderCHAR = (OrderCHAR) response.orderCHAR;
            byte[] value = response.responseValue;
            if (orderCHAR == OrderCHAR.CHAR_PARAMS) {
                if (value.length >= 4) {
                    int header = value[0] & 0xFF;// 0xED
                    int flag = value[1] & 0xFF;// read or write
                    int cmd = value[2] & 0xFF;
                    if (header == 0xED) {
                        ParamsKeyEnum configKeyEnum = ParamsKeyEnum.fromParamKey(cmd);
                        if (configKeyEnum == null) return;
                        int length = value[3] & 0xFF;
                        if (flag == 0x00) {
                            if (length == 0) return;
                            // read
                            switch (configKeyEnum) {
                                case KEY_DEVICE_NAME:
                                    mBind.tvDeviceName.setText(new String(Arrays.copyOfRange(value, 4, 4 + length)));
                                    break;

                                case KEY_PRODUCT_MODEL:
                                    mBind.tvProductModel.setText(new String(Arrays.copyOfRange(value, 4, 4 + length)));
                                    break;

                                case KEY_MANUFACTURER:
                                    mBind.tvManufacturer.setText(new String(Arrays.copyOfRange(value, 4, 4 + length)));
                                    break;

                                case KEY_SOFTWARE_VERSION:
                                    mBind.tvDeviceSoftwareVersion.setText(new String(Arrays.copyOfRange(value, 4, 4 + length)));
                                    break;

                                case KEY_FIRMWARE_VERSION:
                                    mBind.tvWifiFirmwareVersion.setText(new String(Arrays.copyOfRange(value, 4, 4 + length)));
                                    break;

                                case KEY_HARDWARE_VERSION:
                                    mBind.tvDeviceHardwareVersion.setText(new String(Arrays.copyOfRange(value, 4, 4 + length)));
                                    break;

                                case KEY_WIFI_MAC:
                                    byte[] wifiMacBytes = Arrays.copyOfRange(value, 4, 4 + length);
                                    mBind.tvWifiMac.setText(MokoUtils.bytesToHexString(wifiMacBytes).toUpperCase());
                                    break;

                                case KEY_BLE_MAC:
                                    byte[] bleMacBytes = Arrays.copyOfRange(value, 4, 4 + length);
                                    mBind.tvBtMac.setText(MokoUtils.bytesToHexString(bleMacBytes).toUpperCase());
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void onBack(View view) {
        finish();
    }
}
