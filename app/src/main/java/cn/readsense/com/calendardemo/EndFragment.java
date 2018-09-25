package cn.readsense.com.calendardemo;

import android.app.Activity;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: <比对时间>
 * @author: dongyiming
 * @date: 2018/9/25 0:46
 * @version: v1.0
 */
public class EndFragment extends BaseCalendarFragment {

    private BindActivityCallBack callBack;
    private String endTime;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (BindActivityCallBack<String>) getActivity();
        endTime = ((Activity) context).getIntent().getStringExtra("endTime");
    }

    @Override
    public String getCompareTime() {
        return endTime;
    }

    @Override
    public void invoke(Date date) {
        callBack.call(new SimpleDateFormat("yyyy年MM月dd日").format(date), false);
    }
}

