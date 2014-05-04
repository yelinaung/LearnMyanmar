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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import com.yelinaung.learnmyanmar.app.db.CategoryDao;
import com.yelinaung.learnmyanmar.app.db.WordDao;

/**
 * Created by Ye Lin Aung on 14/05/04.
 */
public abstract class BaseFragment extends Fragment {

  ActionBar actionBar;
  CategoryDao mCategoryDao;
  WordDao mWordDao;
  Context mContext;
  Window w;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity().getApplicationContext();
    mCategoryDao = new CategoryDao(mContext);
    mWordDao = new WordDao(mContext);
    w = getActivity().getWindow();
  }

  @Override
  public void onStart() {
    super.onStart();
    //EasyTracker.getInstance().activityStart(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    //EasyTracker.getInstance().activityStop(this);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    actionBar = ((ActionBarActivity) activity).getSupportActionBar();
  }
}
