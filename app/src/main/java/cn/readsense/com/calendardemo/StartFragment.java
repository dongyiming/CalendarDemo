package cn.readsense.com.calendardemo;

import android.app.Activity;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: <指定时间>
 * @author: dongyiming
 * @date: 2018/9/24 21:42
 * @version: v1.0
 */
public class StartFragment extends BaseCalendarFragment {

    private BindActivityCallBack callBack;
    private String startTime;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (BindActivityCallBack<String>) getActivity();
        startTime = ((Activity) context).getIntent().getStringExtra("startTime");
    }

    @Override
    public String getCompareTime() {
        return startTime;
    }

    @Override
    public void invoke(Date date) {
        callBack.call(new SimpleDateFormat("yyyy年MM月dd日").format(date), true);
    }
}
