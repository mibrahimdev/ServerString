package io.github.mohamedisoliman.serverstring

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.github.mohamedisoliman.server_string_annotations.ServerString
import java.util.HashMap

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
class DynamicString private constructor(private val target: Any) {

  companion object {
    private val gson = Gson()
    private val sampleJson = JsonSample.jsonSource //testing
    private val serverDictionary: HashMap<String, String> = HashMap()

    @JvmStatic
    fun bind(target: Any) {
      //1- server dictionary
      flattenJson(sampleJson)
      //dictionary for Activity fields
      val fields = target.javaClass.fields
      fields.forEach {
        it.isAccessible = true
        if (it.type.isAssignableFrom(String::class.java)
            && it.isAnnotationPresent(ServerString::class.java)
        ) {
          val annotation = it.getAnnotation(ServerString::class.java)
          val stringValue = serverDictionary[annotation.value]
          it.set(target, stringValue)
        }
      }

    }

    private fun flattenJson(json: String) {
      val fromJson = gson.fromJson(json, JsonObject::class.java)
      flattenJson(fromJson, serverDictionary)
    }

    private fun flattenJson(
      root: JsonObject,
      dictionary: HashMap<String, String>,
      prefix: String = ""
    ) {
      root.entrySet()
          .forEach { entry ->
            val key = entry.key
            val value = entry.value
            when {
              value is JsonObject -> flattenJson(value, dictionary, "${prefix}_$key")
              value is JsonArray -> flattenJsonArray(value, dictionary)
              value is JsonPrimitive && value.isString ->
                addToDictionary(dictionary, "${prefix}_$key", value)
              else -> return@forEach
            }
          }
    }

    private fun addToDictionary(
      dictionary: HashMap<String, String>,
      key: String,
      value: JsonPrimitive
    ) {
      dictionary += key to value.asString
    }

    private fun flattenJsonArray(
      jsonArray: JsonArray,
      dictionary: HashMap<String, String>,
      prefix: String = ""
    ) {
      jsonArray.forEach {
        if (it is JsonObject) flattenJson(it, dictionary, prefix)
        else if (it is JsonArray) flattenJsonArray(it, dictionary)
      }
    }

  }
}
