package com.prt.iwarehouse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apkfuns.xprogressdialog.XProgressDialog;
import com.prt.iwarehouse.modules.login.LoginActivity;

/**
 * Created by 锴锴兴 on 2018/12/12.
 */

public class ProgressBroadCast extends BroadcastReceiver {
    private XProgressDialog xProgressDialog;
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
         this.context=context;
         if(intent.getBooleanExtra("isShowDialog",false)){
             showProgressDialog();
         }else{
             cancelProgressDialog();
         }
    }
    protected  void showProgressDialog(){
        if(xProgressDialog==null) {
            xProgressDialog = new XProgressDialog(context,
                    "正在加载中...",
                    XProgressDialog.THEME_HORIZONTAL_SPOT);
        }
        if(!xProgressDialog.isShowing()&&xProgressDialog!=null)
            xProgressDialog.show();
    }
    protected  void cancelProgressDialog(){
        if(xProgressDialog!=null&&xProgressDialog.isShowing()) {
            xProgressDialog.dismiss();
            xProgressDialog=null;
        }
    }
}
