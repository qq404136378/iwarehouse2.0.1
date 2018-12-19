package com.prt.baselibrary.log;

import android.content.Context;


/**
 * @author 请叫我张懂
 * @Date 2018/2/11 21:08
 * @Description
 */

class ToastMessage {
    private Context context;
    private CharSequence message;
    private int showDuration;

    ToastMessage(Context context, CharSequence text, int duration) {
        this.context = context;
        this.message = text;
        this.showDuration = duration;
    }

    Context getContext() {
        return context;
    }


    CharSequence getMessage() {
        return message;
    }


    int getShowDuration() {
        return showDuration;
    }

}
