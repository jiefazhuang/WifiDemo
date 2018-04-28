package com.zjf.wifidemo.wifihotsport;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhuangjiefa on 2018/4/27.
 *
 * @Package com.zjf.wifidemo.wifihotsport.
 * @Project_name WifiDemo.
 */

public class WifiHotUtil {

    private static final String TAG =  WifiHotUtil.class.getSimpleName();
    private String mWifiHostName = "TEST";//热点名称
    private String mPassWord = "123456789";//热点密码
    private WifiManager mWifiManager = null;
    private Context mContext = null;

    public WifiHotUtil(Context context) {
        this.mContext = context;
        mWifiManager = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public void setWifiHostName(String name) {
        this.mWifiHostName = name;
    }

    public String getWifiHostName() {
        return mWifiHostName;
    }

    public void setPassWord(String passWord) {
        this.mPassWord = passWord;
    }

    public String getPassWord() {
        return  mPassWord;
    }

    /**
     * 创建Wifi热点
     */
    public void createWifiHotspot() {
        if (mWifiManager.isWifiEnabled()) {
            //如果wifi处于打开状态，则关闭wifi,
            mWifiManager.setWifiEnabled(false);
        }
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = mWifiHostName;
        config.preSharedKey = mPassWord;
        config.hiddenSSID = true;
        config.allowedAuthAlgorithms
                .set(WifiConfiguration.AuthAlgorithm.OPEN);//开放系统认证
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers
                .set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers
                .set(WifiConfiguration.PairwiseCipher.CCMP);
        config.status = WifiConfiguration.Status.ENABLED;
        //通过反射调用设置热点
        try {
            Method method = mWifiManager.getClass().getMethod(
                    "setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            boolean enable = (Boolean) method.invoke(mWifiManager, config, true);
            if (enable) {
                Log.i(TAG,"热点已开启 SSID:" + mWifiHostName + " mPassWord:" + mPassWord);
            } else {
                Log.i(TAG,"创建热点失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,"创建热点失败 e.getMessage():" + e.getMessage());
        }
    }

    /**
     * 关闭WiFi热点
     */
    public void closeWifiHotspot() {
        try {
            Method method = mWifiManager.getClass().getMethod("getWifiApConfiguration");
            method.setAccessible(true);
            WifiConfiguration config = (WifiConfiguration) method.invoke(mWifiManager);
            Method method2 = mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method2.invoke(mWifiManager, config, false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"热点关闭");
    }
}
