package com.example.itai.sensordemo2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import java.util.Random;

public class Main4Activity extends Activity implements SensorEventListener {

    static final float COSTUM_GRAVITY = (float) 5.32452;
    AlertDialog.Builder builder;
    int fails;
    private int egg_status;
    private int x;
    private int y;
    //***********************************8
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 20000L;
    long timeSwapBuff = 20000L;// before it was 0
    long updatedTime = 20000L;
    int secs=20;
    // ******************************
    SharedPreferences sp;
    //********************************************************
    //private int total_score=0;
    private int score_new=0;
    private Bitmap coinBitmap;
    static Coin[] coins_arr = new Coin[7];
    int[] arr_c = new int[2];
    private int viewWidth=1000;
    private int viewHeight=1800;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private AnimatedView mAnimatedView = null;
    private String timerTextView;
    Intent intent;
    static Fendss[] fends_arr = new Fendss[7];
    private Bitmap brokenEggBitmap;
    private Bitmap eggBitmap ;
    private FinishPoint endP;
    private Coin maker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fends_arr[0] = new Fendss(280,400,250,690);
        fends_arr[1] = new Fendss(480,850,470,630);
        fends_arr[2] = new Fendss(70,470,770,960);
        fends_arr[3] = new Fendss(620,770,500,1040);
        fends_arr[4] = new Fendss(420,810,1360,1500);
        fends_arr[5] = new Fendss(370,470,1050,1400);
        fends_arr[6] = new Fendss(-80,300,1040,1200);
        //********************88888
        for(int i=0; i<7; i++){
            coins_arr[i]=new Coin(0,0,true);
        }
        //*****************************8
        endP = new FinishPoint(375,500,1600,1835);
        //*****************************8
        maker = new Coin(1,1,false);
        makeCoins();
        //**********************8
        sp = getSharedPreferences("details",MODE_PRIVATE);
        //**********************8
        int temp;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                temp= 3;
            } else {
                temp= extras.getInt("fails");
            }
        } else {
            temp= (int) savedInstanceState.getSerializable("fails");
        }
        fails=temp;
        //*****************************************8
        final Handler handler = new Handler();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 100000);
            }
        };
        handler.removeCallbacks(task);
        handler.post(task);
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        //**********************************
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //*************************************
        mAnimatedView = new AnimatedView(this);
        //*************************************
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mAnimatedView.setBackground(getResources().getDrawable(R.drawable.l4));
            Bitmap egg = BitmapFactory.decodeResource(getResources(), R.drawable.egg2);
            Bitmap broken_egg = BitmapFactory.decodeResource(getResources(), R.drawable.begg);
            final int egg_Width = 100;
            final int egg_Height = 125;
            eggBitmap = Bitmap.createScaledBitmap(egg, egg_Width, egg_Height, true);
            brokenEggBitmap = Bitmap.createScaledBitmap(broken_egg, egg_Width, egg_Height, true);
            Bitmap coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
            int coin_width= 80;
            int coin_height = 80;
            coinBitmap = Bitmap.createScaledBitmap(coin , coin_width, coin_height, true);
        }
        //****************************************************
        if (fails < 1) {
            intent = new Intent(Main4Activity.this, MapActivity.class);
            startActivity(intent);
        } else {
            setContentView(mAnimatedView);
        }
    }

    Runnable updateTimerThread = new Runnable() {

        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;//before it -
            updatedTime = timeSwapBuff - timeInMilliseconds;//before it was +
            secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);

            if (secs < 0) {
                intent = new Intent(Main4Activity.this, MapActivity.class);
                startActivity(intent);
            } else {
                timerTextView = String.format("" + mins + ":"
                        + String.format("%02d", secs));
                customHandler.postDelayed(this, 0);
            }
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        builder = null;
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }

    private void makeCoins() {
        for(int i=0; i<7; i++){
            arr_c = maker.randomCoinsX(fends_arr,endP,7,viewWidth,viewHeight);
            coins_arr[i]=new Coin(arr_c[0], arr_c[1], true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        timeSwapBuff -= timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float[] x = {(int) event.getX()};
        final float[] y = {(int) event.getY()};
//canvas.drawCircle(250,125,45,mPaint); pause button
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (x[0] >= 210 && x[0] <= 250 && y[0] >= 120 && y[0] <= 200) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(300);
                    if (builder == null) {//work
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Main4Activity.this);
                        onPause();
                        final View dialogview = getLayoutInflater().inflate(R.layout.pausedialog, null);
                        builder.setView(dialogview).setCancelable(false);
                        final AlertDialog dialog = builder.create();
                        dialog.show();
                        Window window = dialog.getWindow();
                        window.setLayout(900, 600);
                        ImageButton noBtn = dialogview.findViewById(R.id.no);
                        noBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                intent = new Intent(Main4Activity.this, MapActivity.class);
                                startActivity(intent);
                            }
                        });
                        ImageButton yesBtn = dialogview.findViewById(R.id.yes);
                        yesBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                onResume();
                            }
                        });
                    }
                }
                return true;
        }
        return false;
    }



    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) { }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mAnimatedView.onSensorEvent(event);
        }
    }

    public class AnimatedView extends View {
        private static final int CIRCLE_RADIUS = 25; //pixels
        private Canvas canvas1;
        private Paint mPaint;
        private int viewWidth;
        private int viewHeight;
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        public AnimatedView(Main4Activity context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextAlign(Paint.Align.LEFT);
            mPaint.setStrokeWidth(10);
            mPaint.setTextSize(60);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            viewWidth = w;
            viewHeight = h;
        }
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onfail(SensorEvent event) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            egg_status = 1;
            onPause();
            if (fails <= 1) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main4Activity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.nohaerts, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);

                ImageButton returnBtn = dialogview.findViewById(R.id.btn_gobacke);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(Main4Activity.this, MapActivity.class);
                        intent.putExtra("failsEnded", 1);
                        startActivity(intent);
                    }
                });
            } else if (fails > 1 && fails <= 2) {
                --fails;
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main4Activity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.faildialog, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);
                ImageButton returnBtn = dialogview.findViewById(R.id.tryagain);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        score_new = 0;
                        egg_status = 0;
                        for (int i = 0; i < 7; i++) {
                            coins_arr[i].setViseblel(true);
                        }
                        x = 50;
                        y = 50;
                        startTime = 0;
                        timeInMilliseconds = 20000L;
                        timeSwapBuff = 20000L;
                        long updatedTime = 20000L;
                        onResume();
                    }
                });
                ImageButton btn_exit = dialogview.findViewById(R.id.exit);
                btn_exit.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        intent = new Intent(Main4Activity.this, MapActivity.class);
                        startActivity(intent);
                    }

                });
                builder.setCancelable(false);
            } else if(fails >2) {
                --fails;
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main4Activity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.activity_two_hearts, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);
                ImageButton returnBtn = dialogview.findViewById(R.id.tryagain);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        score_new = 0;
                        egg_status = 0;
                        for (int i = 0; i < 7; i++) {
                            coins_arr[i].setViseblel(true);
                        }
                        x = 50;
                        y = 50;
                        startTime = 0;
                        timeInMilliseconds = 20000L;
                        timeSwapBuff = 20000L;
                        long updatedTime = 20000L;
                        onResume();
                    }
                });
                ImageButton btn_exit = dialogview.findViewById(R.id.exit);
                btn_exit.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        intent = new Intent(Main4Activity.this, MapActivity.class);
                        startActivity(intent);
                    }

                });
                builder.setCancelable(false);
            }
        }        public void onSucces() {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(300);
            intent = new Intent(Main4Activity.this, MapActivity.class);
            if (score_new >= 200) {
                intent.putExtra("score", 5);
                //intent.putExtra("total_score", score_new);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("score_total", score_new);
                editor.commit();
            }

            startActivity(intent);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onSensorEvent(SensorEvent event) {
            x = x - (int) event.values[0];
            y = y + (int) event.values[1];
            //***********************************************************************
            for (int i = 0; i < 7; i++) {
                if (coins_arr[i].isViseblel()) {
                    if (x >= coins_arr[i].getX_pos()-80  && x <= coins_arr[i].getX_pos()+80  && y >= coins_arr[i].getY_pos()-80  && y <= coins_arr[i].getY_pos() +80) {
                        coins_arr[i].setViseblel(false);
                        score_new += 50;
                    }
                }
            }
            //****************************************************************************
            for (int i = 0; i < 7; i++) {
                if (x >= fends_arr[i].getStartX() && x <= fends_arr[i].getEndX() && y >= fends_arr[i].getStarty() && y <= fends_arr[i].getEndy()) {
                    onfail(event);

                }
            }
            if (x >= endP.getStartX() && x <= endP.getEndX() && y >= endP.getStartY() && y <= endP.getEndY()) {
                //egg_status = 1;
                onSucces();
            }
            else {
                if (x <= 0) {
                    x = 0;
                }
                if (x >= viewWidth - 100) {
                    x = viewWidth - 100;// - CIRCLE_RADIUS;
                }
                if (y <= 200) {
                    y = 200;
                }
                if (y >= viewHeight - 125) {
                    y = viewHeight - 125;
                }
            }
        }
        //****************************************************************************

        @Override
        protected void onDraw(Canvas canvas) {

            if (egg_status==1){
                canvas.drawBitmap(brokenEggBitmap,x,y,null);
            }
            else
                canvas.drawBitmap(eggBitmap, x,y, null);

            for(int i=0; i<7;i++){
                if(coins_arr[i].isViseblel()==true){
                    canvas.drawBitmap(coinBitmap, coins_arr[i].getX_pos(), coins_arr[i].getY_pos(), mPaint);
                }
            }
            String s = Integer.toString(fails);
            canvas.drawText(s, 85, 155, mPaint);
            canvas.drawText(timerTextView, 885, 145, mPaint);
            canvas.drawText(String.valueOf(score_new),470,145,mPaint);
            invalidate();
        }
    }
}

