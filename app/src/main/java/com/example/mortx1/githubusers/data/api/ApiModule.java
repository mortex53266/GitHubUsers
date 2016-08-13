package com.example.mortx1.githubusers.data.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
  @Provides
  @Singleton
  GithubService provideGithubService() {
    return new Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(GithubService.class);
  }
}

