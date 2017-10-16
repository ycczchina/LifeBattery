package com.example.zheng.lifebattery;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RoundedRectProgressBar bar;
    private BatteryView bv;
    private Button btn;
    private FloatingActionButton fba1;
    private int progress;
    private Timer timer;
    private Day d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (RoundedRectProgressBar) findViewById(R.id.bar);
        bv = (BatteryView) findViewById(R.id.bv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    reset();
            }
        });
        fba1 = (FloatingActionButton) findViewById(R.id.fab1);
        fba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPick((FloatingActionButton) v);
            }

        });





    }

    /**
     * 进度条从头到尾跑一次
     */
    private void reset() {
        progress = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bar.setProgress(progress);
                bv.setProgress(progress);
                progress ++;
                if (progress > 100) {
                    timer.cancel();
                }
            }
        }, 0, 30);
    }


    private void showDialogPick(final FloatingActionButton fba) {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                //选择完日期后弹出选择时间对话框
                Toast.makeText(MainActivity.this,"Your birthday is "+time,Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();
    }


}
