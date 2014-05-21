package com.yelinaung.learnmyanmar.app.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.yelinaung.learnmyanmar.app.R;

import static com.yelinaung.learnmyanmar.app.utils.LogUtils.makeLogTag;

public class WordActivity extends BaseActivity {

  private static String TAG = makeLogTag(WordActivity.class);
  private String categoryId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_word);

    overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

    actionBar.setHomeButtonEnabled(true);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    if (getIntent().getExtras() != null) {
      categoryId = getIntent().getExtras().getString("mCategoryId");
      actionBar.setTitle(getIntent().getExtras().getString("mCategoryName"));
    }

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, WordFragment.newInstance(categoryId))
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.word, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    switch (id) {
      case android.R.id.home:
        onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void finish() {
    super.finish();
    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
  }

  @Override
  protected void onPause() {
    super.onPause();
    //closing transition animations
    overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
  }
}
