package com.example.itai.sensordemo2;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.ParticleSystem;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Menu2Activity extends Activity {




    @Override
    public void onBackPressed() {
        moveTaskToBack(false);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        MediaPlayer ring= MediaPlayer.create(Menu2Activity.this,R.raw.music);
        ring.start();


        Button startMap = findViewById(R.id.btn1);
        startMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, MapActivity.class);

                startActivity(intent);
            }
        });


        Button startAbout = findViewById(R.id.btn2);
        startAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, About2Activity.class);

                startActivity(intent);
            }
        });

        Button startscore = findViewById(R.id.btn3);
        startscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, HighScore.class);

                startActivity(intent);
            }
        });




        final ImageView star1Anim = findViewById(R.id.star1);
        ImageView star2Anim = findViewById(R.id.star2);

        ImageView logoAnim = findViewById(R.id.logo1);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        logoAnim.startAnimation(animation);


        star1Anim.animate().translationX(400).translationY(400).setDuration(1500).start();
        star2Anim.animate().translationX(400).translationY(400).setDuration(1500).setStartDelay(700);


    }


}

/*
package com.example.itai.sensordemo2;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.ParticleSystem;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Menu2Activity extends Activity {



    private MediaPlayer mp;


    @Override
    public void onBackPressed() {
        moveTaskToBack(false);

    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        stopPlaying();
        mp =MediaPlayer.create(Menu2Activity.this,R.raw.music);

        mp.start();

        Button startMap = findViewById(R.id.btn1);
        startMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, MapActivity.class);

                startActivity(intent);
            }
        });

        Button startscore = findViewById(R.id.btn3);
        startscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, HighScore.class);

                startActivity(intent);
            }
        });


        Button instructions = findViewById(R.id.btn2);
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });




        final ImageView star1Anim = findViewById(R.id.star1);
        ImageView star2Anim = findViewById(R.id.star2);

        ImageView logoAnim = findViewById(R.id.logo1);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        logoAnim.startAnimation(animation);


        star1Anim.animate().translationX(400).translationY(400).setDuration(1500).start();
        star2Anim.animate().translationX(400).translationY(400).setDuration(1500).setStartDelay(700);


    }


}*/
