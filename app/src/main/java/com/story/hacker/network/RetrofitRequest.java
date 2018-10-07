package com.story.hacker.network;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Class handle network calls
 *@author Arbaz Rizvi
 * @param <T>
 */
public final class RetrofitRequest<T> {

    private final ResponseListener responseListener;
    private Call<T> call;

    public RetrofitRequest(Call<T> call, ResponseListener<T> responseListener) {
        this.call = call;
        this.responseListener = responseListener;
    }

    public void enqueue() {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful())
                    responseListener.onResponse(response.body(), response.headers());
                else {
                    Errors errors = parseError(response);
                    responseListener.onError(response.code(), errors);
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                if (call.isCanceled())// don't process if request is cancelled.
                    return;
                if (t instanceof UnknownHostException)
                    responseListener.onFailure(t);//network error
                else {
                    List<Errors.Error> errors = new ArrayList<>();
                    errors.add(new Errors.Error("Something went wrong", ""));
                    responseListener.onError(400, new Errors(errors));
                }
            }
        });
    }

    public void cancel() {
        call.cancel();
    }

    public void retry() {
        call = call.clone();
        enqueue();
    }

    private static Errors parseError(Response<?> response) {
        Converter<ResponseBody, Errors> converter = RetrofitClient.retrofit()
                .responseBodyConverter(Errors.class, new Annotation[0]);
        Errors error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new Errors();
        }
        return error;
    }

}
