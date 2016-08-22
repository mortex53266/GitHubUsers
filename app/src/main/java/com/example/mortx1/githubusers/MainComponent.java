package com.example.mortx1.githubusers;

import com.example.mortx1.githubusers.data.DataModule;
import com.example.mortx1.githubusers.data.api.ApiModule;
import com.example.mortx1.githubusers.ui.ContactDetailActivity;
import com.example.mortx1.githubusers.ui.adapters.SimpleRecyclerViewAdapter;
import com.example.mortx1.githubusers.ui.fragments.ContactListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    ApiModule.class,
    DataModule.class
})
public interface MainComponent {

    void inject(ContactListFragment contactListFragment);

    void inject(SimpleRecyclerViewAdapter simpleRecyclerViewAdapter);

    void inject (ContactDetailActivity contactDetailActivity);

}
