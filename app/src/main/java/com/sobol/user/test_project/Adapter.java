package com.sobol.user.test_project;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 09.04.2018.
 */

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    static final String[] MOVIES_TITLES = new String[]{"Coco", "Star Wars: The Last Jedi", "Ready Player One", "Black Panther"};
    static final String[] MOVIES_POSTERS = new String[]{"/eKi8dIrr8voobbaGzDpe8w0PVbC.jpg", "/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg", "/pU1ULUq8D3iRxl1fdX2lZIzdHuI.jpg", "/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"};

    Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
      View v = inflater.inflate(R.layout.layout_item, parent, false);
      ViewHolder vH= new ViewHolder(v);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String title = MOVIES_TITLES[position];
        String posters = MOVIES_POSTERS[position];
        holder.posterImageView.set(posters);
        holder.titleTextView.setText(title);
    }

    @Override
    public int getItemCount() {
        return MOVIES_TITLES.length;
    }
}
