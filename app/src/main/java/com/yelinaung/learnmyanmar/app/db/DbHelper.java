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
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Created by Ye Lin Aung on 14/01/30.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "lm.db";
  private static final int DATABASE_VERSION = 1; // important
  private Dao<Category, Integer> mCategoryDao = null;
  private Dao<Word, Integer> mWordDao = null;

  public DbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
    try {
      TableUtils.createTable(connectionSource, Category.class);
      TableUtils.createTable(connectionSource, Word.class);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i,
      int i2) {
    try {
      TableUtils.dropTable(connectionSource, Category.class, true);
      TableUtils.dropTable(connectionSource, Word.class, true);
      onCreate(sqLiteDatabase, connectionSource);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Dao<Category, Integer> getCategoryDao() throws SQLException {
    if (mCategoryDao == null) {
      mCategoryDao = getDao(Category.class);
    }
    return mCategoryDao;
  }

  public Dao<Word, Integer> getWordDao() throws SQLException {
    if (mWordDao == null) {
      mWordDao = getDao(Word.class);
    }
    return mWordDao;
  }

  @Override public void close() {
    super.close();
    mCategoryDao = null;
    mWordDao = null;
  }
}
