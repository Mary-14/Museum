package com.sobol.user.test_project.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sobol.user.test_project.Adapter;
import com.sobol.user.test_project.MainActivity;
import com.sobol.user.test_project.Museum;
import com.sobol.user.test_project.R;


public class MuseumsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.museum_fragment, container, false);

        Context context = getContext();
        Adapter adapter = new Adapter((MainActivity) context);
        RecyclerView recyclerView= view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        return view;

    }
}
