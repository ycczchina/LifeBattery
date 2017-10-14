package com.example.zheng.lifebattery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RoundedRectProgressBar bar;
    private BatteryView bv;
    private Button btn;
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
}
