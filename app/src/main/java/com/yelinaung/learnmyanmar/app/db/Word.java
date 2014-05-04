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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ye Lin Aung on 14/05/04.
 */
@DatabaseTable(tableName = "Words")
public class Word {
  @DatabaseField(generatedId = true) public int id;
  @DatabaseField public String categoryId;
  @DatabaseField public String wordId;
  @DatabaseField public String mmText;
  @DatabaseField public String enText;

  public Word() {
  }

  public Word(String categoryId, String wordId, String mmText, String enText) {
    this.categoryId = categoryId;
    this.wordId = wordId;
    this.mmText = mmText;
    this.enText = enText;
  }
}
