package com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceElectron;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.dialog.StorageListDialog;
import com.prt.iwarehouse.modules.home.HomeActivity;
import com.prt.iwarehouse.pojo.Storage;
import com.zzz.mvp.base.BaseMvpFragment;
import com.zzz.mvp.inject.InjectPresenter;

/**
 * Created by 锴锴兴 on 2018/12/12.
 */

public class ChoiceElectronFragment extends BaseMvpFragment implements View.OnClickListener,ChoiceContract.IChoiceElectronView {
    private StorageListDialog storageListDialog;
    @InjectPresenter
    ChoicePresenter choiceElectronPresenter;
    private Intent intent;
    @Override
    protected int createView() {
        return R.layout.fragment_function_electron;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.frg_electron_tv_function_choice).setOnClickListener(this);
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
            //    KLoggerToast.showText(getActivity(),"点击了put", Toast.LENGTH_SHORT);
                goHomeActivity("put");
                break;
            case R.id.frg_electron_btn_stock:
                KLoggerToast.showText(getActivity(),"点击了stock", Toast.LENGTH_SHORT);
                goHomeActivity("out");
                break;
            case R.id.frg_electron_btn_car:
                KLoggerToast.showText(getActivity(),"点击了car", Toast.LENGTH_SHORT);
                goHomeActivity("car");
                break;
            case R.id.frg_electron_btn_inventory:
                KLoggerToast.showText(getActivity(),"点击了inventory", Toast.LENGTH_SHORT);
                goHomeActivity("inventory");
                break;
            case R.id.frg_electron_btn_more:
                KLoggerToast.showText(getActivity(),"点击了more", Toast.LENGTH_SHORT);
               goHomeActivity("more");
                break;
            case  R.id.frg_electron_tv_function_choice:
                choiceElectronPresenter.getStorageList();
                break;
            default:
                break;
        }

    }
    private void goHomeActivity(String function) {
        intent=new Intent(getActivity(), HomeActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("function",function);
        bundle.putString("storage","电子仓");
        //intent.putExtra("function",function);
        intent.putExtra("msg",bundle);
        getActivity().startActivity(intent);
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
        KLoggerToast.showText(getContext(),msg,Toast.LENGTH_SHORT);
        if(storageListDialog!=null&&storageListDialog.isShowing()) {
            storageListDialog.dismiss();
        }
    }

    @Override
    public void choiceCurrentStorageSuccess(String msg,String storageName) {
        KLoggerToast.showText(getContext(),msg,Toast.LENGTH_SHORT);
        if(!storageName.equals("电子仓")) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.function_fl_content, new ChoiceSmtFragment())
                    .commit();
        }
    }

    @Override
    public void choiceCurrentStorageFail(String msg) {
        KLoggerToast.showText(getContext(),msg,Toast.LENGTH_SHORT);
    }
}
