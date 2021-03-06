package cn.readsense.com.calendardemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.readsense.com.calendardemo.R;
import cn.readsense.com.calendardemo.adapter.BaseStatePageAdapter;
import cn.readsense.com.calendardemo.fragment.BaseCalendarFragment;
import cn.readsense.com.calendardemo.fragment.EndFragment;
import cn.readsense.com.calendardemo.fragment.StartFragment;
import cn.readsense.com.calendardemo.service.BindActivityCallBack;
import cn.readsense.com.calendardemo.utils.BarUtils;

/**
 * @description: TODO
 * @author: dongyiming
 * @date: 2018/9/25 1:08
 * @version: v1.0
 */
public class MainActivity extends AppCompatActivity implements BindActivityCallBack<Date>, View.OnClickListener {

    private String endTime;
    private String startTime;
    private ViewPager viewpager;
    @SuppressLint("UseSparseArrays")
    public static Map<Integer, BaseCalendarFragment> fragments = new HashMap<>();
    private TextView[] tabView = new TextView[2];
    private TabLayout tablayout_top;

    @Override
    public void call(List<Date> list, int ref) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String time = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA).format(list.get(i));
            if (i > 0) {
                stringBuilder.append(" - ").append(time);
            } else {
                stringBuilder.append(time);
            }
        }
        TextView view = tabView[ref];
        if (ref == 0) {
            startTime = stringBuilder.toString();
            if (tabView != null)
                view.setText(startTime);
            viewpager.setCurrentItem(1, false);
        } else {
            endTime = stringBuilder.toString();
            if (tabView != null)
                view.setText(endTime);
            setResult();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //设置状态栏透明度为0，及背景色，需要依赖AndroidUtilCode框架
        BarUtils.setStatusBarColor(this, this.getResources().getColor(R.color.color_title_bg));
    }

    /**
     * <!初始化参数和控件>
     *
     * @author: dongyiming
     * @date: 2018/9/25 21:49
     * @version: v1.0
     */
    private void init() {

        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");

        tablayout_top = findViewById(R.id.tablayout_top);
        viewpager = findViewById(R.id.viewpager);
        findViewById(R.id.img_back).setOnClickListener(this);

        String[] titles = new String[]{startTime, endTime};
        BaseStatePageAdapter mAdapter = new BaseStatePageAdapter(this.getSupportFragmentManager(), titles) {
            @Override
            public Fragment getItemFragment(int position) {
                return buildItemFragment(position);
            }
        };
        tablayout_top.setupWithViewPager(viewpager);
        tablayout_top.getTabAt(0);
        viewpager.setAdapter(mAdapter);
        //设置自定义View
        for (int i = 0; i < mAdapter.getCount(); i++) {
            TabLayout.Tab itemView = tablayout_top.getTabAt(i);
            if (itemView != null) {
                itemView.setCustomView(R.layout.item_tablayout);
                if (itemView.getCustomView() != null) {
                    View timeValue = itemView.getCustomView().findViewById(R.id.txt_value);
                    tabView[i] = (TextView) timeValue;
                    if (i == 0) {
                        tabView[0].setText(startTime);
                    } else {
                        tabView[1].setText(endTime);
                    }
                }
            }
        }
    }

    /**
     * <! 获取指定位置的fragment>
     *
     * @param: position
     * @return: BaseCalendarFragment
     * @author: dongyiming
     * @date: 2018/9/25 1:39
     * @version: v1.0
     */
    public Fragment buildItemFragment(int position) {

        BaseCalendarFragment mFragment = fragments.get(position);
        if (mFragment == null) {
            switch (position) {
                case 0:
                    mFragment = new StartFragment();
                    break;
                case 1:
                    mFragment = new EndFragment();
                    break;
            }
            fragments.put(position, mFragment);
        }
        return mFragment;
    }

    /**
     * <! 跳转startActivityForResult>
     *
     * @param:
     * @return:
     * @author: dongyiming
     * @date: 2018/9/24 21:46
     * @version: v1.0
     */
    public static void startActivity(Context mContext, String startTime, String endTime) {

        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("startTime", startTime);
        intent.putExtra("endTime", endTime);
        ((Activity) mContext).startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_back:
                setResult();
        }
    }

    /**
     * <! 返回到Activity>
     *
     * @author: dongyiming
     * @date: 2018/9/29 12:45
     * @version: v1.0
     */
    public void setResult() {

        Intent intent = new Intent();
        intent.putExtra("startTime", startTime);
        intent.putExtra("endTime", endTime);
        setResult(1, intent);
        finish();
    }
}
