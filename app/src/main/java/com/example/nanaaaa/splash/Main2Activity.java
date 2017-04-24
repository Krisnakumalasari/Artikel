package com.example.nanaaaa.splash;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nanaaaa.splash.adapter.HotelAdapter;
import com.example.nanaaaa.splash.models.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        new AsyncFetch().execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(Main2Activity.this);
        HttpURLConnection connection;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("\tLoading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL("http://dev.republika.co.id/android/latest/smktelkom");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = connection.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("unsucsessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                connection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();
            List<Hotel> data = new ArrayList<>();

            progressDialog.dismiss();
            try {
                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Hotel hotel = new Hotel();
                    hotel.title = jsonObject.getString("title");
                    hotel.author = jsonObject.getString("author");
                    hotel.category = jsonObject.getString("category");
                    hotel.thumbnail = jsonObject.getString("thumbnail");
                    hotel.content = jsonObject.getString("content");
                    data.add(hotel);
                }

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                hotelAdapter = new HotelAdapter(Main2Activity.this, data);
                recyclerView.setAdapter(hotelAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));


            } catch (JSONException e) {
                Toast.makeText(Main2Activity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}