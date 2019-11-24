package project.task.saurabh.imagepro.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.fragments.Horizontal;
import project.task.saurabh.imagepro.fragments.Picker;
import project.task.saurabh.imagepro.fragments.Vertical;


public class FragPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.picker, R.string.horizontal, R.string.vertical};
    private static final Fragment[] FRAGMENTS = new Fragment[]{new Picker(), new Horizontal(), new Vertical()};

    private final Context mContext;

    public FragPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FRAGMENTS[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}