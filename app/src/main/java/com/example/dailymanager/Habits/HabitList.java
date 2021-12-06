package com.example.dailymanager.Habits;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dailymanager.R;
import com.example.dailymanager.adapter.HabitListAdapter;
import com.example.dailymanager.adapter.OnItemClickListener;
import com.example.dailymanager.database.ExpensesViewModel;
import com.example.dailymanager.database.HabitViewModel;
import com.example.dailymanager.database.HabitsDataEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public class HabitList extends Fragment {

    private List<HabitsDataEntity> habitList;
    private HabitViewModel habitViewModel;

    private HabitListAdapter adapter;
    private Context mContext;
    public View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
         view=inflater.inflate(R.layout.fragment_habit_list, container, false);



        this.adapter = new HabitListAdapter(this::OnItemClicked);
        RecyclerView var10000 = (RecyclerView)view.findViewById(R.id.rv_habits);

        HabitListAdapter var10001 = this.adapter;


        var10000.setAdapter((RecyclerView.Adapter)var10001);
        var10000 = (RecyclerView)view.findViewById(R.id.rv_habits);

        var10000.setLayoutManager((RecyclerView.LayoutManager)(new LinearLayoutManager(mContext)));
        this.viewModels();


        ((FloatingActionButton)view.findViewById(R.id.fab_add)).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Navigation.findNavController(view).navigate(R.id.action_habitList_to_habitsNew);
            }
        }));


        ((SwipeRefreshLayout)view.findViewById(R.id.swipeToRefresh)).setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener)(new SwipeRefreshLayout.OnRefreshListener() {
            public final void onRefresh() {
                HabitList.access$getAdapter$p(HabitList.this).setData(HabitList.access$getHabitList$p(HabitList.this));
                SwipeRefreshLayout var10000 = (SwipeRefreshLayout)view.findViewById(R.id.swipeToRefresh);

                var10000.setRefreshing(false);
            }
        }));

        return  view;



    }

    public HabitList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    private final void viewModels() {
        ViewModel var10001 = (new ViewModelProvider((ViewModelStoreOwner)this)).get(HabitViewModel.class);

        this.habitViewModel = (HabitViewModel)var10001;
        HabitViewModel var10000 = this.habitViewModel;


        var10000.getAllHabits.observe((LifecycleOwner)this , (Observer)(new Observer() {
            // $FF: synthetic method
            // $FF: bridge method

            public void onChanged(Object var1) {
                this.onChanged((List)var1);
            }

            public final void onChanged(List it) {
                HabitListAdapter var10000 = HabitList.access$getAdapter$p(HabitList.this);
                Intrinsics.checkNotNullExpressionValue(it, "it");
                var10000.setData(it);
                HabitList.this.habitList = it;
                RecyclerView var2;
                TextView var3;
                if (it.isEmpty()) {
                    var2 = (RecyclerView)view.findViewById(R.id.rv_habits);
                    Intrinsics.checkNotNullExpressionValue(var2, "rv_habits");
                    var2.setVisibility(View.VISIBLE);
                    var3 = (TextView)view.findViewById(R.id.tv_emptyView);
                    Intrinsics.checkNotNullExpressionValue(var3, "tv_emptyView");
                    var3.setVisibility(View.INVISIBLE);
                } else {
                    var2 = (RecyclerView)view.findViewById(R.id.rv_habits);
                    Intrinsics.checkNotNullExpressionValue(var2, "rv_habits");
                    var2.setVisibility(View.VISIBLE);
                    var3 = (TextView)view.findViewById(R.id.tv_emptyView);
                    Intrinsics.checkNotNullExpressionValue(var3, "tv_emptyView");
                    var3.setVisibility(View.INVISIBLE);
                }

            }
        }));
    }



    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {

        inflater.inflate(R.menu.nav_habit, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        switch(item.getItemId()) {
            case R.id.nav_delete:



                habitViewModel.deleteAllHabits();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    // $FF: synthetic method
    public static final HabitListAdapter access$getAdapter$p(HabitList $this) {
        HabitListAdapter var10000 = $this.adapter;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setAdapter$p(HabitList $this, HabitListAdapter var1) {
        $this.adapter = var1;
    }

    // $FF: synthetic method
    public static final List access$getHabitList$p(HabitList $this) {
        List var10000 = $this.habitList;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("habitList");
        }

        return var10000;
    }

    public void OnItemClicked(HabitsDataEntity habit)
    {
        habitViewModel.deleteHabit(habit);
        adapter.notifyDataSetChanged();
    }




}