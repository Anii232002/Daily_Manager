package com.example.dailymanager.database;

import android.app.ActivityManager;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HabitRepository {
    private HabitDao habitdao;
    private LiveData<List<HabitsDataEntity>> mAllHabits;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    HabitRepository(Application application) {
        HabitDatabase db = HabitDatabase.getDatabase(application);
        habitdao = db.habitdao();
        mAllHabits = habitdao.getAllHabit();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<HabitsDataEntity>> getAllHabits() {
        return mAllHabits;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void addHabit(HabitsDataEntity habit) {
        HabitDatabase.databaseWriteExecutor.execute(() -> {
            habitdao.addHabit(habit);
        });
    }

    void updateHabit(HabitsDataEntity habit) {
        HabitDatabase.databaseWriteExecutor.execute(() -> {
            habitdao.updateHabit(habit);
        });
    }

    void deleteHabit(HabitsDataEntity habit) {
        HabitDatabase.databaseWriteExecutor.execute(() -> {
            habitdao.deleteHabit(habit);
        });
    }

    void deleteAllHabit() {
        HabitDatabase.databaseWriteExecutor.execute(() -> {
            habitdao.deleteAll();
        });
    }



}
