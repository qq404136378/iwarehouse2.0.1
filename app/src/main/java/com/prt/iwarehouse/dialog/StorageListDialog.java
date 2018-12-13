package com.prt.iwarehouse.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.prt.baselibrary.log.KLoggerToast;
import com.prt.baselibrary.recyclerview.CommonAdapter;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.adapter.SelectStorageAdapter;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.modules.login.LoginPresenter;
import com.prt.iwarehouse.pojo.StorageList;
import com.zzz.mvp.inject.InjectPresenter;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by 锴锴兴 on 2018/12/13.
 */

public class StorageListDialog  extends Dialog {
    private List<StorageList> storageList;
    private RecyclerView recyclerView;
    private Context context;
    private SelectStorageListener selectStorageListener;

    public void setSelectStorageListener(SelectStorageListener selectStorageListener) {
        this.selectStorageListener = selectStorageListener;
    }

    private  StorageListDialog(Context context, Builder builder){
        super(context, R.style.CommonDialog);
        this.storageList=builder.storageList;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_storage);
        initView();
        initData();
    }

    private void initData() {
        SelectStorageAdapter selectStorageAdapter=new SelectStorageAdapter(context,storageList,R.layout.item_select_storage);
      //  KLoggerToast.showText(context,storageList.toString(), Toast.LENGTH_SHORT);
        selectStorageAdapter.setItemClickListener(new CommonAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
              Constant.storageName=storageList.get(position).getName();
              if(selectStorageListener!=null){
                  selectStorageListener.selectStorage(storageList.get(position).getStorageUuid());
              }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(selectStorageAdapter);
    }

    private void initView() {
        recyclerView=this.findViewById(R.id.dg_rv_storage_select);
    }

    public  static class Builder{
        private List<StorageList> storageList;
        private Context context;

        public  Builder(Context context){
            this.context=context;
        }
        public Builder setStorageList(List<StorageList> storageList){
            this.storageList=storageList;
            return this;
        }


        public StorageListDialog builder(){
            return new StorageListDialog(context,this);
        }
    }

    public  interface SelectStorageListener{
        void  selectStorage(String storageUuid);
    }
}
