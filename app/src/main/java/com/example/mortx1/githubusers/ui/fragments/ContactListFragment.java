package com.example.mortx1.githubusers.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mortx1.githubusers.GitHubUsersApp;
import com.example.mortx1.githubusers.R;
import com.example.mortx1.githubusers.data.api.GithubService;
import com.example.mortx1.githubusers.ui.adapters.SimpleRecyclerViewAdapter;
import com.example.mortx1.githubusers.data.api.models.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactListFragment extends Fragment {

  @Inject
  GithubService githubService;
  static private String ARG_MODE = "mode";
  static public SimpleRecyclerViewAdapter latestAdapter;

  public static ContactListFragment newInstance(int mode) {

    ContactListFragment contactListFragment = new ContactListFragment();
    Bundle b = new Bundle();
    b.putInt(ARG_MODE, mode);
    contactListFragment.setArguments(b);
    return contactListFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final RecyclerView recyclerView = (RecyclerView) inflater.inflate(
        R.layout.fragment_contact_list, container, false);

    recyclerView.setHasFixedSize(true);
    LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
    linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(linearLayout);
    ((GitHubUsersApp) getActivity().getApplication()).getComponent().inject(this);

    githubService.getUsers().enqueue(new Callback<List<User>>() {
      @Override
      public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        int mode = getArguments().getInt(ARG_MODE);
        if (mode == 1) {
          recyclerView.setAdapter(new SimpleRecyclerViewAdapter(response.body(), getActivity(), mode));
        } else {
          latestAdapter = new SimpleRecyclerViewAdapter(getLatest(), getActivity(), mode);
          recyclerView.setAdapter(latestAdapter);
        }
      }

      @Override
      public void onFailure(Call<List<User>> call, Throwable t) {
        Toast.makeText(getActivity(), "Somthing is wrong..",
            Toast.LENGTH_LONG).show();
      }
    });

    return recyclerView;
  }

  private ArrayList<User> getLatest() {
    ArrayList<User> users = new ArrayList<>();
    return users;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}