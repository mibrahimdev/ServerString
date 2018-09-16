
package io.github.mohamedisoliman.serverstring.entity;

import com.google.gson.annotations.SerializedName;


public class ExamModel {

    @SerializedName("glossary")
    private Glossary glossary;

    public Glossary getGlossary() {
        return glossary;
    }

    public void setGlossary(Glossary glossary) {
        this.glossary = glossary;
    }

}
