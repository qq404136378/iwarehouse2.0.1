package com.prt.iwarehouse.modules.ip;

import com.prt.iwarehouse.model.IpDataModel;
import com.zzz.mvp.base.BasePresenter;
import com.zzz.mvp.inject.InjectModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 请叫我张懂
 * @Date 2018/3/29 10:30
 * @Description
 */

public class IpPresenter extends BasePresenter<IpContract.IipView> implements IpContract.IipPresenter {
    @InjectModel
    private IpDataModel ipDataModel;

    @Override
    public void toSaveIpInfo(final String ip) {
        ipDataModel.saveIpInfo(ip)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            getView().saveIpInfoSuccess(ip);
                        } else {
                            getView().saveIpInfoFail("保存IP错误");
                        }
                    }
                });
    }

    @Override
    public void toReadIpInfo() {
        ipDataModel.readIpInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String ip) throws Exception {
                        getView().readIpInfoSuccess(ip);
                    }
                });
    }
}
