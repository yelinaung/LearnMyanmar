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

package com.yelinaung.learnmyanmar.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Ye Lin Aung on 14/05/03.
 */
public class HomeFragment extends Fragment {

  ActionBar actionBar;

  public HomeFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_home, container, false);

    actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
    actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window w = getActivity().getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
      w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    return rootView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    actionBar = ((ActionBarActivity) activity).getSupportActionBar();
  }
}
