package cn.readsense.com.calendardemo.fragment;

import android.app.Activity;
import android.content.Context;

import java.util.Date;
import java.util.List;

import cn.readsense.com.calendardemo.service.BindActivityCallBack;

/**
 * @description: <指定时间>
 * @author: dongyiming
 * @date: 2018/9/24 21:42
 * @version: v1.0
 */
public class StartFragment extends BaseCalendarFragment {

    private BindActivityCallBack<Date> callBack;
    private String startTime;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (BindActivityCallBack) getActivity();
        startTime = ((Activity) context).getIntent().getStringExtra("startTime");
    }

    @Override
    public String getCompareTime() {
        return startTime;
    }

    @Override
    public void invoke(List<Date> dates) {
        callBack.call(dates, 0);
    }
}
