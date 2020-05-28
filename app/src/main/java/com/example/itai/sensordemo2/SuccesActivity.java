package com.example.itai.sensordemo2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class SuccesActivity extends Activity {

    KonfettiView p2;

    String name;

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succses);


        //ImageButton confirm = findViewById(R.id.conf);
        ImageButton menu =findViewById(R.id.menu_final);
        Button highS=findViewById(R.id.high_score_final);

        final EditText et = findViewById(R.id.name);

        final SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);
        int current_score = sp.getInt("score_total_1",0);
        if(current_score < sp.getInt("third_score",0)){
            et.setVisibility(View.INVISIBLE);
           // confirm.setVisibility(View.INVISIBLE);
        }

        //***********************************88


        //******************************************************
        p2=findViewById(R.id.viewconfetty);

        p2.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA,Color.RED,Color.BLUE,Color.CYAN)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(8000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 10))
                .setPosition(0f, 900f, 10f, 0f)
                .stream(200, 5000L);



        ImageView logoAnim = findViewById(R.id.title1);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.egg_anim);
        logoAnim.startAnimation(animation);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccesActivity.this, Menu2Activity.class);
                startActivity(intent);
            }
        });

        highS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccesActivity.this, HighScore.class);
                name = et.getText().toString();
                final SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);
                int current_score = sp.getInt("score_total_1",0);
                int rec_1=sp.getInt("first_score",0);
                int rec_2=sp.getInt("second_score",0);
                int rec_3=sp.getInt("third_score",0);
                String name_1 = sp.getString("first_name"," Player 1 ");
                String name_2 = sp.getString("second_name"," Player 2 ");
                String name_3 = sp.getString("third_name"," Player 3 ");

                if(current_score > rec_1){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("first_score", current_score);
                    editor.putString("first_name",name);
                    editor.putInt("second_score", rec_1);
                    editor.putString("second_name",name_1);
                    editor.putInt("third_score", rec_2);
                    editor.putString("third_name",name_2);
                    editor.commit();
                }
                else if(current_score >rec_2){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("second_score", current_score);
                    editor.putString("second_name",name);
                    editor.putInt("third_score", rec_2);
                    editor.putString("third_name",name_2);
                    editor.commit();
                }
                else if(current_score >rec_3){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("third_score", current_score);
                    editor.putString("third_name",name);
                    editor.commit();
                }

                intent.putExtra("Name",name);
                startActivity(intent);
            }
        });
    }

    }