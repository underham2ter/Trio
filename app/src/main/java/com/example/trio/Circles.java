package com.example.trio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class Circles{
    private int angle;
    private int radius;
    private int x1;
    private int y1;
    private int speed;


    public Circles(Context context) {
        radius = 220;
        angle = 0;
        x1 = (int)Math.cos(angle * Math.PI / 180F) * radius;
        y1 = (int)(0 + Math.sin(angle * Math.PI / 180F) * radius);
        speed = 1;
    }





    public void onGameStartUpdate(){
        if(y1<radius){
            y1 -= radius/30;
        }

    }
    public void update() {
        x1 = (int)(0 + Math.cos(angle * Math.PI / 180F) * radius);
        y1 = (int)(0 + Math.sin(angle * Math.PI / 180F) * radius);
        angle += 3;

    }
    public void negativeUpdate(){
        x1 = (int)(0 + Math.cos(angle * Math.PI / 180F) * radius);
        y1 = (int)(0 + Math.sin(angle * Math.PI / 180F) * radius);
        angle -= 3;
    }


    public int getX1(){
        return x1;
        }


    public int getY1() {
        return y1;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRadius() {
        return radius;
    }


}
