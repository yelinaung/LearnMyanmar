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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.yelinaung.learnmyanmar.app.R;
import com.yelinaung.learnmyanmar.app.db.Category;
import java.util.ArrayList;

import static com.yelinaung.learnmyanmar.app.utils.LogUtils.LOGD;
import static com.yelinaung.learnmyanmar.app.utils.LogUtils.makeLogTag;

/**
 * Created by Ye Lin Aung on 14/05/03.
 */
public class HomeFragment extends BaseFragment {

  ArrayList<String> mList = new ArrayList<String>();

  @InjectView(R.id.listview) ListView mListView;

  private static String TAG = makeLogTag(HomeFragment.class);

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

    LOGD(TAG, "All words ➞ " + mWordDao.getAll().size());

    CategoryAdapter mAdapter = new CategoryAdapter(mContext, R.layout.row_category,
        (ArrayList<Category>) mCategoryDao.getAllNames());
    mListView.setAdapter(mAdapter);

    return rootView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public class CategoryAdapter extends ArrayAdapter<Category> {
    public CategoryAdapter(Context context, int resource, ArrayList<Category> objects) {
      super(context, resource, objects);
    }
  }
}