package com.example.myapplication;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private List<ImageView> mImageList = new ArrayList<ImageView>();
    private int[] mPics = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private int mNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
        getData();
        initAdapter();
        //Register this page
        mViewPager.addOnPageChangeListener(this);
        mLinearLayout.getChildAt(0).setEnabled(true);   //Display the little dot
    }
    private void bindID() {
        mViewPager = findViewById(R.id.main_viewpager);
        mLinearLayout = findViewById(R.id.main_linear);
    }
    private void getData() {
        ImageView imageView;
        View view;
        for (int pic : mPics) {
            imageView = new ImageView(MainActivity.this);
            imageView.setBackgroundResource(pic);
            mImageList.add(imageView);

            //Add littledot
            view = new View(MainActivity.this);
            view.setBackgroundResource(R.drawable.littledot);
            view.setEnabled(false);
            //Set width and height
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            //Set interval
            if (pic != mPics[0]) {
                layoutParams.leftMargin = 40;
            }
            //Add to LinearLayout
            mLinearLayout.addView(view, layoutParams);

        }
    }
    private void initAdapter() {
        ImgAdapter imgAdapter;
        imgAdapter = new ImgAdapter(mImageList);
        mViewPager.setAdapter(imgAdapter);
        /*MyAdapter mAdapter;
        mAdapter = new MyAdapter();
        mViewPager.setAdapter(mAdapter);*/
    }

    public void Entersearch(View view) {
        Intent intent;
        intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    //Listen to the page swipe
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mLinearLayout.getChildAt(mNum).setEnabled(false);
        mLinearLayout.getChildAt(position).setEnabled(true);
        mNum = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /*
    private class MyAdapter extends PagerAdapter {
        //大小
        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageList.get(position));
        }
    }*/



}
