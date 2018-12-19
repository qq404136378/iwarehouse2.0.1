package com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceElectron;

import android.view.View;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.dialog.StorageListDialog;
import com.prt.iwarehouse.pojo.Storage;
import com.zzz.mvp.base.BaseMvpFragment;
import com.zzz.mvp.inject.InjectPresenter;

/**
 * Created by 锴锴兴 on 2018/12/12.
 */

public class ChoiceSmtFragment extends BaseMvpFragment implements View.OnClickListener,ChoiceContract.IChoiceElectronView{
    private StorageListDialog storageListDialog;
    @InjectPresenter
    ChoicePresenter choiceElectronPresenter;
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
        view.findViewById(R.id.frg_smt_tv_function_choice).setOnClickListener(this);
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
            case R.id.frg_smt_tv_function_choice:
                choiceElectronPresenter.getStorageList();
                break;
            default:
                break;
        }
    }

    @Override
    public void getStorageListSuccess(Storage storageList) {
        storageListDialog=new StorageListDialog.Builder(getContext())
                .setStorageList(storageList.getStorageList())
                .builder();
        storageListDialog.setSelectStorageListener(new StorageListDialog.SelectStorageListener() {
            @Override
            public void selectStorage(String storageUuid) {
                choiceElectronPresenter.choiceCurrentStorage(storageUuid);
                storageListDialog.dismiss();
            }
        });
        storageListDialog.show();
    }

    @Override
    public void getStorageListFail(String msg) {
        KLoggerToast.showText(getContext(),msg, Toast.LENGTH_SHORT);
        if(storageListDialog!=null&&storageListDialog.isShowing()) {
            storageListDialog.dismiss();
        }
    }

    @Override
    public void choiceCurrentStorageSuccess(String msg,String storageName) {
        KLoggerToast.showText(getContext(),msg,Toast.LENGTH_SHORT);
        if(!storageName.equals("SMT")) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.function_fl_content, new ChoiceElectronFragment())
                    .commit();
        }
    }

    @Override
    public void choiceCurrentStorageFail(String msg) {
        KLoggerToast.showText(getContext(),msg,Toast.LENGTH_SHORT);
    }
}
