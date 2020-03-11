package com.example.trio;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.util.ArrayList;

class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private float x, y, r;
    private int highscore;
    private int screenX, screenY;
    private boolean flag;
    private Circles circle;
    private Circles3 circle3;
    private Circles2 circle2;
    private Canvas canvas;
    private Thread gameThread = null;
    private ArrayList<Star> stars = new ArrayList<>();

    private boolean playing;

    public Canvas getCanvas() {
        return canvas;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        highscore = 0;
        canvas = getHolder().lockCanvas();
        circle = new Circles(context);
        circle2 = new Circles2(context);
        circle3 = new Circles3(context);
        getHolder().addCallback(this);
        this.screenX = screenX;
        this.screenY = screenY;
        playing = true;


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            @Override
            public void run() {
                Paint p = new Paint();
                p.setColor(Color.WHITE);
                p.setTextSize(30);
                int starNums = 100;
                for (int i = 0; i < starNums; i++) {
                    Star s = new Star(screenX, screenY);
                    stars.add(s);
                }
                while (true) {
                    canvas = getHolder().lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    highscore ++;
                    if (canvas != null) {
                        canvas.drawCircle(canvas.getWidth() / 2 + circle.getX1(), canvas.getHeight() - dpToPx(88) + circle.getY1(), 45, p);
                        canvas.drawCircle(canvas.getWidth() / 2 + circle2.getX2(), canvas.getHeight() - dpToPx(88) + circle2.getY2(), 45, p);
                        canvas.drawCircle(canvas.getWidth() / 2 + circle3.getX3(), canvas.getHeight() - dpToPx(88) + circle3.getY3(), 45, p);
                        canvas.drawText("SCORE: "+highscore,100,50,p);
                        for (Star s : stars) {
                            s.update();
                        }

                        if (flag) {
                            if (x > canvas.getWidth() / 2) {

                                circle.update();
                                circle2.update();
                                circle3.update();
                                canvas.drawCircle(canvas.getWidth() / 2 + circle.getX1(), canvas.getHeight() - dpToPx(88) + circle.getY1(), 45, p);
                                canvas.drawCircle(canvas.getWidth() / 2 + circle2.getX2(), canvas.getHeight() - dpToPx(88) + circle2.getY2(), 45, p);
                                canvas.drawCircle(canvas.getWidth() / 2 + circle3.getX3(), canvas.getHeight() - dpToPx(88) + circle3.getY3(), 45, p);
                            } else {

                                circle.negativeUpdate();
                                circle2.negativeUpdate();
                                circle3.negativeUpdate();
                                canvas.drawCircle(canvas.getWidth() / 2 + circle.getX1(), canvas.getHeight() - dpToPx(88) + circle.getY1(), 45, p);
                                canvas.drawCircle(canvas.getWidth() / 2 + circle2.getX2(), canvas.getHeight() - dpToPx(88) + circle2.getY2(), 45, p);
                                canvas.drawCircle(canvas.getWidth() / 2 + circle3.getX3(), canvas.getHeight() - dpToPx(88) + circle3.getY3(), 45, p);

                            }

                        }
                        for (Star s : stars) {
                            p.setStrokeWidth(s.getStarWidth());
                            canvas.drawPoint(s.getX(), s.getY(), p);
                        }

                        getHolder().unlockCanvasAndPost(canvas);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        this.x = event.getX();
        this.y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                flag = true;
                break;

            case MotionEvent.ACTION_UP:
                flag = false;
                break;
        }
        return true;
    }
}