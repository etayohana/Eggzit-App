package com.example.itai.sensordemo2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class HighScore extends Activity {

    KonfettiView K1;


    ArrayList<User_record> recs = new ArrayList<>();

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Bundle bundle = getIntent().getExtras();
     //   String temp_name = bundle.getString("Name");

        ImageView image= (ImageView) findViewById(R.id.coin1);
        ImageView image2= (ImageView) findViewById(R.id.coin2);
        ImageView image3= (ImageView) findViewById(R.id.coin3);
        ImageView image4= (ImageView) findViewById(R.id.coin4);

        final SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);

        image.animate().rotationY(20000).setDuration(55000).start();
        image2.animate().rotationY(20000).setDuration(55000).setStartDelay(700);
        image3.animate().rotationY(15000).setDuration(55000).setStartDelay(1000);
        image4.animate().rotationY(15000).setDuration(55000).setStartDelay(500);

        K1=findViewById(R.id.viewconfettii1);

        K1.build()
                .addColors(Color.YELLOW,Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(8000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, -50f, -50f, -50f)
                .stream(200, 5000L);

        ImageButton back = findViewById(R.id.b);
        ImageButton rest = findViewById(R.id.rest);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScore.this, Menu2Activity.class);
                startActivity(intent);
            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("first_score", 0);
                editor.putString("first_name","Player 1");
                editor.putInt("second_score", 0);
                editor.putString("second_name","Player 2");
                editor.putInt("third_score", 0);
                editor.putString("third_name","Player 3");
                editor.commit();
            }
        });

        //****8
        //****8


        ListView listView = findViewById(R.id.my_list);

        List<Map<String,Object>> data= new ArrayList<>();

        HashMap<String,Object> user_1 = new HashMap<>();
        HashMap<String,Object> user_2 = new HashMap<>();
        HashMap<String,Object> user_3 = new HashMap<>();

        int rec_1=sp.getInt("first_score",0);
        int rec_2=sp.getInt("second_score",0);
        int rec_3=sp.getInt("third_score",0);

        String name_1 = sp.getString("first_name"," Player 1 ");
        String name_2 = sp.getString("second_name"," Player 2 ");
        String name_3 = sp.getString("third_name"," Player 3 ");

        user_1.put("place",R.drawable.troffi1);
        user_1.put("name", name_1);
        user_1.put("score",rec_1 );
        user_2.put("place",R.drawable.troffi2);
        user_2.put("name", name_2);
        user_2.put("score", rec_2);
        user_3.put("place",R.drawable.troffi3);
        user_3.put("name", name_3);
        user_3.put("score", rec_3);

        String[] from = {"place", "name" , "score"};
        int [] ids = {R.id.num, R.id.name, R.id.high_score_n};

        data.add(user_1);
        data.add(user_2);
        data.add(user_3);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.number_cell, from, ids );

        listView.setAdapter(simpleAdapter);



        SharedPreferences.Editor editor = sp.edit();
        editor.commit();



    }

}