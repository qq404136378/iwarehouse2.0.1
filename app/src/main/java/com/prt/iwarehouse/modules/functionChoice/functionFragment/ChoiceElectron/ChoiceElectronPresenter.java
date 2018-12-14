package com.prt.iwarehouse.modules.functionChoice.functionFragment.ChoiceElectron;

import com.prt.iwarehouse.http.HttpResult;
import com.prt.iwarehouse.http.IHttpCallback;
import com.prt.iwarehouse.http.ProgressObserver;
import com.prt.iwarehouse.model.UserModel;
import com.prt.iwarehouse.pojo.Storage;
import com.prt.iwarehouse.pojo.StorageList;
import com.zzz.mvp.base.BasePresenter;
import com.zzz.mvp.inject.InjectModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 锴锴兴 on 2018/12/14.
 */

public class ChoiceElectronPresenter extends BasePresenter<ChoiceElectronContract.IChoiceElectronView>
                              implements  ChoiceElectronContract.IChoiceElectronPresenter{
   @InjectModel
   private  UserModel userModel;
    @Override
    public void getStorageList() {
      userModel.getStorageList()
              .subscribeOn(Schedulers.io())
              .unsubscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new ProgressObserver<HttpResult<Storage>>(getContext(), true, new IHttpCallback<HttpResult<Storage>>() {
                  @Override
                  public void onSuccess(HttpResult<Storage> storageListHttpResult) {
                      getView().getStorageListSuccess(storageListHttpResult.getData());
                  }

                  @Override
                  public void onError(String message) {
                      getView().getStorageListFail(message);
                  }
              }));
    }
    @Override
    public void choiceCurrentStorage(final String storageUuid) {
        userModel.changeStorage(storageUuid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<HttpResult<Object>>(getContext(), true, new IHttpCallback<HttpResult<Object>>() {
                    @Override
                    public void onSuccess(HttpResult<Object> objectHttpResult) {
                        getView().choiceCurrentStorageSuccess(objectHttpResult.getMsg(),storageUuid);
                    }

                    @Override
                    public void onError(String message) {
                        getView().choiceCurrentStorageFail(message);
                    }
                }));
    }
}
