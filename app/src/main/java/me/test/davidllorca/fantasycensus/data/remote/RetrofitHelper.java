package me.test.davidllorca.fantasycensus.data.remote;

import java.util.concurrent.TimeUnit;

import me.test.davidllorca.fantasycensus.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class to instantiate services.
 */
class RetrofitHelper {

    static <T> T createRetrofitService(final Class<T> service) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).build();

        return retrofit.create(service);
    }
}
