package com.prt.baselibrary.log;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author 请叫我张懂
 * @Date 2018/2/11 18:37
 * @Description
 */
public class KLoggerToast {
    private static Toast mToast;
    private static MyHandler myHandler;
    private static WeakReference<Context> contextReference;

    public static void showText(Context context, CharSequence text, int duration) {
        if (null == contextReference || null == contextReference.get()){
            contextReference = new WeakReference<>(context);
        }
        if(null == contextReference.get()){
            throw new NullPointerException("Context is Null");
        }
        Message message = Message.obtain();
        message.obj = new ToastMessage(contextReference.get(), text, duration);
        if (null == myHandler) {
            HandlerThread handlerThread = new HandlerThread("KLoggerToast");
            handlerThread.start();
            myHandler = new MyHandler(handlerThread.getLooper());
        }
        myHandler.sendMessage(message);
    }


    private static class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (null != msg && msg.obj instanceof ToastMessage) {
                ToastMessage toastMessage = (ToastMessage) msg.obj;
                if (null != mToast) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(toastMessage.getContext(),
                        toastMessage.getMessage(),
                        toastMessage.getShowDuration());
                mToast.show();
            }
        }
    }
}
