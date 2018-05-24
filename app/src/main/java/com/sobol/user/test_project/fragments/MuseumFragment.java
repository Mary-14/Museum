package com.sobol.user.test_project.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.sobol.user.test_project.DatabaseMuseum;
import com.sobol.user.test_project.MainActivity;
import com.sobol.user.test_project.Museum;
import com.sobol.user.test_project.R;
import com.sobol.user.test_project.ViewHolder;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;

public class MuseumFragment extends Fragment {

    GeoDataClient mGeoDataClient;

    TextView titleTextView;
    ImageView photoImageView;
    ProgressBar progressBar;

    static public MuseumFragment newInstance(Museum museum) {
        MuseumFragment fragment = new MuseumFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("MUSEUM", museum );
        fragment.setArguments(arguments);


        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mGeoDataClient = Places.getGeoDataClient(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_museum, container, false);
        Museum museum = (Museum) getArguments().getSerializable("MUSEUM");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
       // activity.getSupportActionBar().setTitle(museum.title);

        titleTextView = view.findViewById(R.id.title_text_view);
        photoImageView = view.findViewById(R.id.photo_image_view);
        progressBar = view.findViewById(R.id.progress_bar);

        getPhoto(museum.place_id);
        getPlace(museum.place_id);

        return view;
    }

    private void showPhoto(Bitmap photo) {
        progressBar.setVisibility(GONE);
        photoImageView.setImageBitmap(photo);
    }

    private void showPlace(Place place) {
        titleTextView.setText(place.getName());
    }

    private void getPhoto(String placeId) {
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
                        showPhoto(bitmap);
                    }
                });
            }
        });
    }
    private void getPlace(String placeId) {
        mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);
                    Log.i(TAG, "Place found: " + myPlace.getName());
                    showPlace(myPlace);
                } else {
                    Log.e(TAG, "Place not found.");
                }
            }
        });
    }

}
//AIzaSyBwZSLvC_Fhp8W3vbEreFWx_UKN8qTjk7w
//https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&type=museum&key=AIzaSyBwZSLvC_Fhp8W3vbEreFWx_UKN8qTjk7w