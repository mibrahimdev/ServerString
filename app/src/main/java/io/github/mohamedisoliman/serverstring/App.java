package io.github.mohamedisoliman.serverstring;

import android.app.Application;
import io.github.mohamedisoliman.binder.ServerString;

/**
 * Created by Mohamed Ibrahim on 11/24/18.
 */
public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();
    ServerString.getInstance()
        .setJson(JsonSample.getJsonSource());
  }
}
