package com.sumod.interfaceapp;


import com.sumod.interfaceapp.model.CoreData;
import com.sumod.interfaceapp.model.Job;
import com.sumod.interfaceapp.model.Lead;
import com.sumod.interfaceapp.model.Proposal;
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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public class Api {

    public static final ApiService service;


    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.HOST)
//                .baseUrl("http://mh.bigindiannews.com/")
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
        @GET("values.php")
        Call<CoreData> init();


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


        @FormUrlEncoded
        @POST("login.php")
        Call<User> login(
                @Field("phone") String phone,
                @Field("password") String password);


        @FormUrlEncoded
        @POST("signup.php")
        Call<User> signup(
                @Field("name") String name,
                @Field("phone") String phone,
                @Field("password") String password,
                @Field("bizType") String bizType,

                @Field("location_id") Integer locationid,
                @Field("job_sector_id") Integer job_sector_id,
                @Field("job_role_id") Integer job_role_id,
                @Field("service_occupation_id") Integer service_occupation_id,
                @Field("service_name_id") Integer service_name_id,

                @Field("product_channel_id") Integer product_channel_id,
                @Field("product_name_id") Object product_name_id);


        @GET("leads.php")
        Call<List<Lead>> getLeads(
                @Query("user_id") Integer userId,
                @Query("job_seeker") Integer job_seeker,
                @Query("service_seeker") Integer service_seeker,
                @Query("product_seeker") Integer product_seeker,

                @Query("no_jobs") Integer no_jobs,
                @Query("no_services") Integer no_services,
                @Query("no_products") Integer no_products
        );


        @FormUrlEncoded
        @POST("leads.php")
        Call<Lead> createLead(
                @Field("creator_id") Integer creator_id,
                @Field("description") String description,
                @Field("job_sector_id") Integer job_sector_id,
                @Field("job_role_id") Integer job_role_id,
                @Field("is_job_seeker") Integer is_job_seeker,
                @Field("service_occupation_id") Integer service_occupation_id,
                @Field("service_name_id") Integer service_name_id,
                @Field("is_service_seeker") Integer is_service_seeker,
                @Field("product_name_id") Integer product_name_id,
                @Field("product_channel_id") Integer product_channel_id,
                @Field("is_product_seeker") Integer is_product_seeker,
                @Field("location_id") Integer location_id);


        @GET("proposals.php")
        Call<List<Proposal>> getProposals(@Query("user_id") int userId);

        @GET("chat/getChats.php")
        Call<List<Integer>> getChats(@Query("user_id") int userId);


        @FormUrlEncoded
        @POST("proposals.php")
        Call<Proposal> createProposal(
                @Field("user_id") Integer user_id,
                @Field("lead_id") Integer lead_id);
    }
}
