package cn.readsense.com.calendardemo

import java.util.*

/**
 * @description: <日历界面>
 * @author: dongyiming
 * @date: 2018/9/24 23:42
 * @version: v1.0
 */
public interface CalendarView {

    /**
     * <TODO>
     * @param:startDate 开始月份
     * @param:endDate 结束月份
     * @param:selectedDate 当前选中日期
     * @author: dongyiming
     * @date: 2018/9/25 0:13
     * @version: v1.0
     */
    fun initTime(startDate: Date, endDate: Date, selectedDate: Date)
}
