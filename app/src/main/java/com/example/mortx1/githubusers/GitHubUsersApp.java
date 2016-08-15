package com.example.mortx1.githubusers;

import android.app.Application;
import android.content.Context;

import com.example.mortx1.githubusers.data.api.ApiModule;

public class GitHubUsersApp extends Application {

  MainComponent mainComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    mainComponent = DaggerMainComponent.builder()
        .apiModule(new ApiModule())
        .build();

  }

  public MainComponent getComponent() {
    return mainComponent;
  }

  public static GitHubUsersApp get(Context context) {
    return (GitHubUsersApp) context.getApplicationContext();
  }
}
