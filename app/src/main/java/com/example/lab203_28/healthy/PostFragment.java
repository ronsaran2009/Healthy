package com.example.lab203_28.healthy;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab203_28.healthy.Weight.Weight_FromFragment;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostFragment extends Fragment {

    OkHttpClient client = new OkHttpClient();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initbackbnt();

        String url = "https://jsonplaceholder.typicode.com/ posts";

        try {
            getRestAPI(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    String getRestAPI(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Log.d("POST", "request_"+ request);
        Response response = client.newCall(request).execute();
        Log.d("POST", "request_Client");
        Log.d("POST", response.body().string());
            return response.body().string();
    }
    void initbackbnt(){
        Button _back =  getView().findViewById(R.id.post_back);
        _back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("POST", "GOTO Menu");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
            }
        });
    }
}