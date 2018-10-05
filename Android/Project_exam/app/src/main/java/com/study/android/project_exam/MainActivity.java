package com.study.android.project_exam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewPager pager1;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager1 = findViewById(R.id.ViewPager1);
        pager1.setOffscreenPageLimit(5);

        MyPagerAdapter adapter = new MyPagerAdapter(this);
        pager1.setAdapter(adapter);

    }

    public void naviClicked(View v){
        Intent intent = new Intent(getApplicationContext(),Navigator.class);
        startActivity(intent);
    }

    public void orderClicked(View v){
        Intent intent = new Intent(getApplicationContext(),order.class);
        startActivity(intent);
    }


    class MyPagerAdapter extends PagerAdapter {


        private LayoutInflater inflater;
        private Context context;

        public MyPagerAdapter(Context context){
            this.context = context;
        }

        private int[] images = {R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3, R.drawable.img4, R.drawable.img5 };

        @Override
        public int getCount() {

            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            pager1.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.activity_slider, container, false);
            ImageView imageView = v.findViewById(R.id.imageView);
            imageView.setImageResource(images[position]);
            container.addView(v);
            return v;
        }
    }
}
