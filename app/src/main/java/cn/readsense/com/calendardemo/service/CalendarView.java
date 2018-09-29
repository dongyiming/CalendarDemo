package cn.readsense.com.calendardemo.service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: dongyiming
 * @date: 2018/9/28 22:44
 * @version: v1.0
 */
public interface CalendarView {

    /**
     * <TODO>
     *
     * @param:startDate 开始月份
     * @param:endDate 结束月份
     * @param:selectedDates 当前选中时间或时间段
     * @param:isTimePeriod 是否是时间段，true代表是
     * @author: dongyiming
     * @date: 2018/9/25 0:13
     * @version: v1.0
     */
    void initTime(Date startDate, Date endDate, List<Date> selectedDates, boolean isTimePeriod);
}
