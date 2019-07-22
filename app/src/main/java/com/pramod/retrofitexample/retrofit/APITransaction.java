package com.pramod.retrofitexample.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.pramod.retrofitexample.interfaces.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APITransaction {
    private static final String TAG = "APITransaction";

    public static void startNetworkService(Call request, final APIResponse apiResponse) {
        request.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Log.d(TAG, "onResponse: " + new Gson().toJson(response.body()));
                apiResponse.OnResponseAPI(response.body());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.d("onResponse", "error :" + t.getMessage());
                apiResponse.OnErrorAPI(t.getMessage());
            }
        });
    }
}
