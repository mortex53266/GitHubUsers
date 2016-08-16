package com.example.mortx1.githubusers.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mortx1.githubusers.R;
import com.example.mortx1.githubusers.data.api.models.User;
import com.example.mortx1.githubusers.ui.ContactDetailActivity;
import com.example.mortx1.githubusers.ui.fragments.ContactListFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleRecyclerViewAdapter
    extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

  private final TypedValue mTypedValue = new TypedValue();
  private int mBackground;
  private Context context;
  public List<User> contactList;
  private int mode;

  public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;

    @BindView(R.id.avatar)
    ImageView mImageView;

    @BindView(android.R.id.text1)
    TextView mTextView;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      mView = view;
    }
  }

  public SimpleRecyclerViewAdapter(List<User> user, Context context, int mode) {

    this.mode = mode;
    this.contactList = user;
    this.context = context;
    mBackground = mTypedValue.resourceId;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    view.setBackgroundResource(mBackground);
    return new ViewHolder(view);
  }
;
  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {

    final User ci = contactList.get(position);
    holder.mTextView.setText(ci.login);
    Picasso.with(context).load(ci.avatar_url).into(holder.mImageView);
    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mode == 1) {
          SharedPreferences mSettings = context.getApplicationContext().getSharedPreferences("latest", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = mSettings.edit();
          editor.putString("username", ci.login).commit();
          editor.putString("avatar_url", ci.avatar_url).commit();
          SimpleRecyclerViewAdapter adapter = ContactListFragment.latestAdapter;
          adapter.contactList.add(adapter.contactList.size(),
              new User(mSettings.getString("username", "missing"), mSettings.getString("avatar_url", "missing")));
          if (adapter.contactList.size() >= 6)
            adapter.contactList.remove(0);
          adapter.notifyDataSetChanged();
        }
        Context context = v.getContext();
        Intent intent = new Intent(context, ContactDetailActivity.class);
        intent.putExtra("login", ci.login);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return contactList.size();
  }
}