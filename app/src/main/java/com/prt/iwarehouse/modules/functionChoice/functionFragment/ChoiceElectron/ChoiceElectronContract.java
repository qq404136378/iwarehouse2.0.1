package com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceElectron;

import com.prt.iwarehouse.pojo.Storage;
import com.prt.iwarehouse.pojo.StorageList;
import com.zzz.mvp.base.IBaseView;

import java.util.List;

/**
 * Created by 锴锴兴 on 2018/12/14.
 */

interface ChoiceElectronContract {
    interface IChoiceElectronView extends IBaseView{
        void getStorageListSuccess(Storage storageList);

        void getStorageListFail(String msg);

        void choiceCurrentStorageSuccess(String msg,String storageName);

        void choiceCurrentStorageFail(String msg);
    }
    interface IChoiceElectronPresenter {
        void  getStorageList();

        void  choiceCurrentStorage(String storageUuid);
    }
}
