package com.sobol.user.test_project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobol.user.test_project.R;

/**
 * Created by User on 09.04.2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    ImageView posterImageView;

    public ViewHolder(View itemView) {
        super(itemView);

        titleTextView = itemView.findViewById(R.id.title_text_view);
        posterImageView = itemView.findViewById(R.id.poster_image_view);
    }

}
