package com.zjf.wifidemo.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.zjf.wifidemo.R;

/**
 * Created by zhuangjiefa on 2018/4/28.
 *
 * @Package com.zjf.wifidemo.Dialog.
 * @Project_name WifiDemo.
 */

public class WifiHotPassWord extends Dialog {

    private static final String TAG = WifiHotPassWord.class.getSimpleName();
    private EditText mEditPassWord = null;
    private CheckBox mCbShowPassWord = null;
    private Button mBtnCancel = null;
    private Button mBtnOk = null;

    public WifiHotPassWord(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_wifihotsport_password);
        initView();
    }

    private void initView() {
        mEditPassWord = findViewById(R.id.edit_password);
        mCbShowPassWord = findViewById(R.id.checkbox_show_passwork);
        mCbShowPassWord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    mEditPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //未选中，隐藏密码
                    mEditPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);
    }
}
