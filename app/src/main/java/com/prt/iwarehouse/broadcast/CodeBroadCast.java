package com.prt.iwarehouse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 注册广播的Action
 * "android.intent.ACTION_DECODE_DATA";
 */
public class CodeBroadCast extends BroadcastReceiver {
    private CodeBroadCastListener listener;

    public void setCodeListener(CodeBroadCastListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) {
           listener.onReceiverCode(intent.getStringExtra("value").trim());
        }
    }

    public interface CodeBroadCastListener {
        void onReceiverCode(String code);
    }
}
