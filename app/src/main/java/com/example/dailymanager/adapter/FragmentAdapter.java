package com.example.dailymanager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dailymanager.ExpenseFragment;
import com.example.dailymanager.IncomeFragment;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new IncomeFragment();
        }

        return new ExpenseFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
