package com.sobol.user.test_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    MainActivity activity;

    public Adapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
      View view = inflater.inflate(R.layout.layout_item, parent, false);
      ViewHolder vH= new ViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Museum museum = DatabaseMuseum.MUSEUMS [position];
        String title = museum.title;
     //   holder.photoImageView.setBackground(photo);
        holder.titleTextView.setText(title);
        int randomColor = 0x00FF000000 + (int)(Math.random() * 0x01000000);
        holder.photoImageView.setBackgroundColor(randomColor);

   holder.itemView.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View view) {
           MuseumActivity(museum);
       }
   });

    }

    @Override
    public int getItemCount() {
        return DatabaseMuseum.MUSEUMS.length;
    }

    private void showMuseumActivity(Museum museum){
        activity.getSupportFragmentManager()
                .beginTransaction()




        Intent intent = new Intent(context, MuseumActivity.class);
    intent.putExtra("MUSEUM", museum);
    context.startActivity(intent);
    }

}
