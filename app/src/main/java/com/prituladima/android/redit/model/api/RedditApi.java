package com.prituladima.android.redit.model.api;

import com.google.gson.GsonBuilder;
import com.prituladima.android.redit.BuildConfig;
import com.prituladima.android.redit.model.dto.ResponseDTO;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RedditApi {

    @GET("/top.json")
    Observable<ResponseDTO> getPage(@Query("count") int count, @Query("after") String after);

    class Factory {

        @Inject
        public Factory() {
        }

        public RedditApi makeService() {
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                    new GsonBuilder()
                            .registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                            .create());

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .writeTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(50, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RedditApi.class);
        }
    }
}