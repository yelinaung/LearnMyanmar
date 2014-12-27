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

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ye Lin Aung on 14/05/04.
 */
@DatabaseTable(tableName = "Category")
public class Category implements Parcelable {
  @DatabaseField(generatedId = true) public int id;
  @DatabaseField public String category_id;
  @DatabaseField public String name;

  public Category() {
  }

  public Category(String category_id, String name) {
    this.category_id = category_id;
    this.name = name;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.category_id);
    dest.writeString(this.name);
  }

  private Category(Parcel in) {
    this.id = in.readInt();
    this.category_id = in.readString();
    this.name = in.readString();
  }

  public static Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
    public Category createFromParcel(Parcel source) {
      return new Category(source);
    }

    public Category[] newArray(int size) {
      return new Category[size];
    }
  };
}
