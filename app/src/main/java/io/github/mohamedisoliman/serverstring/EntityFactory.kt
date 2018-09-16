package io.github.mohamedisoliman.serverstring

import com.google.gson.Gson
import io.github.mohamedisoliman.serverstring.entity.ExamModel

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
object EntityFactory {


    fun getSampleModel(): ExamModel {
        val gson = Gson()
        val examModel = gson.fromJson(ExamJson.jsonSource, ExamModel::class.java)
        return examModel;
    }


}
