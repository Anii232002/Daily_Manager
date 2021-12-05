package com.example.dailymanager.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HabitViewModel extends AndroidViewModel {
    private HabitRepository mRepository;

    public final LiveData<List<HabitsDataEntity>> getAllHabits;

    public HabitViewModel(Application application) {
        super(application);
        mRepository = new HabitRepository(application);
        getAllHabits = mRepository.getAllHabits();
    }




    public LiveData<List<HabitsDataEntity>> getAllHabits() { return getAllHabits; }

    public void addHabit(HabitsDataEntity habit) { mRepository.addHabit(habit); }

    public void updateHabit(HabitsDataEntity habit) { mRepository.updateHabit(habit); }

    public void deleteHabit(HabitsDataEntity habit) { mRepository.deleteHabit(habit); }

    public void deleteAllHabits() { mRepository.deleteAllHabit(); }



}
