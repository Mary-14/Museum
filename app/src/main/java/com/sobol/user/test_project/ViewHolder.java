package com.sobol.user.test_project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobol.user.test_project.R;


public class ViewHolder extends RecyclerView.ViewHolder {

    View itemView;
   TextView titleTextView;
   ImageView photoImageView;

    public ViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;

        titleTextView = itemView.findViewById(R.id.title_text_view);
        photoImageView = itemView.findViewById(R.id.photo_image_view);

    }
}
