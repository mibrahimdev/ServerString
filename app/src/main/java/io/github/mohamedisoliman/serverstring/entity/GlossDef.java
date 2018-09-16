
package io.github.mohamedisoliman.serverstring.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GlossDef {

    @SerializedName("GlossSeeAlso")
    private List<String> glossSeeAlso;
    @SerializedName("para")
    private String para;

    public List<String> getGlossSeeAlso() {
        return glossSeeAlso;
    }

    public void setGlossSeeAlso(List<String> glossSeeAlso) {
        this.glossSeeAlso = glossSeeAlso;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

}
