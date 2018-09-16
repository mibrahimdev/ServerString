
package io.github.mohamedisoliman.serverstring.entity;

import com.google.gson.annotations.SerializedName;

public class GlossDiv {

    @SerializedName("GlossList")
    private GlossList glossList;
    @SerializedName("title")
    private String title;

    public GlossList getGlossList() {
        return glossList;
    }

    public void setGlossList(GlossList glossList) {
        this.glossList = glossList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
