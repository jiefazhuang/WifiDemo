package com.zjf.wifidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zjf.wifidemo.CustomDialog.WifiHotPassWord;
import com.zjf.wifidemo.wifihotsport.WifiHotUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnCreate = null;
    private Button mBtnClose = null;
    private Button mBtnChangePassWord = null;

    private WifiHotUtil mWifiHotUtil = null;
    private WifiHotPassWord mWifiHotPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void initView() {
        mBtnCreate = findViewById(R.id.btn_create_hostspot);
        mBtnCreate.setOnClickListener(this);
        mBtnClose = findViewById(R.id.btn_close_hostspot);
        mBtnClose.setOnClickListener(this);
        mBtnChangePassWord = findViewById(R.id.btn_change_password);
        mBtnChangePassWord.setOnClickListener(this);
    }

    private void init() {
        mWifiHotUtil = new WifiHotUtil(this);
        mWifiHotPassWord = new WifiHotPassWord(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_hostspot:
                mWifiHotUtil.createWifiHotspot();
                break;
            case R.id.btn_close_hostspot:
                mWifiHotUtil.closeWifiHotspot();
                break;
            case R.id.btn_change_password:
                mWifiHotPassWord.show();
                break;
        }
    }
}
