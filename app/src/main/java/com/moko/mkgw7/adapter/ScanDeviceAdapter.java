package com.moko.mkgw7.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moko.mkgw7.R;

public class ScanDeviceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ScanDeviceAdapter() {
        super(R.layout.item_scan_device_mkgw7);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_scan_device_info, item);
    }
}
