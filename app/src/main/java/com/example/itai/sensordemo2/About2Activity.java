package com.example.itai.sensordemo2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class About2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);

        ImageView logoAnim = findViewById(R.id.phone);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.phone_anim);
        logoAnim.startAnimation(animation);

    }
}