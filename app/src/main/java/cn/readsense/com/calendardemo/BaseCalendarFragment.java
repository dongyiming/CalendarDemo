package cn.readsense.com.calendardemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import cn.readsense.com.calendardemo.timessquare.CalendarPickerView;

/**
 * @description: TODO
 * @author: dongyiming
 * @date: 2018/9/25 1:14
 * @version: v1.0
 */
public abstract class BaseCalendarFragment extends Fragment implements CalendarView {

    private CalendarPickerView calendar_view;
    private CalendarController calendarController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, null);
        calendar_view = (CalendarPickerView) view.findViewById(R.id.calendar_view);
        calendarController = new CalendarController(getActivity(), this);
        calendarController.initTime(getCompareTime());
        return view;
    }

    @Override
    public void initTime(@NotNull Date startDate, @NotNull Date endDate, @NotNull Date selectedDate) {

        calendar_view.init(startDate, endDate).withSelectedDate(selectedDate);
        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {

            @Override
            public void onDateSelected(Date date) {
                invoke(date);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    public abstract String getCompareTime();

    public abstract void invoke(Date date);
}

