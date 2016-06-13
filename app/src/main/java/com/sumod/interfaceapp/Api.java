package com.sumod.interfaceapp;


import com.sumod.interfaceapp.model.Job;
import com.sumod.interfaceapp.model.User;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class Api {

    public static final ApiService service;


    static {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.102/mahendra/")
                .baseUrl("http://mh.bigindiannews.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build();


        service = retrofit.create(ApiService.class);
    }


    static OkHttpClient createClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                okhttp3.Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(interceptor);

        return client.build();
    }


    public interface ApiService {
        @GET("jobs.php?get")
        Call<List<Job>> listJobs(
                @Query("need") String need,
                @Query("job") String job,
                @Query("location_id") int locationid);

        @GET("jobs.php?add")
        Call<String> postJob(
                @Query("creator_id") int creatorId,
                @Query("need") String need,
                @Query("job") String job,
                @Query("description") String description,
                @Query("location_id") int locationid);

        @GET("login.php?signin")
        Call<String> signup(
                @Query("name") String name,
                @Query("email") String email,
                @Query("phone") String phone,
                @Query("password") String password,
                @Query("location_id") int locationid,
                @Query("occupation") String occupation,
                @Query("job") String job,
                @Query("bizType") String bizType);

        @GET("login.php?login")
        Call<User> login(
                @Query("email") String email,
                @Query("password") String password);
    }
}
