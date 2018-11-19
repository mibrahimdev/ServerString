package io.github.mohamedisoliman.serverstring;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.github.mohamedisoliman.server_string_annotations.ServerString;

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
public class SimpleActivity extends AppCompatActivity {

    @ServerString("title")
    public String title;

    @ServerString("para")
    public String para;

    @ServerString("GlossTerm")
    public String description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DynamicString.Companion.bind(this);

        TextView text1 = findViewById(R.id.text_1);
        text1.setText(title);

        TextView text2 = findViewById(R.id.text_2);
        text2.setText(para);

        TextView text3 = findViewById(R.id.text_3);
        text3.setText(description);
    }
}
