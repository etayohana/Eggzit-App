package com.example.itai.sensordemo2;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;


    // list of images
    public int[] lst_images = {
            R.drawable.play1,
            R.drawable.play2,

    };
    // list of titles
    public String[] lst_title = {
            "instraction1",
            "instraction2",

    }   ;

    // list of background colors
    public int[]  lst_backgroundcolor = {

            Color.rgb(239,85,85),
            Color.rgb(110,49,89),

    };




    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_slide,container,false);
        RelativeLayout layoutslide = (RelativeLayout) view.findViewById(R.id.sliderelativelayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);

        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        //description.setText(lst_description[position]);

        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}

