package com.prt.iwarehouse.modules.home;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.app.Constant;
import com.prt.iwarehouse.modules.home.put.PutFragment;
import com.zzz.mvp.base.BaseMvpActivity;

public class HomeActivity extends BaseMvpActivity {
    private TextView tv_current_function;
    private TextView tv_current_account;
    private TextView tv_current_storage;
    private TextView tv_scanner;
    private FunctionFragment functionFragment;


    @Override
    protected int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
       tv_current_account=this.findViewById(R.id.home_tv_current_account);
        tv_current_function=this.findViewById(R.id.home_tv_current_function);
        tv_current_storage=this.findViewById(R.id.home_tv_current_storage);
        tv_scanner=this.findViewById(R.id.home_tv_scanner);
    }

    @Override
    protected void initData() {
        Bundle bundle=getIntent().getBundleExtra("msg");
       tv_current_account.setText(Constant.user.getUsername());
       tv_current_storage.setText(bundle.getString("storage"));
       tv_current_function.setText("入库");
       getSupportFragmentManager()
               .beginTransaction()
                   .replace(R.id.home_fl_home,new PutFragment())
                 .commit();
    }
}
