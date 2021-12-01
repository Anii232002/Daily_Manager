package com.example.dailymanager.Habits;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailymanager.Expenses;
import com.example.dailymanager.R;
import com.example.dailymanager.database.HabitViewModel;
import com.example.dailymanager.database.HabitsDataEntity;

import java.util.Calendar;


public class HabitsNew extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private String title = "";
    private String description = "";
    private String timestamp = "";
    private int drawableselected = 0;

    private int day = 0;
    private int month = 0;
    private int year = 0;
    private int hour = 0;
    private int minute = 0;

    private String cleanDate = "";
    private String cleanTime = "";

    private EditText et_habitTitle ;
    private EditText et_habitDescription ;

    private Button btn_pickTime ;
    private Button btn_pickDate;
    private TextView tv_timeSelected ;
    private TextView tv_dateSelected ;
    private ImageView ic_foodselected ;
    private ImageView ic_walkselcted ;
    private ImageView ic_smokeselected ;
    private Button btn_confirm ;

    private HabitViewModel habitViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_habit_new);
        et_habitTitle = findViewById(R.id.et_habitTitle);
        btn_confirm = findViewById(R.id.btn_confirm);
        ic_foodselected = findViewById(R.id.iv_fastFoodSelected);
        ic_smokeselected = findViewById(R.id.iv_smokingSelected);
        btn_pickTime = findViewById(R.id.btn_pickTime);
        ic_walkselcted = findViewById(R.id.iv_walkSelected);
        btn_pickDate = findViewById(R.id.btn_pickDate);
        tv_dateSelected = findViewById(R.id.tv_dateSelected);
        tv_timeSelected = findViewById(R.id.tv_timeSelected);
        et_habitDescription = findViewById(R.id.et_habitDescription);
        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
       btn_confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addHabitToDB();
           }
       });
       pickDateAndTime();
       drawableSelected();



    }



    private void pickDateAndTime() {


         btn_pickDate.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onClick(View view) {
                 getDateCalender();

                  new DatePickerDialog(HabitsNew.this,(DatePickerDialog.OnDateSetListener)HabitsNew.this,year,month,day).show();
             }
         });




        btn_pickTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                getTimeCalender();

                 new TimePickerDialog(HabitsNew.this,(TimePickerDialog.OnTimeSetListener)HabitsNew.this,hour,minute,true).show();

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        tv_dateSelected.setText("Date:"+cleanDate);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
   //cleanTime = Calc.INSTANCE.cleanTime(hour,minute);
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

               ic_smokeselected.setSelected(false) ;
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

    private void addHabitToDB() {

        //Get text from editTexts
        title = et_habitTitle.toString();
        description = et_habitDescription.toString();

        //Create a timestamp string for our recyclerview
        timestamp = cleanDate+cleanTime;

        //Check that the form is complete before submitting data to the database
        if (!(title.isEmpty() || description.isEmpty() || timestamp.isEmpty() || drawableselected == 0)) {
           HabitsDataEntity habit = new HabitsDataEntity(title, description, timestamp, drawableselected);

            //add the habit if all the fields are filled
            habitViewModel.addHabit(habit);
            Toast.makeText(HabitsNew.this, "Habit created successfully!", Toast.LENGTH_SHORT).show();

            //navigate back to our home fragment
            Intent intent = new Intent(this, HabitsList.class);
            startActivity(intent);
        } else {
            Toast.makeText(HabitsNew.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
    }



}