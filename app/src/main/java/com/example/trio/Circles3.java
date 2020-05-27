package com.example.trio;

import android.content.Context;

public class Circles3 {
    private int angle3; //5
    private int radius3; //150
    private int x3;
    private int y3;
    private int speed;


    public Circles3(Context context) {
        angle3 = 240;
        radius3 = 220;
        x3 = (int)(0+ Math.cos(angle3 * Math.PI / 180F) * radius3);
        y3 = (int)(0+Math.sin(angle3 * Math.PI / 180F) * radius3);
        speed = 1;
    }




    public void update() {
        x3 = (int)(0+ Math.cos(angle3 * Math.PI / 180F) * radius3);
        y3 = (int)(0+Math.sin(angle3 * Math.PI / 180F) * radius3);
        angle3 += 3;

    }
    public void negativeUpdate(){
        x3 = (int)(0 + Math.cos(angle3 * Math.PI / 180F) * radius3);
        y3 = (int)(0 + Math.sin(angle3 * Math.PI / 180F) * radius3);
        angle3 -= 3;
    }



    public int getSpeed() {
        return speed;
    }

    public int getRadius3() {
        return radius3;
    }

    public int getX3() {
        return x3;
    }


    public int getY3() {
        return y3;
    }

}

