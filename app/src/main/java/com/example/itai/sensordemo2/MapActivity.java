package com.example.itai.sensordemo2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.BreakIterator;

import static android.widget.ImageView.ScaleType.FIT_XY;

public class MapActivity extends Activity {


    TextView failTv;
    String time;
    static Intent intent;
    int temp=0;

    int fails=0;
    static int score=1;
    static int total_score;
    private TextView tvS;

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
        Intent intent = new Intent(MapActivity.this, Menu2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);
        int temp = sp.getInt("score_total",0);

        if((getIntent().getIntExtra("score", 0))> score ) {
            score++;
            total_score+=temp;
            //total_score += (getIntent().getIntExtra("total_score",0));
        }

        //total_score=getIntent().getIntExtra("total_score",0);


        ImageView logoAnim = findViewById(R.id.eg);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.egg_anim);
        logoAnim.startAnimation(animation);








        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ImageButton level1 = findViewById(R.id.level1);
        ImageButton level2 = findViewById(R.id.level2);
        ImageButton level3 = findViewById(R.id.level3);
        ImageButton level4 = findViewById(R.id.level4);
        ImageButton level5 = findViewById(R.id.level5);
        ImageButton level6 = findViewById(R.id.level6);

        level2.setBackgroundResource(R.drawable.btn2lock);
        level2.setScaleX((float) 1.0);
        level2.setScaleY((float) 1.2);
        level3.setBackgroundResource(R.drawable.btn3lock);
        level3.setScaleX((float) 1.0);
        level3.setScaleY((float) 1.2);
        level4.setBackgroundResource(R.drawable.btn4lock);
        level4.setScaleX((float) 1.0);
        level4.setScaleY((float) 1.2);
        level5.setBackgroundResource(R.drawable.btn5lock);
        level5.setScaleX((float) 1.0);
        level5.setScaleY((float) 1.2);
        level6.setBackgroundResource(R.drawable.btn6lock);
        level6.setScaleX((float) 1.0);
        level6.setScaleY((float) 1.2);
        TextView tv = findViewById(R.id.score_tv);
        tvS= findViewById(R.id.score_lock);
        //TextView tvS = findViewById(R.id.total_score);

       tv.setText(total_score + " ");
        tvS.setText(score + " ");
      //  failTv.setText(fails+ " ");





        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.activity_lock_level, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);

                ImageButton returnBtn = dialogview.findViewById(R.id.btn_gobacke);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*intent = new Intent(MapActivity.this, MapActivity.class);
                        intent.putExtra("failsEnded", 1);
                        startActivity(intent);*/
                        dialog.dismiss();
                    }
                });
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.activity_lock_level, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);

                ImageButton returnBtn = dialogview.findViewById(R.id.btn_gobacke);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.activity_lock_level, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);

                ImageButton returnBtn = dialogview.findViewById(R.id.btn_gobacke);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {dialog.dismiss();
                    }
                });

            }
        });
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.activity_lock_level, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);

                ImageButton returnBtn = dialogview.findViewById(R.id.btn_gobacke);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {dialog.dismiss();
                    }
                });

            }
        });
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                final View dialogview = getLayoutInflater().inflate(R.layout.activity_lock_level, null);
                builder.setView(dialogview).setCancelable(false);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(900, 600);

                ImageButton returnBtn = dialogview.findViewById(R.id.btn_gobacke);
                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {dialog.dismiss();
                    }
                });

            }
        });



        if(score>=2) {
            level2.setBackgroundResource(R.drawable.btn2unloc);
            level2.setScaleX((float) 1.0);
            level2.setScaleY((float) 1.2);

            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MapActivity.this, Main2Activity.class);
                   // intent.putExtra("last_score",total_score);
                    startActivity(intent);
                }
            });
        }
        if(score>=3) {
            level3.setBackgroundResource(R.drawable.btn3unlock);
            level3.setScaleX((float) 1.0);
            level3.setScaleY((float) 1.2);

            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapActivity.this, Main3Activity.class);
                    startActivity(intent);
                }
            });
        }
        if(score>=4) {
            level4.setBackgroundResource(R.drawable.btn4unlock);
            level4.setScaleX((float) 1.0);
            level4.setScaleY((float) 1.2);
            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapActivity.this, Main4Activity.class);
                    startActivity(intent);
                }
            });
        }
        if(score>=5) {
            level5.setBackgroundResource(R.drawable.btn5unlock);
            level5.setScaleX((float) 1.0);
            level5.setScaleY((float) 1.2);
            level5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapActivity.this, Main5Activity.class);
                    startActivity(intent);
                }
            });
        }
        if(score>=6) {
            level6.setBackgroundResource(R.drawable.btn6unlock);
            level6.setScaleX((float) 1.0);
            level6.setScaleY((float) 1.2);
            level6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapActivity.this, Main6Activity.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("score_total_1", total_score);
                    editor.commit();
                    startActivity(intent);
                }
            });
        }

    }

}






