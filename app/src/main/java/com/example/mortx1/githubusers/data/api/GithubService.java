package com.example.mortx1.githubusers.data.api;


import com.example.mortx1.githubusers.data.api.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

  @GET("/users")
  Call<List<User>> getUsers();

  @GET("/users/{username}")
  Call<User> getUser(@Path("username") String username);
}