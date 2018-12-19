package com.prt.iwarehouse.modules.ip;

import com.zzz.mvp.base.IBaseView;

/**
 * @author 请叫我张懂
 * @Date 2018/3/29 10:27
 * @Description
 */

public interface IpContract {

    interface IipView extends IBaseView {
        void saveIpInfoSuccess(String ip);

        void saveIpInfoFail(String message);

        void readIpInfoSuccess(String ip);
    }

    interface IipPresenter {

        void toSaveIpInfo(String ip);

        void toReadIpInfo();
    }
}
