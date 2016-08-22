package com.example.mortx1.githubusers.data;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
  public static final String PREF_USERS = "prefUsers";

  Application application;

  public DataModule(Application application) {

    this.application = application;
  }

  @Provides
  @Singleton
  SharedPreferences providesSharedPreferences() {
    return application.getSharedPreferences("com.example.gitHubUsers", Context.MODE_PRIVATE);
  }

  @Provides
  @Singleton
  LatestGitHubPrefs provideLatestGithubPrefs(SharedPreferences preferences) {

    return new LatestGitHubPrefs(preferences, PREF_USERS);
  }
}
