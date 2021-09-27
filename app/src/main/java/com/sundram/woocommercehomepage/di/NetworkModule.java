package com.sundram.woocommercehomepage.di;

import static com.sundram.woocommercehomepage.utils.ConstantUtils.BASE_URL;
import static com.sundram.woocommercehomepage.utils.ConstantUtils.UID;

import androidx.annotation.NonNull;

import com.sundram.woocommercehomepage.apiservices.HomeApiService;
import com.sundram.woocommercehomepage.utils.ConstantUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn({SingletonComponent.class})
public class NetworkModule {

    @Provides
    @Singleton
    public static Retrofit getRetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("uid", UID)
                    .addHeader("langid","en").build();
            return chain.proceed(request);
        })
                .connectTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();


        OkHttpClient.Builder cBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        cBuilder.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static HomeApiService provideHomeApiService(Retrofit retrofit) {
        return retrofit.create(HomeApiService.class);
    }

}
