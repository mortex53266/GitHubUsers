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

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactListFragment extends Fragment {

  @Inject
  GithubService githubService;

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
        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(response.body(), getActivity());
        recyclerView.setAdapter(adapter);
      }

      @Override
      public void onFailure(Call<List<User>> call, Throwable t) {
        Toast.makeText(getActivity(), "Somthing is wrong..",
            Toast.LENGTH_LONG).show();
      }
    });

    return recyclerView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}