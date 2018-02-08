package com.example.micha.searchgit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.micha.searchgit.model.GitUser;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView profile;
    private EditText username;
    public static final String BASE_URL = "https://api.github.com/";
    private String user;
    private TextView name;
    private TextView bio;
    private boolean found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        profile = findViewById(R.id.portrait);
        name = findViewById(R.id.name);
        found = false;
        bio = findViewById(R.id.bio);
    }

    public void findUser(View view) {
        user = username.getText().toString();
        RetrofitHelper.gitUser(user).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<GitUser>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GitUser gitUser) {
                Toast.makeText(MainActivity.this, gitUser.getName(), Toast.LENGTH_SHORT).show();
                Glide.with(MainActivity.this).load(gitUser.getAvatarUrl()).into(profile);
                name.setText(gitUser.getName());
                bio.setText(gitUser.getBio());
                found = true;
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

    public void getRepos(View view) {
        if(found){
            Intent intent = new Intent(getApplicationContext(), RepoActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
    }
}
