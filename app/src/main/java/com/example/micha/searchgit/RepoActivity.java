package com.example.micha.searchgit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.micha.searchgit.model.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RepoActivity extends AppCompatActivity {

    private static final String TAG = RepoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        String user = getIntent().getStringExtra("user");
        final RecyclerView recycle = findViewById(R.id.repositories);
        RetrofitHelper.getRepositories(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Repository[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Repository[] repositories) {
                        List<Repository> repos = new ArrayList<>();
                        RepoAdapter adapter = new RepoAdapter();
                        adapter.addToList(repositories);
                        recycle.setAdapter(adapter);
                        RecyclerView.LayoutManager manager = new LinearLayoutManager(RepoActivity.this);
                        recycle.setLayoutManager(manager);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
