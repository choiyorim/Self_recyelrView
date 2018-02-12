package com.example.choiyorim.self_recyelrview.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.choiyorim.self_recyelrview.R;

/**
 * Created by ChoiYorim on 2018-01-26.
 */
public class Fragment2 extends Fragment {
        private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2,container,false);

        return v;
    }
}