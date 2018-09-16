
package io.github.mohamedisoliman.serverstring.entity;

import com.google.gson.annotations.SerializedName;


public class GlossEntry {

    @SerializedName("Abbrev")
    private String abbrev;
    @SerializedName("Acronym")
    private String acronym;
    @SerializedName("GlossDef")
    private GlossDef glossDef;
    @SerializedName("GlossSee")
    private String glossSee;
    @SerializedName("GlossTerm")
    private String glossTerm;
    @SerializedName("ID")
    private String iD;
    @SerializedName("SortAs")
    private String sortAs;

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public GlossDef getGlossDef() {
        return glossDef;
    }

    public void setGlossDef(GlossDef glossDef) {
        this.glossDef = glossDef;
    }

    public String getGlossSee() {
        return glossSee;
    }

    public void setGlossSee(String glossSee) {
        this.glossSee = glossSee;
    }

    public String getGlossTerm() {
        return glossTerm;
    }

    public void setGlossTerm(String glossTerm) {
        this.glossTerm = glossTerm;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getSortAs() {
        return sortAs;
    }

    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }

}
