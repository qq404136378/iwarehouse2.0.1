package com.prt.iwarehouse.modules.functionChoice;

import android.content.IntentFilter;

import com.prt.iwarehouse.R;
import com.prt.iwarehouse.broadcast.ProgressBroadCast;
import com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceElectron.ChoiceElectronFragment;
import com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceElectron.ChoiceSmtFragment;
import com.zzz.mvp.base.BaseMvpActivity;
import com.zzz.mvp.base.BaseMvpFragment;


public class FunctionChoiceActivity extends BaseMvpActivity {
    private BaseMvpFragment fragment;
    private ProgressBroadCast progressBroadCast;
    @Override
    protected int setContentView() {
        return R.layout.activity_function;
    }

    @Override
    protected void initView() {
        String storageName=getIntent().getStringExtra("storageName");
        if(storageName.equals("电子仓")){
            fragment=new ChoiceElectronFragment();
        }else if(storageName.equals("SMT")){
            fragment=new ChoiceSmtFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.function_fl_content,fragment)
                .commit();
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void  onResume() {
        progressBroadCast=new ProgressBroadCast();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("PROGRESS_DIALOG");
        registerReceiver(progressBroadCast,intentFilter);
        super.onResume();
    }
     @Override
    protected  void onPause() {
         unregisterReceiver(progressBroadCast);
         super.onPause();
     }
}
