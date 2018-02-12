package com.example.choiyorim.self_recyelrview.Activity;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.choiyorim.self_recyelrview.Fragments.Fragment1;
import com.example.choiyorim.self_recyelrview.Fragments.Fragment2;
import com.example.choiyorim.self_recyelrview.Fragments.Fragment3;
import com.example.choiyorim.self_recyelrview.Fragments.Fragment4;
import com.example.choiyorim.self_recyelrview.Fragments.Fragment5;
import com.example.choiyorim.self_recyelrview.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;
    private Fragment fragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noNavBarGoodness();


        mBottomBar.setItemsFromMenu(R.menu.bottom_bar, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                if (menuItemId == R.id.action_zero) {
                    fragment = new Fragment1();
                }
                else if (menuItemId == R.id.action_one) {
                    fragment = new Fragment2();
                } else if (menuItemId == R.id.action_two) {
                    fragment = new Fragment3();

                } else if (menuItemId == R.id.action_three) {
                    fragment  = new Fragment5();

                } else if (menuItemId == R.id.action_four) {
                    fragment = new Fragment4();
                }
                android.app.FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer,fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.action_zero) {
                    // The user reselected item number one, scroll your content to top.

                }
            }
        });


        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.colorPrimaryDark));


        // Set the color for the active tab. Ignored on mobile when there are more than three tabs.
        //  mBottomBar.setActiveTabColor("#009688");


        // mBottomBar.selectTabAtPosition(1, true);


    }

}