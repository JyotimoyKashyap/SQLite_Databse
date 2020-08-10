package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private List<Model.List> data;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_tab1, container, false);


         // recycler View part

         RecyclerView recyclerView = view.findViewById(R.id.settings);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         recyclerView.setHasFixedSize(true);

         recyclerView.setAdapter(new RecyclerViewAdapter(items()));



         return  view;
    }

    private List<Model.List> items(){

        data = new ArrayList<>();
        data.add(new Model.List(R.drawable.aboutphone, "About Phone"));
        data.add(new Model.List(R.drawable.battery, "Battery"));
        data.add(new Model.List(R.drawable.security, "Privacy"));
        data.add(new Model.List(R.drawable.privacy, "Security"));
        data.add(new Model.List(R.drawable.update, "System Update"));
        data.add(new Model.List(R.drawable.brightness, "Display"));
        data.add(new Model.List(R.drawable.wifi, "WiFi"));
        data.add(new Model.List(R.drawable.bluetooth, "Bluetooth"));
        data.add(new Model.List(R.drawable.call, "Video"));
        data.add(new Model.List(R.drawable.connection, "Connection and Sharing"));
        data.add(new Model.List(R.drawable.sim, "SIM card manager"));
        data.add(new Model.List(R.drawable.experiment, "Experimental Features"));
        data.add(new Model.List(R.drawable.google, "Google"));


        return data;
    }



}