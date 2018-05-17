package com.sobol.user.test_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sobol.user.test_project.fragments.MuseumFragment;

import static android.content.ContentValues.TAG;
import static com.sobol.user.test_project.DatabaseMuseum.MUSEUMS;


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
        final Museum museum = MUSEUMS.get(position);

        String place_id = DatabaseMuseum.MUSEUMS.get(position).place_id;

        mGeoDataClient = Places.getGeoDataClient(this, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);
                    Log.i(TAG, "Place found: " + myPlace.getName());
                    places.release();
                } else {
                    Log.e(TAG, "Place not found.");
                }
            }
        });


   holder.itemView.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View view) {
          showMuseumActivity(museum);
       }
   });

    }

    @Override
    public int getItemCount() {
        return MUSEUMS.size();
    }

    private void showMuseumActivity(Museum museum) {
        MuseumFragment fragment = MuseumFragment.newInstance(museum);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment, null)
                .commit();

    }

}
