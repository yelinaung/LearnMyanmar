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
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import com.yelinaung.learnmyanmar.app.R;
import com.yelinaung.learnmyanmar.app.db.Category;
import com.yelinaung.learnmyanmar.app.db.CategoryDao;
import com.yelinaung.learnmyanmar.app.db.Word;
import com.yelinaung.learnmyanmar.app.db.WordDao;
import com.yelinaung.learnmyanmar.app.utils.SharePref;

public class HomeActivity extends ActionBarActivity {

  WordDao mWordDao;
  CategoryDao mCategoryDao;
  SharePref sharePref;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
    setContentView(R.layout.activity_home);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new HomeFragment())
          .commit();
    }

    sharePref = SharePref.getInstance(HomeActivity.this);

    mCategoryDao = new CategoryDao(HomeActivity.this);
    mWordDao = new WordDao(HomeActivity.this);

    if (sharePref.isFirstTime()) {
      for (int i = 0; i < getResources().getStringArray(R.array.categories).length; i++) {
        Category c = new Category();
        c.id = i;
        c.name = getResources().getStringArray(R.array.categories)[i];
        mCategoryDao.create(c);
      }

      for (int i = 0; i < getResources().getStringArray(R.array.categoryId).length; i++) {
        Word w = new Word();
        w.categoryId = getResources().getStringArray(R.array.categoryId)[i];
        w.enText = getResources().getStringArray(R.array.enText)[i];
        w.mmText = getResources().getStringArray(R.array.mmText)[i];
        mWordDao.create(w);
      }

      sharePref.noLongerFirstTime();
    }
  }
}
