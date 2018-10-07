package com.story.hacker.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for Create and Handling retrofit instance handling
 * @author Arbaz Rizvi
 */
public class RetrofitClient {

    public static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

    private static RetrofitClient sInstance;
    private Retrofit retrofit;

    public static void create() {
        if (sInstance == null) {
            synchronized (RetrofitClient.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitClient();
                }
            }
        } else
            throw new IllegalStateException("RetrofitClient instance is already been created.");
    }


    private RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Returns the instance of {@link Retrofit}.
     * This method must be called after {@link #create}.
     */
    public static Retrofit retrofit() {
        synchronized (RetrofitClient.class) {
            if (sInstance == null)
                throw new IllegalStateException("RetrofitClient instance is not created yet. Call RetrofitClient.create() before calling getInstance()");

        }
        return sInstance.retrofit;
    }

    /**
     * Returns the instance of {@link APIInterface}.
     * This method must be called after {@link #create}.
     */
    public static APIInterface getAPIInterface() {
        return retrofit().create(APIInterface.class);
    }

}
