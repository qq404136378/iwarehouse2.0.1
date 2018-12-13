package com.prt.iwarehouse.modules.functionChoice.functionFragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.zzz.mvp.base.BaseMvpFragment;

/**
 * Created by 锴锴兴 on 2018/12/12.
 */

public class ChoiceFragmentElectron extends BaseMvpFragment implements View.OnClickListener {

    @Override
    protected int createView() {
        return R.layout.fragment_function_electron;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.frg_electron_btn_put).setOnClickListener(this);
        view.findViewById(R.id.frg_electron_btn_stock).setOnClickListener(this);
        view.findViewById(R.id.frg_electron_btn_car).setOnClickListener(this);
        view.findViewById(R.id.frg_electron_btn_more).setOnClickListener(this);
        view.findViewById(R.id.frg_electron_btn_inventory).setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frg_electron_btn_put:
                KLoggerToast.showText(getActivity(),"点击了put", Toast.LENGTH_SHORT);
                break;
            case R.id.frg_electron_btn_stock:
                KLoggerToast.showText(getActivity(),"点击了stock", Toast.LENGTH_SHORT);
                break;
            case R.id.frg_electron_btn_car:
                KLoggerToast.showText(getActivity(),"点击了car", Toast.LENGTH_SHORT);
                break;
            case R.id.frg_electron_btn_inventory:
                KLoggerToast.showText(getActivity(),"点击了inventory", Toast.LENGTH_SHORT);
                break;
            case R.id.frg_electron_btn_more:
                KLoggerToast.showText(getActivity(),"点击了more", Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
    }
}
