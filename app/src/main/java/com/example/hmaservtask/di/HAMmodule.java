package com.example.hmaservtask.di;

import com.example.hmaservtask.data.HAMApi;
import com.example.hmaservtask.repository.HAMRepository;
import com.example.hmaservtask.utils.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class HAMmodule {

    @Provides
    @Singleton
    public static HttpLoggingInterceptor getHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public static OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    public static Retrofit getRetrofit(
            OkHttpClient client
    ) {
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static HAMApi getHamApi(Retrofit retrofit) {
        return retrofit.create(HAMApi.class);
    }

//    @Provides
//    @Singleton
//    public static HAMRepository getHamRepository() {
//        return new HAMRepository();
//    }
}
