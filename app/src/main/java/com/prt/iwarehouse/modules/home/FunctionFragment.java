package com.prt.iwarehouse.modules.home;

import android.support.v4.app.DialogFragment;

import com.apkfuns.xprogressdialog.XProgressDialog;
import com.mylhyl.circledialog.CircleDialog;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.broadcast.CodeBroadCast;
import com.prt.iwarehouse.utils.SoundPlayer;
import com.zzz.mvp.base.BaseMvpFragment;

/**
 * @author 请叫我张懂
 * @Date 2018/3/31 9:30
 * @Description
 */

public abstract class FunctionFragment extends BaseMvpFragment implements CodeBroadCast.CodeBroadCastListener {

    private DialogFragment dialog;
    private SoundPlayer soundPlayer;
    private OnFunctionChangeListener onFunctionChangeListener;
    private XProgressDialog xProgressDialog;


    public void setOnFunctionChangeListener(OnFunctionChangeListener onFunctionChangeListener) {
        this.onFunctionChangeListener = onFunctionChangeListener;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveInstanceInfo();
    }

    protected void showFailDialog(String message) {
        if(xProgressDialog!=null&&xProgressDialog.isShowing()){
            xProgressDialog.dismiss();
        }
        dialog = new CircleDialog.Builder()
                .setTitle("提示")
                .setText(message)
                .setPositive("我知道了", null)

                .show(getFragmentManager());
        if (soundPlayer == null) {
            soundPlayer = new SoundPlayer(getContext());
        }
        soundPlayer.play(R.raw.success, false);

    }
    protected void showProgress(){
        if (xProgressDialog == null) {
            xProgressDialog = new XProgressDialog(getContext(),
                    "",
                    XProgressDialog.THEME_HORIZONTAL_SPOT);
        }
        if(!xProgressDialog.isShowing()) {
            xProgressDialog.show();
        }
    }
    protected void hideFailDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    protected void hideProgressDialog(){
        if(xProgressDialog!=null&&xProgressDialog.isShowing()){
            xProgressDialog.dismiss();
        }
    }
    public void playErrorVoice() {
        if (soundPlayer == null) {
            soundPlayer = new SoundPlayer(getContext());
        }
        soundPlayer.play(R.raw.success, false);
    }


    protected void changeFunction(String functionDetail) {
        if (onFunctionChangeListener != null) {
            onFunctionChangeListener.onChange(functionDetail);
        }
    }
    protected abstract void saveInstanceInfo();

    public interface OnFunctionChangeListener {
        void onChange(String functionDetail);
    }

}
