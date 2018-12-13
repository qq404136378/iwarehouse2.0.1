package com.prt.iwarehouse.modules.functionChoice;

import android.view.KeyEvent;

import com.prt.iwarehouse.R;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceFragment;
import com.zzz.mvp.base.BaseMvpActivity;
import com.zzz.mvp.base.BaseMvpFragment;


public class FunctionChoiceActivity extends BaseMvpActivity {
    private BaseMvpFragment choiceFragment;
    @Override
    protected int setContentView() {
        return R.layout.activity_function;
    }

    @Override
    protected void initView() {
        choiceFragment=new ChoiceFragment();
        Constant.FLAG_FUNCTION=0;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.function_fl_content,choiceFragment)
                .commit();
    }
    @Override
    protected void initData() {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(Constant.FLAG_FUNCTION==1){
            initView();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
