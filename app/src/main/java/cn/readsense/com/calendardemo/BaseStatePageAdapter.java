package cn.readsense.com.calendardemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @version : 1.0
 * @Description : 针对FragmentStatePagerAdapter
 * @autho : dongyiming
 * @data : 2017/8/9 19:39
 */
public abstract class BaseStatePageAdapter extends FragmentStatePagerAdapter {


    private String[] titles;

    public BaseStatePageAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return getItemFragment(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

    public abstract Fragment getItemFragment(int position);
}
