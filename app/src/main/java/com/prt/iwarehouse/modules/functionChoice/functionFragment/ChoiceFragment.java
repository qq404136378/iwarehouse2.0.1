package com.prt.iwarehouse.modules.functionChoice.functionFragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.app.Constant;
import com.zzz.mvp.base.BaseMvpFragment;

/**
 * Created by 锴锴兴 on 2018/12/12.
 */

public class ChoiceFragment extends BaseMvpFragment implements View.OnClickListener{
    private TextView tv_function_electron;
    private TextView tv_function_smt;
    private BaseMvpFragment fragment;
    @Override
    protected int createView() {
        return R.layout.fragment_function_second;
    }

    @Override
    protected void initView(View view) {
      tv_function_electron=view.findViewById(R.id.frg_second_tv_function_electron);
      tv_function_electron.setOnClickListener(this);
      tv_function_smt=view.findViewById(R.id.frg_second_tv_function_smt);
      tv_function_smt.setOnClickListener(this);
    }
    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View view) {
        KLoggerToast.showText(getContext(),"aaa",Toast.LENGTH_SHORT);
        switch (view.getId()){
            case R.id.frg_second_tv_function_electron:
                fragment=new ChoiceFragmentElectron();
                break;
            case R.id.frg_second_tv_function_smt:
                fragment=new ChoiceFragmentSmt();
                break;
            default:
                break;
        }
        if(fragment!=null){
            getActivity()
                    .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.function_fl_content, fragment)
                        .commit();
            Constant.FLAG_FUNCTION=1;
        }

    }
}
