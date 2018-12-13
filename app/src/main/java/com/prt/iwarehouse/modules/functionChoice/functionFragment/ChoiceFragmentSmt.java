package com.prt.iwarehouse.modules.functionChoice.functionFragment;

import android.view.View;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.zzz.mvp.base.BaseMvpFragment;

/**
 * Created by 锴锴兴 on 2018/12/12.
 */

public class ChoiceFragmentSmt extends BaseMvpFragment implements View.OnClickListener{

    @Override
    protected int createView() {
        return R.layout.fragment_function_smt;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.frg_smt_btn_put).setOnClickListener(this);
        view.findViewById(R.id.frg_smt_btn_stock).setOnClickListener(this);
        view.findViewById(R.id.frg_smt_btn_car).setOnClickListener(this);
        view.findViewById(R.id.frg_smt_btn_more).setOnClickListener(this);
        view.findViewById(R.id.frg_smt_btn_inventory).setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frg_smt_btn_put:
               
                break;
            case R.id.frg_smt_btn_stock:

                break;
            case R.id.frg_smt_btn_car:

                break;
            case R.id.frg_smt_btn_inventory:

                break;
            case R.id.frg_smt_btn_more:

                break;
            default:
                break;
        }
    }
}
