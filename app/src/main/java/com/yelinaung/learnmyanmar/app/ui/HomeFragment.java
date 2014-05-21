/*
 * Copyright 2014 Ye Lin Aung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yelinaung.learnmyanmar.app.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.yelinaung.learnmyanmar.app.R;
import com.yelinaung.learnmyanmar.app.db.Category;
import java.util.ArrayList;

/**
 * Created by Ye Lin Aung on 14/05/03.
 */
public class HomeFragment extends BaseFragment {

  @InjectView(R.id.list_view) ListView mListView;
  private ArrayList<Category> mCategories;

  public HomeFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_home, container, false);

    ButterKnife.inject(this, rootView);

    actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      w = getActivity().getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
      w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    mCategories = (ArrayList<Category>) mCategoryDao.getAll();

    CategoryAdapter mAdapter = new CategoryAdapter(mContext, R.layout.row_category, mCategories);
    mListView.setAdapter(mAdapter);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(mContext, WordActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("mCategoryId", mCategories.get(position).category_id);
        i.putExtra("mCategoryName", mCategories.get(position).name);

        mContext.startActivity(i);
      }
    });

    return rootView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public class CategoryAdapter extends ArrayAdapter<Category> {

    private ArrayList<Category> mCategories = new ArrayList<Category>();

    public CategoryAdapter(Context context, int resource, ArrayList<Category> objects) {
      super(context, resource, objects);
      this.mCategories = objects;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      final ViewHolder holder;
      View vi = convertView;
      if (vi == null || vi.getTag() == null) {
        LayoutInflater inflater =
            (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi = inflater.inflate(R.layout.row_category, null);
        holder = new ViewHolder(vi);
        assert vi != null;
        vi.setTag(holder);
      } else {
        holder = (ViewHolder) vi.getTag();
      }

      holder.mCategoryName.setText(null);
      holder.mCategoryName.setText(mCategories.get(position).name);

      return vi;
    }

    class ViewHolder {
      @InjectView(R.id.category_text) TextView mCategoryName;

      ViewHolder(View view) {
        ButterKnife.inject(this, view);
      }
    }
  }
}
