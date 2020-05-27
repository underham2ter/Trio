package com.example.trio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;

class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private float x, y, r;
    private PreferenceScore preferenceScore;
    private PreferenceLogger preferenceLogger;
    private LeaderBoardSQL leaderBoardSQL;
    private static int best;
    private Context context;
    private int rectNum;
    private boolean playing;
    private boolean gameOver;
    private int highscore;
    private Thread gameThread;
    private int screenX, screenY;
    private Paint p;
    private boolean flag;
    private Rects rects;
    private Circles circle;
    private Circles3 circle3;
    private Circles2 circle2;
    private int circleRadius;
    private Canvas canvas;
    private static MediaPlayer mediaPlayer;
    private ArrayList<Star> stars = new ArrayList<>();
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.context = context;
        preferenceScore = new PreferenceScore(context);
        preferenceLogger = new PreferenceLogger(context);
        leaderBoardSQL = new LeaderBoardSQL(context);
        highscore = 0;
        rectNum = 5;
        playing = true;
        gameOver = false;
        rects = new Rects(screenX,screenY);
        circle = new Circles(context);
        circle2 = new Circles2(context);
        circle3 = new Circles3(context);
        circleRadius = 35;
        mediaPlayer = MediaPlayer.create(context, R.raw.music);

        getHolder().addCallback(this);
        this.screenX = screenX;
        this.screenY = screenY;
        p = new Paint();
        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }
        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (getPlaying()) {
                    collision(circle,circle2,circle3,rects);
                    draw();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
    public void collision(Circles c1, Circles2 c2, Circles3 c3, Rects r){
        int side = (int)(circleRadius/Math.sqrt(2));
        Rect rectc1 = new Rect(c1.getX1()-side+screenX/2,c1.getY1()-side+screenY-dpToPx(100)-circleRadius*2,c1.getX1()+side+screenX/2,c1.getY1()+side+screenY-dpToPx(100)-circleRadius*2);
        Rect rectc2 = new Rect(c2.getX2()-side+screenX/2,c2.getY2()-side+screenY-dpToPx(100)-circleRadius*2,c2.getX2()+side+screenX/2,c2.getY2()+side+screenY-dpToPx(100)-circleRadius*2);
        Rect rectc3 = new Rect(c3.getX3()-side+screenX/2,c3.getY3()-side+screenY-dpToPx(100)-circleRadius*2,c3.getX3()+side+screenX/2,c3.getY3()+side+screenY-dpToPx(100)-circleRadius*2);
        Rect rectr1 = new Rect(r.getX1_1(),0 + r.getDeltaY1_1(),r.getX2_1(),r.getY2()+r.getDeltaY2_1());
        Rect rectr2 = new Rect(r.getX1_2(),0 + r.getDeltaY1_2(),r.getX2_2(),r.getY2()+r.getDeltaY2_2());
        if(Rect.intersects(rectc1, rectr1)||Rect.intersects(rectc2, rectr1)||Rect.intersects(rectc3, rectr1)){
            gameOver = true;
        }
        if(Rect.intersects(rectc1, rectr2)||Rect.intersects(rectc2, rectr2)||Rect.intersects(rectc3, rectr2)){
            gameOver = true;
        }
    }

    public void draw(){
        //Log.e("SP111",sharedPreferences2.getInt("score1", 0)+"");
        canvas = getHolder().lockCanvas();
        p.setColor(Color.WHITE);
        p.setTextSize(40);
        highscore++;
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            for (Star s : stars) {
                s.update();
            }

            rects.update1();
            rects.update2();

            if (flag) {
                if (x > canvas.getWidth() / 2) {
                    circle.update();
                    circle2.update();
                    circle3.update();
                } else {
                    circle.negativeUpdate();
                    circle2.negativeUpdate();
                    circle3.negativeUpdate();
                }
            }

            for (Star s : stars) {
                p.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), p);
            }

            canvas.drawRect(new Rect(rects.getX1_1(),0 + rects.getDeltaY1_1(),rects.getX2_1(),rects.getY2()+rects.getDeltaY2_1()),p);
            canvas.drawRect(new Rect(rects.getX1_2(),0 + rects.getDeltaY1_2(),rects.getX2_2(),rects.getY2()+rects.getDeltaY2_2()),p);
            canvas.drawCircle(canvas.getWidth() / 2 + circle.getX1(), canvas.getHeight() - dpToPx(100) + circle.getY1(), circleRadius, p);
            canvas.drawCircle(canvas.getWidth() / 2 + circle2.getX2(), canvas.getHeight() - dpToPx(100) + circle2.getY2(), circleRadius, p);
            canvas.drawCircle(canvas.getWidth() / 2 + circle3.getX3(), canvas.getHeight() - dpToPx(100) + circle3.getY3(), circleRadius, p);
            canvas.drawText("SCORE: " + highscore, 0, 50, p);
            if(gameOver){
                p.setTextSize(250);
                p.setStyle(Paint.Style.STROKE);
                p.setTextAlign(Paint.Align.CENTER);
                int yPos=(int) ((canvas.getHeight() / 2) - ((p.descent() + p.ascent())));
                canvas.drawText("RESTART",canvas.getWidth()/2,yPos,p);
            }

            getHolder().unlockCanvasAndPost(canvas);
            if(gameOver){
                playing = false;
                mediaPlayer.stop();
                if (highscore > preferenceScore.getValue()) {
                    preferenceScore.setValue(highscore);
                    leaderBoardSQL.updateData(preferenceLogger.getName(),highscore);
                }
            }
        }
    }
    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.start();
        mediaPlayer.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.x = event.getX();
        this.y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                flag = true;
                break;

            case MotionEvent.ACTION_UP:
                flag = false;
                break;
        }
        if(gameOver){
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }
        return true;
    }
    public void pause() {
        //Log.e("RRRRRR","pause");
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
    }


    public static int getBest() {
        return best;
    }

}
