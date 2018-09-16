
package io.github.mohamedisoliman.serverstring.entity;

import com.google.gson.annotations.SerializedName;

public class Glossary {

    @SerializedName("GlossDiv")
    private GlossDiv glossDiv;
    @SerializedName("title")
    private String title;

    public GlossDiv getGlossDiv() {
        return glossDiv;
    }

    public void setGlossDiv(GlossDiv glossDiv) {
        this.glossDiv = glossDiv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
