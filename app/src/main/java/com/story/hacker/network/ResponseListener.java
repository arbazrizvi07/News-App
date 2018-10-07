package com.story.hacker.network;

import okhttp3.Headers;

/**
 * Interface for Retrofit Response Handling
 * @param <T>
 */
public interface ResponseListener<T> {

    void onResponse(T response, Headers headers);

    void onError(int status, Errors errors);

    void onFailure(Throwable throwable);
}
