package com.example.mortx1.githubusers.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mortx1.githubusers.data.api.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatestGitHubPrefs {

  public SharedPreferences sharedPreferences;
  public final String key;
  public final List<User> defaultList;
  public Context context;

  public LatestGitHubPrefs(SharedPreferences sharedPreferences, String key, List<User> defaultList) {
    this.sharedPreferences = sharedPreferences;
    this.key = key;
    this.defaultList = defaultList;
  }

  public LatestGitHubPrefs(SharedPreferences sharedPreferences, String key) {
    this.sharedPreferences = sharedPreferences;
    this.key = key;
    this.defaultList = Collections.emptyList();
  }

  public List<User> getList() {
    Gson gson = new Gson();
    String json = sharedPreferences.getString(key, "");
    Type type = new TypeToken<List<User>>() {
    }.getType();
    List<User> latestUsers = gson.fromJson(json, type);
    if(latestUsers != null && latestUsers.size() > 0)
      return (ArrayList) latestUsers;
    else
      return new ArrayList<>();
  }

  public void setList(User latestUsers) {
    List<User> myList = getList();
    myList.add(latestUsers);
    if (myList.size() > 5) {
      myList.remove(0);
    }
    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
    Gson gson = new Gson();
    String json = gson.toJson(myList);
    prefsEditor.putString(key, json);
    prefsEditor.commit();
  }

  public boolean isSet() {

    if (getList() == null) {
      return false;
    }
    return true;
  }

  public void delete() {
    getList().clear();

  }
}
