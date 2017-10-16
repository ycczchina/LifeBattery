package com.example.zheng.lifebattery;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private BatteryView bv;
    private FloatingActionButton fba1;
    private int progress;
    private Timer timer;
    private int year_birth, month_birth, day_birth;
    private int year_cur, month_cur, day_cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bv = (BatteryView) findViewById(R.id.bv);

        fba1 = (FloatingActionButton) findViewById(R.id.fab1);
        fba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPick((FloatingActionButton) v);
            }

        });

    }

    //Charging Animation
    private void reset(final int percent) {
        progress = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bv.setProgress(progress);
                progress ++;
                if (progress > percent) {
                    timer.cancel();
                }
            }
        }, 0, 30);
    }


    private void showDialogPick(final FloatingActionButton fba1) {
        final StringBuffer time = new StringBuffer();
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        year_cur = year;
        month_cur = month + 1;
        day_cur = day;

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                year_birth = year;
                month_birth = monthOfYear + 1;
                day_birth = dayOfMonth;
                Toast.makeText(MainActivity.this,"Your birthday is "+time,Toast.LENGTH_SHORT).show();

                int percent = 100 - ((((day_cur - day_birth) / 30) + month_cur - month_birth) / 12 + year_cur - year_birth) * 100 / 70;

                reset(percent);
            }
        }, year, month, day);

        datePickerDialog.show();

    }




}
