package com.sobol.user.test_project;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.util.logging.Level.parse;

public class DatabaseMuseum {
 /*  public static final Museum[] MUSEUMS = {
            new Museum("Эрмитаж", "https://s0.rbk.ru/v6_top_pics/resized/1180xH/media/img/3/69/754576020861693.jpg")
           ,new Museum("Третьяковская галерея", "https://minvr.ru/upload/iblock/de6/57492.jpg")
           ,new Museum ("Эрарта", "https://spbhi.ru/assets/images/showplace/muzei/erarta/2.jpg")
           ,new Museum("Лувр", "https://static.tonkosti.ru/images/4/40/%D0%92%D0%B8%D0%B4_%D0%BD%D0%B0_%D0%9B%D1%83%D0%B2%D1%80%2C_%D0%9F%D0%B0%D1%80%D0%B8%D0%B6.jpg")
   };*/

    public static final String DATA_URL= "https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&type=museum&key=AIzaSyBwZSLvC_Fhp8W3vbEreFWx_UKN8qTjk7w";
    public static final List<Museum> MUSEUMS = new ArrayList<>();

    public static  void load() {
        Request request = new Request.Builder()
                    .url(DATA_URL)
                    .build();

        OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                public void onFailure(Call call, IOException e) {
                }

                public void onResponse(Call call, Response response) throws IOException {
                    String data = response.body().string();
                    parse(data);
                }
            });
        }

    public static void parse(String data) {
        try {
            JSONObject object = new JSONObject(data);
            JSONArray results = object.getJSONArray("results");
            for (int i = 0; i < results.length(); ++i) {
                JSONObject museum1 = results.getJSONObject(i);
                double lat = museum1.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                double lng = museum1.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                String place_id= museum1.getJSONObject("geometry").getString("place_id");

                Museum museum = new Museum(lat,lng, place_id);
                MUSEUMS.add(museum);
            }
            EventBus.getDefault().post(new OnMuseumChangedEvent());
        } catch (JSONException e) {
            Log.e("AHAH", "ERROR!", e);
        }
    }

    public static class OnMuseumChangedEvent{

    }

}
