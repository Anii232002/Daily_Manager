package com.example.dailymanager.Habits;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dailymanager.R;
import com.example.dailymanager.database.HabitViewModel;
import com.example.dailymanager.database.HabitsDataEntity;

import java.util.Calendar;

public class HabitsUpdate extends Fragment {
    private String title ;
    private String description ;
    private String timestamp ;
    private int drawableselected = 0;

/*<argument
    android:name="selectedHabit"
    app:argType="com.example.dailymanager.database.HabitsDataEntity" />*/

    private int day = 0;
    private int month = 0;
    private int year = 0;
    private int hour = 0;
    private int minute = 0;

    private String cleanDate = "";
    private String cleanTime = "";



    private AppCompatEditText et_habitTitle ;
    private AppCompatEditText et_habitDescription ;

  //  HabitsUpdateArgs navArgs ;



    private Button btn_pickTime ;
    private Button btn_pickDate;
    private TextView tv_timeSelected ;
    private TextView tv_dateSelected ;
    private ImageView ic_foodselected ;
    private ImageView ic_walkselcted ;
    private ImageView ic_smokeselected ;
    private Button btn_confirm ;
    private Context mContext;
    private HabitViewModel habitViewModel;

    View view;

    @RequiresApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_habits_update, container, false);


            et_habitTitle = (AppCompatEditText)view.findViewById(R.id.et_habitTitle_update);
            btn_confirm = view.findViewById(R.id.btn_confirm_update);
            ic_foodselected = view.findViewById(R.id.iv_fastFoodSelected_update);
            ic_smokeselected = view.findViewById(R.id.iv_smokingSelected_update);
            btn_pickTime = view.findViewById(R.id.btn_pickTime_update);
            ic_walkselcted = view.findViewById(R.id.iv_walkSelected);
            btn_pickDate = view.findViewById(R.id.btn_pickDate_update);
            tv_dateSelected = view.findViewById(R.id.tv_dateSelected_update);
            tv_timeSelected = view.findViewById(R.id.tv_timeSelected_update);
            et_habitDescription = (AppCompatEditText)view.findViewById(R.id.et_habitDescription_update);
            habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
       // et_habitTitle.setText(navArgs.getSelectedHabit().getH_title());
       // et_habitDescription.setText(navArgs.getSelectedHabit().getH_description());

            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    addHabitToDB();
                }
            });
            pickDateAndTime();
            drawableSelected();

return view;

        }


    public HabitsUpdate() {
        // Required empty public constructor
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        habitViewModel = new ViewModelProvider((ViewModelStoreOwner)this).get(HabitViewModel.class);

    }


        private void pickDateAndTime() {


            btn_pickDate.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    getDateCalender();

                    new DatePickerDialog(mContext,(DatePickerDialog.OnDateSetListener)HabitsUpdate.this,year,month,day).show();
                }
            });




            btn_pickTime.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    getTimeCalender();

                    new TimePickerDialog(mContext,(TimePickerDialog.OnTimeSetListener)HabitsUpdate.this,hour,minute,true).show();

                }
            });
        }


        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            tv_dateSelected.setText("Date:"+cleanDate);
        }

        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            cleanTime = Calc.INSTANCE.cleanTime(hour,minute);
            tv_timeSelected.setText("Time:"+cleanTime);
        }

        public void drawableSelected()
        {
            ic_foodselected.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ic_foodselected.setSelected(!ic_foodselected.isSelected());
                    drawableselected = R.drawable.ic_food_selected;

                    ic_smokeselected.setSelected(false) ;
                    ic_walkselcted.setSelected(false) ;


                }
            });

            ic_walkselcted.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ic_walkselcted.setSelected(!ic_walkselcted.isSelected());
                    drawableselected = R.drawable.ic_walk_selected;

                    ic_smokeselected.setSelected(false);  ;
                    ic_foodselected.setSelected(false);


                }
            });

            ic_smokeselected.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ic_smokeselected.setSelected(!ic_smokeselected.isSelected());
                    drawableselected = R.drawable.ic_smoke_selected;

                    ic_foodselected.setSelected(false);
                    ic_walkselcted.setSelected(false);


                }
            });
        }

        void getTimeCalender()
        {
            Calendar cal = Calendar.getInstance() ;
            hour =  cal.get(Calendar.HOUR_OF_DAY);
            minute =   cal.get(Calendar.MINUTE);


        }

        void getDateCalender()
        {
            Calendar cal = Calendar.getInstance() ;
            month =  cal.get(Calendar.MONTH);
            day =   cal.get(Calendar.DAY_OF_MONTH);
            year =   cal.get(Calendar.YEAR);

        }

    private void updateHabit(HabitsDataEntity habit) {
        habitViewModel.deleteHabit(habit);
        Toast.makeText(mContext, "Habit successfully deleted!", Toast.LENGTH_SHORT).show();

        Navigation.findNavController(view).navigate(R.id.action_habitList_to_habitsNew);
    }

        private void addHabitToDB() {

            //Get text from editTexts
            title = et_habitTitle.getText().toString();
            description = String.valueOf(et_habitDescription.getText());

            //Create a timestamp string for our recyclerview
            timestamp = cleanDate+cleanTime;

            //Check that the form is complete before submitting data to the database
            if (!(title.isEmpty() || description.isEmpty() || timestamp.isEmpty() || drawableselected == 0)) {
                HabitsDataEntity habit = new HabitsDataEntity();
              //  habit.setId(navArgs.getSelectedHabit().getId());
                habit.setH_description(description);
                habit.setH_timestamp(timestamp);
                habit.setH_title(title);
                habit.setImageid(drawableselected);


                //add the habit if all the fields are filled
                habitViewModel.updateHabit(habit);
                Toast.makeText(mContext, "Habit Updated successfully!", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigate(R.id.action_habitsUpdate_to_habitList);
            } else {
                Toast.makeText(mContext, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        }

    }

