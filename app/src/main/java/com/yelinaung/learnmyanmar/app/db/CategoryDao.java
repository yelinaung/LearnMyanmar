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

package com.yelinaung.learnmyanmar.app.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ye Lin Aung on 14/01/30.
 */
public class CategoryDao {
  private Dao<Category, Integer> mCategoryDao;
  private ConnectionSource source;

  public CategoryDao(Context ctx) {
    try {
      DbMgr dbManager = new DbMgr();
      DbHelper dbHelper = dbManager.getHelper(ctx);
      mCategoryDao = dbHelper.getCategoryDao();
      source = dbHelper.getConnectionSource();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int create(Category Category) {
    try {
      return mCategoryDao.create(Category);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public List<Category> getAll() {
    try {
      return mCategoryDao.queryForAll();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void deleteAllStories() throws SQLException {
    TableUtils.clearTable(source, Category.class);
  }
}
