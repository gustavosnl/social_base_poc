package com.glima.socialbasepoc.network.service;

import android.content.Context;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.network.StringConverterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by gustavo on 04/07/16.
 */
public class BaseDataSource {

    private final String HEADER_CONTENT_TYPE = "Content-type";
    private final String HEADER_API_KEY = "trakt-api-key";
    private final String HEADER_API_VERSION = "trakt-api-version";

    protected Retrofit retrofit;
    private Context context;

    public BaseDataSource(Context context, int baseUrlResourceId) {
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(baseUrlResourceId))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .client(setupClient())
                .build();
    }

    private OkHttpClient setupClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addNetworkInterceptor(buildInterceptor());
        return httpClient.build();
    }

    private Interceptor buildInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().
                        newBuilder().
                        addHeader(HEADER_CONTENT_TYPE, "application/json").
                        addHeader(HEADER_API_KEY, context.getString(R.string.api_key_trakt)).
                        addHeader(HEADER_API_VERSION, "2").build();

                return chain.proceed(request);
            }
        };
    }
}
