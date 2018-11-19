package io.github.mohamedisoliman.serverstring;

import io.github.mohamedisoliman.server_string_annotations.ServerString;

/**
 * Created by Mohamed Ibrahim on 9/25/18.
 */
public class Presenter {

    @ServerString("title")
    public String title;

    @ServerString("para")
    public String para;

    @ServerString("GlossTerm")
    public String description;

    public Presenter() {
    }


}
