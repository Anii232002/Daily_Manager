package com.example.dailymanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailymanager.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class FieldSelection extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter adapter;


    public FieldSelection() {
        // Required empty public constructor
            super(R.layout.fragment_field_selection);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager2=view.findViewById(R.id.view_pager);

        FragmentManager fm= getActivity().getSupportFragmentManager();
       adapter=new FragmentAdapter(fm,getLifecycle());
       viewPager2.setAdapter(adapter);

       tabLayout.addTab(tabLayout.newTab().setText("Expenses"));
       tabLayout.addTab(tabLayout.newTab().setText("Income"));

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               viewPager2.setCurrentItem(tab.getPosition());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position) {
               tabLayout.selectTab(tabLayout.getTabAt(position));
           }
       });


        

    }
}