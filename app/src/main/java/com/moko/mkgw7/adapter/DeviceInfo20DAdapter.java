package com.moko.mkgw7.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moko.mkgw7.R;
import com.moko.support.mkgw7.entity.DeviceInfo;

public class DeviceInfo20DAdapter extends BaseQuickAdapter<DeviceInfo, BaseViewHolder> {
    public DeviceInfo20DAdapter() {
        super(R.layout.item_devices);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceInfo item) {
        helper.setText(R.id.tv_device_name, item.name);
        helper.setText(R.id.tv_device_rssi, String.valueOf(item.rssi));
    }
}
