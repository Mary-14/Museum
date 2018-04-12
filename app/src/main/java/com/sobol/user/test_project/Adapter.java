package com.sobol.user.test_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.layout_item, parent, false);
      ViewHolder vH= new ViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Movie movie = Database.MOVIES[position];
        String title = movie.title;
        //holder.posterImageView.setBackground(posters);
        holder.titleTextView.setText(title);
        int randomColor = 0x00FF000000 + (int)(Math.random() * 0x01000000);
        holder.posterImageView.setBackgroundColor(randomColor);

   holder.itemView.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View view) {
           MovieActivity(movie);
       }
   });

    }

    @Override
    public int getItemCount() {
        return Database.MOVIES.length;
    }

    private void MovieActivity(Movie movie){
    Intent intent = new Intent(context, MovieActivity.class);
    intent.putExtra("MOVIE", movie);
    context.startActivity(intent);


    }

}
