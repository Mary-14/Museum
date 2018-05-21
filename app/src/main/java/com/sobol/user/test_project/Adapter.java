package com.sobol.user.test_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sobol.user.test_project.fragments.MuseumFragment;

import static android.content.ContentValues.TAG;
import static com.sobol.user.test_project.DatabaseMuseum.MUSEUMS;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    MainActivity activity;
    GeoDataClient mGeoDataClient;


    public Adapter(MainActivity activity) {
        this.activity = activity;

        mGeoDataClient = Places.getGeoDataClient(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
      View view = inflater.inflate(R.layout.layout_item, parent, false);
      ViewHolder vH= new ViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Museum museum = MUSEUMS.get(position);

        String placeId = DatabaseMuseum.MUSEUMS.get(position).place_id;
        getPhoto(holder, placeId);
        getPlace(holder, placeId);


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

    private void showPhoto(ViewHolder holder, Bitmap photo) {
        holder.photoImageView.setImageBitmap(photo);
    }

    private void showPlace(ViewHolder holder, Place place) {
        holder.titleTextView.setText(place.getName());
    }

    private void getPhoto(final ViewHolder holder, String placeId) {
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(placeId);
        photoMetadataResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
                // Get the list of photos.
                PlacePhotoMetadataResponse photos = task.getResult();
                // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
                PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
                // Get the first photo in the list.
                PlacePhotoMetadata photoMetadata = photoMetadataBuffer.get(0);
                // Get the attribution text.
                CharSequence attribution = photoMetadata.getAttributions();
                // Get a full-size bitmap for the photo.
                Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photoMetadata);
                photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlacePhotoResponse> task) {
                        PlacePhotoResponse photo = task.getResult();
                        Bitmap bitmap = photo.getBitmap();
                        showPhoto(holder, bitmap);
                    }
                });
            }
        });
    }
    private void getPlace(final ViewHolder holder, String placeId) {
        mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);
                    Log.i(TAG, "Place found: " + myPlace.getName());
                    showPlace(holder, myPlace);
                } else {
                    Log.e(TAG, "Place not found.");
                }
            }
        });
    }
}

