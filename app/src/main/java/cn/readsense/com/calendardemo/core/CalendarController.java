package cn.readsense.com.calendardemo.core;

import android.content.Context;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.readsense.com.calendardemo.service.CalendarView;

/**
 * @description: <日历界面业务定制层>
 * @author: dongyiming
 * @date: 2018/9/24 22:44
 * @version: v1.0
 */
public class CalendarController {

    private CalendarView calendarView;
    private List<Date> dates = new ArrayList<>();
    private final SimpleDateFormat simpleDateFormat;
    private static final String DATE_TYPE = "yyyy/MM/dd";

    public CalendarController(Context context, CalendarView calendarView) {

        WeakReference<Context> mContext = new WeakReference<>(context);
        this.calendarView = calendarView;
        simpleDateFormat = new SimpleDateFormat(DATE_TYPE, Locale.CHINA);
    }

    /**
     * <! 根据传入的时间/时间段{@code time}，选择界面可滑动到的最小时间和最终时间>
     *
     * @param: time\传入的时间、时间段，格式：2018/6/5 - 2018/7/7
     * @return:
     * @author: dongyiming
     * @date: 2018/9/25 0:13
     * @version: v1.0
     */
    public void initTime(String time) {
        //parseSelectedTime(time);
        boolean isTimePeriod = false;
        Calendar startCalendar = Calendar.getInstance();
        String[] arrayTime = time.split(" - ");
        long diff = TimeUtils.getTimeSpanByNow(arrayTime[0], simpleDateFormat, TimeConstants.DAY);
        if (arrayTime.length == 1) {
            Date date = TimeUtils.string2Date(arrayTime[0], simpleDateFormat);
            dates.add(date);
            if (diff < 0) {//不超过当前时间，给定时间往前计数10年
                startCalendar.setTime(date);
            } else {
                startCalendar.setTime(TimeUtils.getNowDate());
            }
        } else {
            Date startSelectedDate = TimeUtils.string2Date(arrayTime[0], simpleDateFormat);
            Date endSelectedDate = TimeUtils.string2Date(arrayTime[1], simpleDateFormat);
            dates.add(startSelectedDate);
            dates.add(endSelectedDate);
            if (diff < 0) {//不超过当前时间，给定时间往前计数10年
                startCalendar.setTime(startSelectedDate);
            } else {
                startCalendar.setTime(TimeUtils.getNowDate());
            }
            isTimePeriod = true;
        }

        startCalendar.add(Calendar.YEAR, -10);
        Date startDate = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DATE, 1);
        Date endDate = endCalendar.getTime();
        calendarView.initTime(startDate, endDate, dates, isTimePeriod);
    }
}

