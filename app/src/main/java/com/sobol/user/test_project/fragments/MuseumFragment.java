package com.sobol.user.test_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sobol.user.test_project.Museum;
import com.sobol.user.test_project.R;


public class MuseumFragment extends Fragment {

    public static MuseumFragment newInstance(Museum museum) {
        MuseumFragment fragment = new MuseumFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("MUSEUM", museum );
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_museum, container, false);

        Museum museum = (Museum) getArguments().getSerializable("MUSEUM");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        //activity.getSupportActionBar().setTitle(museum.title);

        return view;
    }

}
//AIzaSyBwZSLvC_Fhp8W3vbEreFWx_UKN8qTjk7w
//https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&type=museum&key=AIzaSyBwZSLvC_Fhp8W3vbEreFWx_UKN8qTjk7w