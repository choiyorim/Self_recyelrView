package com.example.choiyorim.self_recyelrview.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.choiyorim.self_recyelrview.Activity.testActivity;
import com.example.choiyorim.self_recyelrview.Adapter.MyAdpater;
import com.example.choiyorim.self_recyelrview.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ChoiYorim on 2018-01-26.
 */

public class Fragment5 extends Fragment implements View.OnClickListener{
    private ImageButton btn;
    private ArrayList<testActivity> items=new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mDatabase;
    private View v;
    private MyAdpater myAdapter;
    private Fragment fragment;

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment5,container,false);
        btn =(ImageButton) v.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("book");
        Query contacts = myRef;
        contacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //items.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    testActivity test = snapshot.getValue(testActivity.class);
                    items.add(test);
                    myAdapter.notifyItemInserted(items.size() - 1);

                }
                myAdapter = new MyAdpater(items,getActivity());

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;

    }

    @Override
    public void onClick(View view) {
        fragment = new WriteFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer,fragment);
        fragmentTransaction.commit();
    }

//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//            public void onClick(View v){
//        fragment = new WriteFragment();
//        android.app.FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.contentContainer,fragment);
//        fragmentTransaction.commit();
//        }
//    @Override
//    public void onActivityCreated(Bundle saveInsatnceState){
//        super.onActivityCreated(saveInsatnceState);
//
//    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //show();
//    }
//
//
//    private  String StringTest(String email){
//        String email1 = email;
//        int inx = email1.indexOf(".");
//        String email2 = email1.substring(0,inx);
//        return email2;
//    }
//
//    public void show() {
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("book");
//        final String email1=StringTest(email);
//        Query contacts = myRef;
//        WriteFragment wf = new WriteFragment();
//          date = wf.date;
//
//        contacts.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                items.clear();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    testActivity test = snapshot.getValue(com.example.choiyorim.self_recyelrview.Activity.testActivity.class);
//                    myAdapter.notifyItemInserted(test.size() -1);
//                    items.add(test);
//
//                }
//
//                recyclerView.setAdapter(new MyAdpater(getActivity().getApplicationContext(),items,R.layout.fragment_fragment5));
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

}

