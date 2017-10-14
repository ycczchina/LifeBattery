package com.example.zheng.lifebattery;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zheng on 10/13/17.
 */

public class Day {
        public static void main(String[] args) {
            try {
                test("2012-02-29",30);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        static void test(String d, int day) throws ParseException{
            Date date = new Date();
            long a = date.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long b = sdf.parse(d).getTime();
            int success = (int) ((b-a)/(1000*60*60*24));  //1000毫秒*60分钟*60秒*24小时 = 天
            System.out.println("距离"+d+"还有"+success+"天");
            Log.i("距离", "d");
            Log.d("天", "success");
            if(success <= day){  //如果距离那天天数等于day
                //把值存到某个地方,或者返回回去
            }
        }

}
