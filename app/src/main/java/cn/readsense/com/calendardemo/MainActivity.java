package cn.readsense.com.calendardemo;

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
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: dongyiming
 * @date: 2018/9/25 1:08
 * @version: v1.0
 */
public class MainActivity extends AppCompatActivity implements BindActivityCallBack<String>, View.OnClickListener {

    private TabLayout tablayout_top;
    private ViewPager viewpager;
    private BaseStatePageAdapter mAdapter;
    private String startTime;
    private String endTime;
    public static Map<Integer, BaseCalendarFragment> fragments = new HashMap<>();
    private ImageView backImg;

    @Override
    public void call(String time, boolean flag) {

        if (flag) {
            startTime = time;
            tablayout_top.getTabAt(0).setText(time);
        } else {
            endTime = time;
            tablayout_top.getTabAt(1).setText(time);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //设置状态栏透明度为0，及背景色，需要依赖AndroidUtilCode框架
        BarUtils.setStatusBarColor(this, this.getResources().getColor(R.color.color_title_bg));
        //BarUtils.setStatusBarAlpha(this);
    }

    /**
     * <!初始化参数和控件>
     *
     * @param:
     * @return:
     * @author: dongyiming
     * @date: 2018/9/25 21:49
     * @version: v1.0
     */
    private void init() {

        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");

        tablayout_top = (TabLayout) findViewById(R.id.tablayout_top);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        backImg = (ImageView) findViewById(R.id.img_back);
        backImg.setOnClickListener(this);

        String[] titles = new String[]{startTime, endTime};
        mAdapter = new BaseStatePageAdapter(this.getSupportFragmentManager(), titles) {
            @Override
            public Fragment getItemFragment(int position) {
                return buildItemFragment(position);
            }
        };
        tablayout_top.setupWithViewPager(viewpager);
        tablayout_top.getTabAt(0);
        viewpager.setAdapter(mAdapter);
    }

    /**
     * <获取指定位置的fragment>
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
     * <！跳转startActivityForResult>
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
                Intent intent = new Intent();
                intent.putExtra("startTime", startTime);
                intent.putExtra("endTime", endTime);
                setResult(1, intent);
                finish();
        }

    }
}
