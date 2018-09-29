package cn.readsense.com.calendardemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import cn.readsense.com.calendardemo.R;
import cn.readsense.com.calendardemo.core.CalendarController;
import cn.readsense.com.calendardemo.service.CalendarView;
import cn.readsense.com.calendardemo.timessquare.CalendarPickerView;

/**
 * @description: TODO
 * @author: dongyiming
 * @date: 2018/9/25 1:14
 * @version: v1.0
 */
public abstract class BaseCalendarFragment extends Fragment implements CalendarView {

    private CalendarPickerView calendar_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, null);
        calendar_view = view.findViewById(R.id.calendar_view);
        CalendarController calendarController = new CalendarController(getActivity(), this);
        calendarController.initDate(getCompareTime());
        return view;
    }

    @Override
    public void initTime(Date startDate, Date endDate, List<Date> selectedDates, boolean isTimePeriod) {

        //是否是时间段
        if (isTimePeriod) {
            calendar_view.init(startDate, endDate).inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDates(selectedDates);
        } else {
            calendar_view.init(startDate, endDate).withSelectedDate(selectedDates.get(0)).inMode(CalendarPickerView.SelectionMode.RANGE);
        }
        //当范围选择成功后才回调数据，返回两次的数据
        calendar_view.setOnRangeDateSelectedListener(new CalendarPickerView.OnRangeDateSelectedListener() {
            @Override
            public void onDateSelected(List<Date> dates) {
                invoke(dates);
            }
        });
    }

    public abstract String getCompareTime();

    public abstract void invoke(List<Date> dates);
}

