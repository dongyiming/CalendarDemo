package cn.readsense.com.calendardemo;

import android.content.Context;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: <日历界面业务定制层>
 * @author: dongyiming
 * @date: 2018/9/24 22:44
 * @version: v1.0
 */
public class CalendarController {

    private Date startDate;
    private Date endDate;
    private Date selectedDate;

    private WeakReference<Context> mContext;
    private CalendarView calendarView;
    private final String DATE_TYPE = "yyyy年MM月dd日";

    public CalendarController(Context context, CalendarView calendarView) {

        mContext = new WeakReference<>(context);
        this.calendarView = calendarView;
    }

    /**
     * <TODO>
     *
     * @param:
     * @return:
     * @author: dongyiming
     * @date: 2018/9/25 0:13
     * @version: v1.0
     */
    public void initTime(String time) {
        long diff = TimeUtils.getTimeSpanByNow(time, new SimpleDateFormat(DATE_TYPE), TimeConstants.DAY);
        Calendar startCalendar = Calendar.getInstance();
        if (diff < 0) {//不超过当前时间，给定时间往前计数10年
            Date date = TimeUtils.string2Date(time, new SimpleDateFormat(DATE_TYPE));
            startCalendar.setTime(date);
            selectedDate = date;
        } else {
            selectedDate = TimeUtils.getNowDate();
        }
        startCalendar.add(Calendar.YEAR, -10);
        startDate = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DATE, 1);
        endDate = endCalendar.getTime();
        calendarView.initTime(startDate, endDate, selectedDate);
    }
}

