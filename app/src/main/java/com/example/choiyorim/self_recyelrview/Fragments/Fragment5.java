package com.example.choiyorim.self_recyelrview.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.choiyorim.self_recyelrview.R;

/**
 * Created by ChoiYorim on 2018-01-26.
 */

public class Fragment5 extends Fragment implements View.OnClickListener{
    private ImageButton btn;
    private Fragment fragment;
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment5,container,false);
        btn =(ImageButton) v.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        return v;
    }
    public void onClick(View v){
        fragment = new WriteFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer,fragment);
        fragmentTransaction.commit();
        }
    }
