package com.example.micha.searchgit;

import android.util.Log;

import com.example.micha.searchgit.model.GitUser;
import com.example.micha.searchgit.model.Repository;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by micha on 2/7/2018.
 */

public class RetrofitHelper {

    public static final String TAG = RetrofitHelper.class.getSimpleName();

    public static class Factory{
        public static Retrofit getRetro(){
            return new Retrofit.Builder().baseUrl(MainActivity.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
    }

    public static Observable<GitUser> gitUser(String user){
        RetrofitService gitService = RetrofitHelper.Factory.getRetro().create(RetrofitService.class);
        Log.d(TAG, "gitUser: Testing 1,2,3");
        return gitService.gitUser(user);
    }

    public static Observable<Repository[]> getRepositories(String user){
        RetrofitService gitService = RetrofitHelper.Factory.getRetro().create(RetrofitService.class);
        return gitService.getRepos(user);
    }


    interface RetrofitService{
        @GET("/users/{user}")
        Observable<GitUser> gitUser(@Path("user") String user);

        @GET("users/{user}/repos")
        Observable<Repository[]> getRepos(@Path("user") String user);
    }
}
