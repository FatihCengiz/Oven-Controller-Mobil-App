package com.jaydeep.blesample.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jaydeep.blesample.operation.MainFavPage;
import com.jaydeep.blesample.operation.MainSettingsPage;
import com.jaydeep.blesample.operation.MainTempPage;
import com.jaydeep.blesample.operation.MainTimerPage;

import java.util.List;

public class SlidePageAdapter  extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public SlidePageAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
