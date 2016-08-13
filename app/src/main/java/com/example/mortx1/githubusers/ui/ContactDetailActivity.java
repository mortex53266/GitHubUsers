package com.example.mortx1.githubusers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mortx1.githubusers.GitHubUsersApp;
import com.example.mortx1.githubusers.R;
import com.example.mortx1.githubusers.data.api.GithubService;
import com.example.mortx1.githubusers.data.api.models.User;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactDetailActivity extends AppCompatActivity {

  @BindView(R.id.collapsing_toolbar)
  CollapsingToolbarLayout collapsingToolbarLayout;
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @Inject
  GithubService githubService;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    String login = intent.getStringExtra("login");
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    GitHubUsersApp.get(this).getComponent().inject(this);
    getData(login);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.sample_actions, menu);
    return true;
  }

  public void getData(String username) {
    githubService.getUser(username).enqueue(new Callback<User>() {

      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();
        collapsingToolbarLayout.setTitle(user.login);
        Picasso.with(ContactDetailActivity.this).load(user.avatar_url).into((ImageView) findViewById(R.id.backdrop));
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(ContactDetailActivity.this, "Somthing is wrong...",
            Toast.LENGTH_LONG).show();
      }
    });
  }
}
