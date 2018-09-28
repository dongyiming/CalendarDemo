package cn.readsense.com.calendardemo.fragment;

import android.app.Activity;
import android.content.Context;

import java.util.Date;
import java.util.List;

import cn.readsense.com.calendardemo.BindActivityCallBack;

/**
 * @description: <比对时间>
 * @author: dongyiming
 * @date: 2018/9/25 0:46
 * @version: v1.0
 */
public class EndFragment extends BaseCalendarFragment {

    private BindActivityCallBack<Date> callBack;
    private String endTime;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (BindActivityCallBack<Date>) getActivity();
        endTime = ((Activity) context).getIntent().getStringExtra("endTime");
    }

    @Override
    public String getCompareTime() {
        return endTime;
    }

    @Override
    public void invoke(List<Date> dates) {

        callBack.call(dates, 1);
    }
}

