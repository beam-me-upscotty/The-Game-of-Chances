package com.somethoughts.chinmay.game.GameHolder;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.somethoughts.chinmay.game.R;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener
{
    ViewPager gamePager;
    gamePagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamePager = (ViewPager) findViewById(R.id.activity_pager);
        pagerAdapter = new gamePagerAdapter(getSupportFragmentManager());
        if(pagerAdapter == null)
            Log.v("Error","****PAge Adapter is down***");
        if(gamePager == null)
            Log.v("Error","****PAge Adapter is down***");
        gamePager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_view);
        tabLayout.addTab(tabLayout.newTab().setText("Ropaors"));
        tabLayout.addTab(tabLayout.newTab().setText("Dice Throw"));
        tabLayout.addTab(tabLayout.newTab().setText("Coin Toss"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        gamePager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
            gamePager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
}
