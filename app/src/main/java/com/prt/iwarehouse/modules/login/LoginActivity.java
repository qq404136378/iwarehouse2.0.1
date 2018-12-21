package com.prt.iwarehouse.modules.login;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.broadcast.ProgressBroadCast;
import com.prt.iwarehouse.dialog.IpSettingDialog;
import com.prt.iwarehouse.dialog.StorageListDialog;
import com.prt.iwarehouse.http.RetrofitManager;
import com.prt.iwarehouse.modules.functionChoice.FunctionChoiceActivity;
import com.prt.iwarehouse.pojo.StorageList;
import com.prt.iwarehouse.pojo.User;
import com.zzz.mvp.base.BaseMvpActivity;
import com.zzz.mvp.inject.InjectPresenter;

import java.util.List;

//登录系统

public class LoginActivity extends BaseMvpActivity implements View.OnClickListener,LoginContract.ILoginView {
    private EditText et_account;
    private EditText et_password;
    private TextView tv_login;
    private TextView tv_setting;
    private String ipInfo="";
    private  ProgressBroadCast progressBroadCast;
    private StorageListDialog storageListDialog;
    @InjectPresenter
    private LoginPresenter loginPresenter;
    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        et_account=this.findViewById(R.id.login_activity_et_account);
        et_password=this.findViewById(R.id.login_activity_et_password);
        tv_login=this.findViewById(R.id.login_activity_tv_login);
        tv_login.setOnClickListener(this);
        tv_setting=this.findViewById(R.id.login_activity_tv_setting);
        tv_setting.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        loginPresenter.readIpInfo();
        loginPresenter.readUserInfo();
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.login_activity_tv_login){
            checkLogin();
        }else if(id==R.id.login_activity_tv_setting){
           showIpDialog();
        }
    }
   //检查登陆
    private void checkLogin() {
        String account=et_account.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        if(account.isEmpty()||password.isEmpty()){
            KLoggerToast.showText(LoginActivity.this,"请输入用户名或密码", Toast.LENGTH_SHORT);
        }else
            LoginSuccess("登陆成功","电子仓");
         //   loginPresenter.toLogin(account,password);
    }
    //展示IP设置窗口
    private void showIpDialog() {
        IpSettingDialog ipSettingDialog=new IpSettingDialog.Builder(LoginActivity.this)
                .setDefaulIp(ipInfo)
                .builder();
        ipSettingDialog.setOnSettingListener(new IpSettingDialog.OnSettingListener() {
            @Override
            public void onSetting(String ip) {
                loginPresenter.saveIpInfo(ip);
            }
        });
        ipSettingDialog.show();
    }

    @Override
    public void LoginSuccess(String msg,String storageName) {
        String account=et_account.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        loginPresenter.saveUserInfo(account,password);
        Constant.user=new User(account,password);
        Intent intent=new Intent(LoginActivity.this, FunctionChoiceActivity.class);
        intent.putExtra("storageName",storageName);
        startActivity(intent);
        KLoggerToast.showText(LoginActivity.this,msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void LoginFail(String msg) {
        KLoggerToast.showText(LoginActivity.this,msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void readIpInfoSuccess(String ip) {
        ipInfo=ip;
    }
    @Override
    public void saveIpInfoSuccess(String ip) {
        RetrofitManager.getInstance().init(ip);
        ipInfo=ip;
    }

    @Override
    public void saveIpInfoFail(String msg) {
       KLoggerToast.showText(LoginActivity.this,msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void readUserInfoSuccess(User user) {
        if(user!=null) {
            et_account.setText(user.getUsername());
            et_password.setText(user.getPassword());
        }
    }
    private void saveUserInfo(){
        String account=et_account.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        loginPresenter.saveUserInfo(account,password);
    }
    @Override
    protected void onPause() {
        saveUserInfo();
        unregisterReceiver(progressBroadCast);
        super.onPause();
    }
    @Override
    protected void onResume() {
        registerProgressReceiver();
        super.onResume();
    }
    //注册让进度条消失的广播
     private void registerProgressReceiver(){
         IntentFilter intentFilter=new IntentFilter();
         intentFilter.addAction("PROGRESS_DIALOG");
         progressBroadCast=new ProgressBroadCast();
         registerReceiver(progressBroadCast,intentFilter);
     }
    @Override
    public void showStorageList(List<StorageList> storageList) {
         storageListDialog=new StorageListDialog.Builder(LoginActivity.this)
                .setStorageList(storageList)
                .builder();
        storageListDialog.setSelectStorageListener(new StorageListDialog.SelectStorageListener() {
            @Override
            public void selectStorage(String storageUuid) {
                loginPresenter.changeStorage(storageUuid);
            }
        });
        storageListDialog.show();
    }

    @Override
    public void changeStorageSuccess(String msg) {
        if(storageListDialog!=null&&storageListDialog.isShowing()){
            storageListDialog.dismiss();
        }
        KLoggerToast.showText(LoginActivity.this,msg+"再次点击登陆",Toast.LENGTH_SHORT);
    }

    @Override
    public void changeStorageFail(String msg) {
        KLoggerToast.showText(LoginActivity.this,msg,Toast.LENGTH_SHORT);
    }
}
