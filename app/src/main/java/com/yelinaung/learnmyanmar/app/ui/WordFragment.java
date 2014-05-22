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
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.yelinaung.learnmyanmar.app.R;
import com.yelinaung.learnmyanmar.app.db.Word;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ye Lin Aung on 14/05/06.
 */
public class WordFragment extends BaseFragment {

  @InjectView(R.id.list_view) ListView mListView;

  public WordFragment() {
  }

  public static WordFragment newInstance(String mCategoryId) {
    WordFragment f = new WordFragment();
    Bundle bdl = new Bundle(1);
    bdl.putString("mCategoryId", mCategoryId);
    f.setArguments(bdl);
    return f;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_word, container, false);

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

    String mCategoryId = getArguments().getString("mCategoryId");

    mListView.setAdapter(
        new WordAdapter(mContext, (ArrayList<Word>) mWordDao.getWordsByCategoryId(mCategoryId)));

    return rootView;
  }

  public class WordAdapter extends ArrayAdapter<Word> implements TextToSpeech.OnInitListener {

    private ArrayList<Word> mWords = new ArrayList<Word>();
    private TextToSpeech tts;

    public WordAdapter(Context context, ArrayList<Word> objects) {
      super(context, R.layout.row_word, objects);
      this.mWords = objects;
      this.tts = new TextToSpeech(context, this);
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
      final ViewHolder holder;
      View vi = convertView;
      if (vi == null || vi.getTag() == null) {
        LayoutInflater inflater =
            (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi = inflater.inflate(R.layout.row_word, null);
        holder = new ViewHolder(vi);
        assert vi != null;
        vi.setTag(holder);
      } else {
        holder = (ViewHolder) vi.getTag();
      }

      holder.mEngWord.setText(mWords.get(position).enWord);
      holder.mPronunciation.setText(mWords.get(position).mPronunciation);
      holder.mMMWord.setText(mWords.get(position).mmWord);
      holder.mTTS.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          // TODO make it configurable ?
          tts.setLanguage(Locale.US);
          tts.speak(mWords.get(position).mPronunciation, TextToSpeech.QUEUE_FLUSH, null);
        }
      });

      return vi;
    }

    @Override public void onInit(int status) {
    }

    class ViewHolder {
      @InjectView(R.id.eng_word) TextView mEngWord;
      @InjectView(R.id.pronunciation) TextView mPronunciation;
      @InjectView(R.id.mm_word) TextView mMMWord;
      @InjectView(R.id.text_to_speech) ImageButton mTTS;

      ViewHolder(View view) {
        ButterKnife.inject(this, view);
      }
    }
  }
}
