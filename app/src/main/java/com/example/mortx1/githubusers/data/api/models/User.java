package com.example.mortx1.githubusers.data.api.models;

public class User {
  public String login;
  public String avatar_url;

  public User(String login, String avatar_url) {
    this.login = login;
    this.avatar_url = avatar_url;
  }
}