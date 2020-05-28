package com.example.itai.sensordemo2;

import android.widget.Toast;

import java.util.Random;

import static android.app.PendingIntent.getActivity;
import static android.widget.Toast.LENGTH_LONG;

public class Coin {

    private int x_pos;
    private int y_pos;
    private boolean viseblel;

    public Coin(int i, int i1, boolean b) {
        this.x_pos = i;
        this.y_pos = i1;
        this.viseblel = b;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public boolean isViseblel() {
        return viseblel;
    }

    public void setViseblel(boolean viseblel) {
        this.viseblel = viseblel;
    }


    public int[] randomCoinsX(Fendss fends_arr[], FinishPoint fin, int size, int viewWidth, int viewHeight) {
        Random rand = new Random();
        int[] arr = new int[2];
        int x_1 = rand.nextInt(viewWidth) + 1;
        int y_1 = rand.nextInt(viewHeight) + 1;

        for (int i = 0; i < size; i++) {
            if(x_1 >= fends_arr[i].getStartX()-240 &&
                    x_1 <= fends_arr[i].getEndX()+140 &&
                    y_1 >= fends_arr[i].getStarty()-240 &&
                    y_1 <= fends_arr[i].getEndy()+140){

                x_1=rand.nextInt(viewWidth)+1;
                y_1 = rand.nextInt(viewHeight) + 1;
                i=0;
            }
            if(y_1<=200 ){
                y_1 = rand.nextInt(viewHeight) + 1;
                i=0;
            }
            if(y_1==50 && x_1 == 50){
                x_1=rand.nextInt(viewWidth)+1;
                y_1 = rand.nextInt(viewHeight) + 1;
                i=0;
            }
            if(x_1>=fin.getStartX()-80 && x_1<=fin.getEndX()+80 && y_1 >= fin.getStartY()-80 &&y_1<= fin.getEndY()+80){
                x_1=rand.nextInt(viewWidth)+1;
                y_1 = rand.nextInt(viewHeight) + 1;
                i=0;
            }

        }
            /*else if (y_1 <= 200) {
                y_1 = rand.nextInt(viewHeight) + 1;
            }
            else if (y_1 == 50 && x_1 == 50) {
                x_1 = rand.nextInt(viewWidth) + 1;
                y_1 = rand.nextInt(viewHeight) + 1;
            }*/

            arr[0] = x_1;
            arr[1] = y_1;
            return arr;
        }

    }


