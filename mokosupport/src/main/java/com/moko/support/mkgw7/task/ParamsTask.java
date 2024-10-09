package com.moko.support.mkgw7.task;

import androidx.annotation.IntRange;

import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.utils.MokoUtils;
import com.moko.support.mkgw7.MokoSupport;
import com.moko.support.mkgw7.entity.OrderCHAR;
import com.moko.support.mkgw7.entity.ParamsKeyEnum;
import com.moko.support.mkgw7.entity.ParamsLongKeyEnum;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ParamsTask extends OrderTask {
    public byte[] data;

    public ParamsTask() {
        super(OrderCHAR.CHAR_PARAMS, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(ParamsKeyEnum key) {
        createGetConfigData(key.getParamsKey());
    }

    public void setData(ParamsLongKeyEnum key) {
        createGetLongConfigData(key.getParamsKey());
    }

    private void createGetLongConfigData(int paramsKey) {
        response.responseValue = data = new byte[]{
                (byte) 0xEE,
                (byte) 0x00,
                (byte) paramsKey,
                (byte) 0x00
        };
    }

    private void createGetConfigData(int configKey) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x00,
                (byte) configKey,
                (byte) 0x00
        };
    }

    public void reboot() {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_REBOOT.getParamsKey(),
                (byte) 0x00
        };
    }

    public void exitConfigMode() {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_EXIT_CONFIG_MODE.getParamsKey(),
                (byte) 0x00,
        };
    }

    public void changePassword(String password) {
        byte[] dataBytes = password.getBytes();
        int length = dataBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_PASSWORD.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setDeviceName(String deviceName) {
        byte[] dataBytes = deviceName.getBytes();
        int length = dataBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_DEVICE_NAME.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setProductModel(String productModel) {
        byte[] dataBytes = productModel.getBytes();
        int length = dataBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_PRODUCT_MODEL.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setHardwareVersion(String hardwareVersion) {
        byte[] dataBytes = hardwareVersion.getBytes();
        int length = dataBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_HARDWARE_VERSION.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setManufacturer(String manufacturer) {
        byte[] dataBytes = manufacturer.getBytes();
        int length = dataBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MANUFACTURER.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setProductTestMode() {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PRODUCT_TEST_MODE.getParamsKey(),
                (byte) 0x00
        };
    }

    public void setProductTestDeviceState(@IntRange(from = 0, to = 2) int state) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PRODUCT_TEST_DEVICE_STATE.getParamsKey(),
                (byte) 0x01,
                (byte) state,
        };
    }

    public void resetParams() {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_RESET_PARAMS.getParamsKey(),
                (byte) 0x00
        };
    }

    public void setIndicatorSwitch(@IntRange(from = 0, to = 15) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_INDICATOR_SWITCH.getParamsKey(),
                (byte) 0x01,
                (byte) enable,
        };
    }

    public void setNtpEnable(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_NTP_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable,
        };
    }


    public void setNtpUrl(String url) {
        byte[] dataBytes = url.getBytes();
        int length = dataBytes.length;
        this.data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_NTP_URL.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setTimezone(@IntRange(from = -24, to = 28) int timezone) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_NTP_TIME_ZONE.getParamsKey(),
                (byte) 0x01,
                (byte) timezone,
        };
    }

    public void setMqttHost(String host) {
        byte[] dataBytes = host.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MQTT_HOST.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setMqttPort(@IntRange(from = 1, to = 65535) int port) {
        byte[] dataBytes = MokoUtils.toByteArray(port, 2);
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_PORT.getParamsKey(),
                (byte) 0x02,
                dataBytes[0],
                dataBytes[1]
        };
    }

    public void setMqttClientId(String clientId) {
        byte[] dataBytes = clientId.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MQTT_CLIENT_ID.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setMqttCleanSession(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_CLEAN_SESSION.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setMqttKeepAlive(@IntRange(from = 10, to = 120) int keepAlive) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_KEEP_ALIVE.getParamsKey(),
                (byte) 0x01,
                (byte) keepAlive
        };
    }

    public void setMqttQos(@IntRange(from = 0, to = 2) int qos) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_QOS.getParamsKey(),
                (byte) 0x01,
                (byte) qos
        };
    }

    public void setMqttSubscribeTopic(String topic) {
        byte[] dataBytes = topic.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MQTT_SUBSCRIBE_TOPIC.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setMqttPublishTopic(String topic) {
        byte[] dataBytes = topic.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MQTT_PUBLISH_TOPIC.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setMqttLwtEnable(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_LWT_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setMqttLwtQos(@IntRange(from = 0, to = 2) int qos) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_LWT_QOS.getParamsKey(),
                (byte) 0x01,
                (byte) qos
        };
    }

    public void setMqttLwtRetain(@IntRange(from = 0, to = 1) int retain) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_LWT_RETAIN.getParamsKey(),
                (byte) 0x01,
                (byte) retain
        };
    }

    public void setMqttLwtTopic(String topic) {
        byte[] dataBytes = topic.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MQTT_LWT_TOPIC.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setMqttLwtPayload(String payload) {
        byte[] dataBytes = payload.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MQTT_LWT_PAYLOAD.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setMqttConnectMode(@IntRange(from = 0, to = 3) int mode) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MQTT_CONNECT_MODE.getParamsKey(),
                (byte) 0x01,
                (byte) mode
        };
    }

    public void setWifiPassword(String password) {
        byte[] dataBytes = password.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_WIFI_PASSWORD.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setFilterRSSI(@IntRange(from = -127, to = 0) int rssi) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_RSSI.getParamsKey(),
                (byte) 0x01,
                (byte) rssi
        };
    }

    public void setFilterRelationship(@IntRange(from = 0, to = 7) int relationship) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_RELATIONSHIP.getParamsKey(),
                (byte) 0x01,
                (byte) relationship
        };
    }

    public void setFilterMacPrecise(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_MAC_PRECISE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setFilterMacReverse(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_MAC_REVERSE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setFilterMacRules(ArrayList<String> filterMacRules) {
        if (filterMacRules == null || filterMacRules.isEmpty()) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_FILTER_MAC_RULES.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = 0;
            for (String mac : filterMacRules) {
                length += 1;
                length += mac.length() / 2;
            }
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_MAC_RULES.getParamsKey();
            data[3] = (byte) length;
            int index = 0;
            for (int i = 0, size = filterMacRules.size(); i < size; i++) {
                String mac = filterMacRules.get(i);
                byte[] macBytes = MokoUtils.hex2bytes(mac);
                int l = macBytes.length;
                data[4 + index] = (byte) l;
                index++;
                for (int j = 0; j < l; j++, index++) {
                    data[4 + index] = macBytes[j];
                }
            }
        }
        response.responseValue = data;
    }

    public void setFilterNamePrecise(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_NAME_PRECISE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setFilterNameReverse(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_NAME_REVERSE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setFile(ParamsLongKeyEnum key, File file) throws Exception {
        FileInputStream inputSteam = new FileInputStream(file);
        dataBytes = new byte[(int) file.length()];
        inputSteam.read(dataBytes);
        dataLength = dataBytes.length;
        if (dataLength % DATA_LENGTH_MAX > 0) {
            packetCount = dataLength / DATA_LENGTH_MAX + 1;
        } else {
            packetCount = dataLength / DATA_LENGTH_MAX;
        }
        remainPack = packetCount - 1;
        packetIndex = 0;
        delayTime = DEFAULT_DELAY_TIME + 500L * packetCount;
        if (packetCount > 1) {
            data = new byte[DATA_LENGTH_MAX + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) key.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) DATA_LENGTH_MAX;
            for (int i = 0; i < DATA_LENGTH_MAX; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        } else {
            data = new byte[dataLength + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) key.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) dataLength;
            System.arraycopy(dataBytes, 0, data, 6, dataLength);
        }
        inputSteam.close();
    }


    public void setFilterNameRules(ArrayList<String> filterNameRules) {
        int length = 0;
        for (String name : filterNameRules) {
            length += 1;
            length += name.length();
        }
        dataBytes = new byte[length];
        int index = 0;
        for (int i = 0, size = filterNameRules.size(); i < size; i++) {
            String name = filterNameRules.get(i);
            byte[] nameBytes = name.getBytes();
            int l = nameBytes.length;
            dataBytes[index] = (byte) l;
            index++;
            for (int j = 0; j < l; j++, index++) {
                dataBytes[index] = nameBytes[j];
            }
        }
        dataLength = dataBytes.length;
        if (dataLength != 0) {
            if (dataLength % DATA_LENGTH_MAX > 0) {
                packetCount = dataLength / DATA_LENGTH_MAX + 1;
            } else {
                packetCount = dataLength / DATA_LENGTH_MAX;
            }
        } else {
            packetCount = 1;
        }
        remainPack = packetCount - 1;
        packetIndex = 0;
        delayTime = DEFAULT_DELAY_TIME + 500L * packetCount;
        if (packetCount > 1) {
            data = new byte[DATA_LENGTH_MAX + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsLongKeyEnum.KEY_FILTER_NAME_RULES.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) DATA_LENGTH_MAX;
            for (int i = 0; i < DATA_LENGTH_MAX; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        } else {
            data = new byte[dataLength + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsLongKeyEnum.KEY_FILTER_NAME_RULES.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) dataLength;
            System.arraycopy(dataBytes, 0, data, 6, dataLength);
        }
    }

    public void setLongChar(ParamsLongKeyEnum key, String character) {
        dataBytes = character.getBytes();
        dataLength = dataBytes.length;
        if (dataLength != 0) {
            if (dataLength % DATA_LENGTH_MAX > 0) {
                packetCount = dataLength / DATA_LENGTH_MAX + 1;
            } else {
                packetCount = dataLength / DATA_LENGTH_MAX;
            }
        } else {
            packetCount = 1;
        }
        remainPack = packetCount - 1;
        packetIndex = 0;
        delayTime = DEFAULT_DELAY_TIME + 500L * packetCount;
        if (packetCount > 1) {
            data = new byte[DATA_LENGTH_MAX + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) key.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) DATA_LENGTH_MAX;
            for (int i = 0; i < DATA_LENGTH_MAX; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        } else {
            data = new byte[dataLength + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) key.getParamsKey();
            data[3] = (byte) 0x01;
            data[4] = (byte) packetIndex;
            data[5] = (byte) dataLength;
            System.arraycopy(dataBytes, 0, data, 6, dataLength);
        }
    }

    private int packetCount;
    private int packetIndex;
    private int remainPack;
    private int dataLength;
    private int dataOrigin;
    private byte[] dataBytes;
    private String dataBytesStr = "";
    private static final int DATA_LENGTH_MAX = 232;

    @Override
    public boolean parseValue(byte[] value) {
        final int header = value[0] & 0xFF;
        final int flag = value[1] & 0xFF;
        if (header == 0xED) return true;
        if (flag == 0x01) {
            final int cmd = value[2] & 0xFF;
            final int result = value[4] & 0xFF;
            if (result == 1) {
                remainPack--;
                packetIndex++;
                if (remainPack >= 0) {
                    assembleRemainData(cmd);
                    return false;
                }
                return true;
            }
        } else {
            final int cmd = value[2] & 0xFF;
            final int packetCount = value[3] & 0xFF;
            final int indexPack = value[4] & 0xFF;
            final int length = value[5] & 0xFF;
            if (indexPack < (packetCount - 1)) {
                byte[] remainBytes = Arrays.copyOfRange(value, 6, 6 + length);
                dataBytesStr += MokoUtils.bytesToHexString(remainBytes);
            } else {
                if (length == 0) {
                    data = new byte[5];
                    data[0] = (byte) 0xEE;
                    data[1] = (byte) 0x00;
                    data[2] = (byte) cmd;
                    data[3] = 0;
                    data[4] = 0;
                    response.responseValue = data;
                    orderStatus = ORDER_STATUS_SUCCESS;
                    MokoSupport.getInstance().pollTask();
                    MokoSupport.getInstance().executeTask();
                    MokoSupport.getInstance().orderResult(response);
                    return false;
                }
                byte[] remainBytes = Arrays.copyOfRange(value, 6, 6 + length);
                dataBytesStr += MokoUtils.bytesToHexString(remainBytes);
                dataBytes = MokoUtils.hex2bytes(dataBytesStr);
                dataLength = dataBytes.length;
                byte[] dataLengthBytes = MokoUtils.toByteArray(dataLength, 2);
                data = new byte[dataLength + 5];
                data[0] = (byte) 0xEE;
                data[1] = (byte) 0x00;
                data[2] = (byte) cmd;
                data[3] = dataLengthBytes[0];
                data[4] = dataLengthBytes[1];
                System.arraycopy(dataBytes, 0, data, 5, dataLength);
                response.responseValue = data;
                orderStatus = ORDER_STATUS_SUCCESS;
                MokoSupport.getInstance().pollTask();
                MokoSupport.getInstance().executeTask();
                MokoSupport.getInstance().orderResult(response);
                dataBytesStr = "";
            }
        }
        return false;
    }

    private void assembleRemainData(int cmd) {
        int length = dataLength - dataOrigin;
        if (length > DATA_LENGTH_MAX) {
            data = new byte[DATA_LENGTH_MAX + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) cmd;
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) DATA_LENGTH_MAX;
            for (int i = 0; i < DATA_LENGTH_MAX; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        } else {
            data = new byte[length + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) cmd;
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) length;
            for (int i = 0; i < length; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        }
        MokoSupport.getInstance().sendDirectOrder(this);
    }

    public void setIBeaconEnable(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_I_BEACON_SWITCH.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setIBeaconMajor(@IntRange(from = 0, to = 65535) int major) {
        byte[] bytes = MokoUtils.toByteArray(major, 2);
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_I_BEACON_MAJOR.getParamsKey(),
                (byte) 0x02,
                bytes[0],
                bytes[1]
        };
    }

    public void setIBeaconMinor(@IntRange(from = 0, to = 65535) int minor) {
        byte[] bytes = MokoUtils.toByteArray(minor, 2);
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_I_BEACON_MINOR.getParamsKey(),
                (byte) 0x02,
                bytes[0],
                bytes[1]
        };
    }

    public void setIBeaconUuid(String uuid) {
        byte[] uuidBytes = MokoUtils.hex2bytes(uuid);
        int length = uuidBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_I_BEACON_UUID.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(uuidBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setIBeaconAdInterval(@IntRange(from = 1, to = 100) int interval) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_I_BEACON_AD_INTERVAL.getParamsKey(),
                (byte) 0x01,
                (byte) interval
        };
    }

    public void setIBeaconTxPower(@IntRange(from = 0, to = 15) int txPower) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_I_BEACON_TX_POWER.getParamsKey(),
                (byte) 0x01,
                (byte) txPower
        };
    }

    public void setIBeaconRssi1M(@IntRange(from = -100, to = 0) int rssi1M) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_I_BEACON_RSSI1M.getParamsKey(),
                (byte) 0x01,
                (byte) rssi1M
        };
    }

    public void setWifiSecurityType(@IntRange(from = 0, to = 1) int type) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_WIFI_SECURITY_TYPE.getParamsKey(),
                (byte) 0x01,
                (byte) type
        };
    }

    public void setWifiSSID(String SSID) {
        byte[] dataBytes = SSID.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_WIFI_SSID.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setWifiEapType(@IntRange(from = 0, to = 2) int type) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_WIFI_EAP_TYPE.getParamsKey(),
                (byte) 0x01,
                (byte) type
        };
    }

    public void setWifiEapUsername(String username) {
        byte[] dataBytes = username.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_WIFI_EAP_USERNAME.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setWifiEapPassword(String password) {
        byte[] dataBytes = password.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_WIFI_EAP_PASSWORD.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setWifiEapDomainId(String domainId) {
        byte[] dataBytes = domainId.getBytes();
        int length = dataBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_WIFI_EAP_DOMAIN_ID.getParamsKey();
        data[3] = (byte) length;
        System.arraycopy(dataBytes, 0, data, 4, length);
        response.responseValue = data;
    }

    public void setWifiEapVerifyServiceEnable(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_WIFI_EAP_VERIFY_SERVICE_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setNetworkDHCP(@IntRange(from = 0, to = 1) int enable) {
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_NETWORK_DHCP.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setNetworkIPInfo(String ip, String networkMask, String gateway, String dns) {
        byte[] ipBytes = MokoUtils.hex2bytes(ip);
        byte[] networkMaskBytes = MokoUtils.hex2bytes(networkMask);
        byte[] gatewayBytes = MokoUtils.hex2bytes(gateway);
        byte[] dnsBytes = MokoUtils.hex2bytes(dns);
        response.responseValue = data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_NETWORK_IP_INFO.getParamsKey(),
                (byte) 0x10,
                ipBytes[0],
                ipBytes[1],
                ipBytes[2],
                ipBytes[3],
                networkMaskBytes[0],
                networkMaskBytes[1],
                networkMaskBytes[2],
                networkMaskBytes[3],
                gatewayBytes[0],
                gatewayBytes[1],
                gatewayBytes[2],
                gatewayBytes[3],
                dnsBytes[0],
                dnsBytes[1],
                dnsBytes[2],
                dnsBytes[3],
        };
    }
}
