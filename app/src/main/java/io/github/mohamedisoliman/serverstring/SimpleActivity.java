package io.github.mohamedisoliman.serverstring;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import io.github.mohamedisoliman.binder.ServerString;
import io.github.mohamedisoliman.server_string_annotations.DynamicString;

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
public class SimpleActivity extends AppCompatActivity {

  @DynamicString(serverKey = "_glossary_title", viewId = R.id.text_2)
  TextView textView1;
  @DynamicString(serverKey = "_glossary_GlossDiv_GlossList_GlossEntry_SortAs", viewId = R.id.text_2)
  TextView textView2;
  @DynamicString(serverKey = "_glossary_GlossDiv_title", viewId = R.id.text_3)
  TextView textView3;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ServerString.bind(this);
  }
}
