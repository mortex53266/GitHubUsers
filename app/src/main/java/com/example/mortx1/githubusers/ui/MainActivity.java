package com.example.mortx1.githubusers.ui;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mortx1.githubusers.R;
import com.example.mortx1.githubusers.ui.fragments.ContactListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.fab)
  FloatingActionButton fab;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.drawer_layout)
  DrawerLayout drawerlayout;

  @BindView(R.id.nav_view)
  NavigationView navigationView;

  @BindView(R.id.viewpager)
  ViewPager viewPager;

  @BindDrawable(R.drawable.ic_menu)
  Drawable ic_menu;

  @BindView(R.id.tabs)
  TabLayout tabLayout;

  @OnClick(R.id.fab)
  public void onClick(View view) {
    Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_SHORT)
        .setAction("Action", null).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    final ActionBar actionBar = getSupportActionBar();
    actionBar.setHomeAsUpIndicator(ic_menu);
    actionBar.setDisplayHomeAsUpEnabled(true);
    if (navigationView != null) {
      setupDrawerContent(navigationView);
    }

    if (viewPager != null) {
      setupViewPager(viewPager);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.sample_actions, menu);
    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    switch (AppCompatDelegate.getDefaultNightMode()) {
      case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
        menu.findItem(R.id.menu_night_mode_system).setChecked(true);
        break;
      case AppCompatDelegate.MODE_NIGHT_AUTO:
        menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
        break;
      case AppCompatDelegate.MODE_NIGHT_YES:
        menu.findItem(R.id.menu_night_mode_night).setChecked(true);
        break;
      case AppCompatDelegate.MODE_NIGHT_NO:
        menu.findItem(R.id.menu_night_mode_day).setChecked(true);
        break;
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerlayout.openDrawer(GravityCompat.START);
        return true;
      case R.id.menu_night_mode_system:
        setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        break;
      case R.id.menu_night_mode_day:
        setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        break;
      case R.id.menu_night_mode_night:
        setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        break;
      case R.id.menu_night_mode_auto:
        setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
    AppCompatDelegate.setDefaultNightMode(nightMode);

    if (Build.VERSION.SDK_INT >= 11) {
      recreate();
    }
  }

  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    adapter.addFragment(new ContactListFragment(), "GitHub Users");
    viewPager.setAdapter(adapter);
  }

  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            drawerlayout.closeDrawers();
            return true;
          }
        });
  }

  static class Adapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public Adapter(FragmentManager fm) {
      super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
      mFragments.add(fragment);
      mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragments.get(position);
    }

    @Override
    public int getCount() {
      return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitles.get(position);
    }
  }
}