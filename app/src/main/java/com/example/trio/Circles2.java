package com.example.trio;

import android.content.Context;

public class Circles2 {
    private int angle2; //5
    private int radius2; //150
    private int x2;
    private int y2;
    private int speed;


    public Circles2(Context context) {
        x2 = (int)(0+ Math.cos(angle2 * Math.PI / 180F) * radius2);
        y2 = (int)(0+Math.sin(angle2 * Math.PI / 180F) * radius2);
        speed = 1;
        angle2 = 120;
        radius2 = 220;


    }




    public void update() {
        x2 = (int)(0+ Math.cos(angle2 * Math.PI / 180F) * radius2);
        y2 = (int)(0+Math.sin(angle2 * Math.PI / 180F) * radius2);
        angle2 += 3;

    }
    public void negativeUpdate(){
        x2 = (int)(0 + Math.cos(angle2 * Math.PI / 180F) * radius2);
        y2 = (int)(0 + Math.sin(angle2 * Math.PI / 180F) * radius2);
        angle2 -= 3;
    }



    public int getSpeed() {
        return speed;
    }

    public int getRadius2() {
        return radius2;
    }

    public int getX2() {
        return x2;
    }


    public int getY2() {
        return y2;
    }

}
