package com.example.dailymanager.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HabitViewModel extends AndroidViewModel {
    private HabitRepository mRepository;

    private final LiveData<List<HabitsDataEntity>> mAllWords;

    public HabitViewModel(Application application) {
        super(application);
        mRepository = new HabitRepository(application);
        mAllWords = mRepository.getAllHabits();
    }




    LiveData<List<HabitsDataEntity>> getAllWords() { return mAllWords; }

    public void addHabit(HabitsDataEntity habit) { mRepository.addHabit(habit); }

    public void updateHabit(HabitsDataEntity habit) { mRepository.updateHabit(habit); }

    public void deleteHabit(HabitsDataEntity habit) { mRepository.deleteHabit(habit); }

    public void deleteAllHabits(HabitsDataEntity habit) { mRepository.deleteAllHabit(); }


}
