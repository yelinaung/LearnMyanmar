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

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import com.yelinaung.learnmyanmar.app.db.CategoryDao;
import com.yelinaung.learnmyanmar.app.db.WordDao;
import com.yelinaung.learnmyanmar.app.utils.SharePref;

/**
 * Created by Ye Lin Aung on 14/05/05.
 */

public abstract class BaseActivity extends ActionBarActivity {

  protected WordDao mWordDao;
  protected CategoryDao mCategoryDao;
  protected SharePref sharePref;
  protected ActionBar actionBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    actionBar = getSupportActionBar();
    sharePref = SharePref.getInstance(BaseActivity.this);
    mCategoryDao = new CategoryDao(BaseActivity.this);
    mWordDao = new WordDao(BaseActivity.this);
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onResume() {
    super.onResume();
  }
}
