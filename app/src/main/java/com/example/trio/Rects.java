package com.example.trio;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class Rects{
    private Random generator;
    private int speed1;
    private int speed2;
    private int rectSize;
    private int deltaY1_1,deltaY1_2;
    private int deltaY2_1,deltaY2_2;
    private int Y1;
    private int Y2;
    private int X1_1,X1_2;
    private int X2_1,X2_2;
    private int maxY;
    private int maxX;
    private boolean gateKeeper2;
    public Rects(int screenX, int screenY){
        generator = new Random();
        speed1 = 15;
        speed2 = 15;
        rectSize = 100;
        deltaY1_1 = 0;
        deltaY1_2 = 0;
        deltaY2_1 = 0;
        deltaY2_2 = 0;
        X1_1 = generator.nextInt();
        X2_1 = X1_1 + rectSize;
        X1_2 = generator.nextInt();
        X2_2 = X1_2 + rectSize;
        Y1 = 0;
        Y2 = Y1 + rectSize;
        maxX = screenX;
        maxY = screenY;
        gateKeeper2 = false;
    }

    public int getRectSize() {
        return rectSize;
    }

    public void update1(){
        deltaY1_1 += speed1;
        deltaY2_1 += speed1;
        if (deltaY1_1 > maxY) {

            deltaY1_1 = 0;
            deltaY2_1 = 0;
            X1_1 = generator.nextInt(maxX-rectSize-400)+200;
            X2_1 = X1_1 + rectSize;
        }
        speed1 += 0.5;
    }
    public void update2() {
        if (deltaY1_1 > maxY / 2) {
            gateKeeper2 = true;
        }
        if(gateKeeper2) {
            deltaY1_2 += speed2;
            deltaY2_2 += speed2;

            if (deltaY1_2 > maxY) {

                deltaY1_2 = 0;
                deltaY2_2 = 0;
                X1_2 = generator.nextInt(maxX-rectSize-400)+200;
                X2_2 = X1_2 + rectSize;
            }
            speed2 += 0.5;
        }
    }

    public int getX1_1() {
        return X1_1;
    }
    public int getX2_1() {
        return X2_1;
    }

    public int getX1_2() {
        return X1_2;
    }

    public int getX2_2() {
        return X2_2;
    }

    public int getY2() {
        return Y2;
    }

    public int getDeltaY1_1() {
        return deltaY1_1;
    }

    public int getDeltaY2_1() {
        return deltaY2_1;
    }
    public int getDeltaY1_2() {
        return deltaY1_2;
    }

    public int getDeltaY2_2() {
        return deltaY2_2;
    }
}
