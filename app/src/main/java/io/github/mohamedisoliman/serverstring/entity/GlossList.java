
package io.github.mohamedisoliman.serverstring.entity;

import com.google.gson.annotations.SerializedName;

public class GlossList {

    @SerializedName("GlossEntry")
    private GlossEntry glossEntry;

    public GlossEntry getGlossEntry() {
        return glossEntry;
    }

    public void setGlossEntry(GlossEntry glossEntry) {
        this.glossEntry = glossEntry;
    }

}
