package com.prt.iwarehouse.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.http.RetrofitManager;

/**
 * @author 请叫我张懂
 * @Date 2018/4/9 15:40
 * @Description
 */

public class IpSettingDialog extends Dialog {
    private OnSettingListener onSettingListener;
    private String ip;

    public void setOnSettingListener(OnSettingListener onSettingListener) {
        this.onSettingListener = onSettingListener;
    }

    private IpSettingDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.CommonDialog);
        this.ip = builder.ip;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ip_setting);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        final EditText etIp = this.findViewById(R.id.et_ip);
        this.findViewById(R.id.tv_cancel)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
        this.findViewById(R.id.tv_setting)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ip = etIp.getText().toString();
                        if (TextUtils.isEmpty(ip)) {
                            KLoggerToast.showText(getContext(), "输入的内容不能为空", Toast.LENGTH_LONG);
                            return;
                        }
                        if (!Patterns.WEB_URL.matcher(ip).matches()) {
                            KLoggerToast.showText(getContext(), "IP输入格式错误", Toast.LENGTH_LONG);
                            return;
                        }
                        KLoggerToast.showText(getContext(),ip,Toast.LENGTH_SHORT);
                        if (onSettingListener != null) {
                            onSettingListener.onSetting(ip);
                        }
                        dismiss();
                    }
                });

        if (Patterns.WEB_URL.matcher(ip).matches()) {
            etIp.setText(ip);
            etIp.setSelection(ip.length());
        }
    }

    public interface OnSettingListener {
        void onSetting(String ip);
    }

    public static class Builder {
        private String ip;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDefaulIp(String defaultIp) {
            this.ip = defaultIp;
            return this;
        }

        public IpSettingDialog builder() {
            return new IpSettingDialog(context, this);
        }
    }
}
