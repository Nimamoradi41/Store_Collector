package com.example;

import android.app.Application;
import android.graphics.Typeface;

import com.example.store_collector.Api;
import com.example.store_collector.Constanc;
import com.example.store_collector.R;

import java.util.concurrent.TimeUnit;

import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppStart extends Application {
    public static Typeface danaTypeface;
    private static Api api;
    @Override
    public void onCreate() {


        super.onCreate();
        danaTypeface = Typeface.createFromAsset(getAssets(), "fonts/vazir_med.ttf");
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new io.github.inflationx.calligraphy3.CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/vazir_med.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }
    public static Api getApi() {
        if (api == null) {
            HttpLoggingInterceptor interceptor;
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(Constanc.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(Constanc.READ_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(Constanc.WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .build();
            api = new Retrofit.Builder()
                    .baseUrl(Constanc.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(Api.class);
        }


        return api;
    }
}
