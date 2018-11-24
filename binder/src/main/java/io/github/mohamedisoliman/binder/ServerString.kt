package io.github.mohamedisoliman.binder

import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.lang.reflect.InvocationTargetException
import java.util.HashMap

/**
 * Created by Mohamed Ibrahim on 11/23/18.
 */
class ServerString private constructor() {

  private lateinit var json: String

  fun setJson(json: String) {
    INSTANCE?.json = json
    flattenJson(json)
  }

  companion object {
    var INSTANCE: ServerString? = null
    const val suffix: String = "_ServerStringBinding"
    private val gson = Gson()
    private val serverDictionary: HashMap<String, String> = HashMap()

    @JvmStatic
    fun getStringValue(key: String) = serverDictionary[key]

    @JvmStatic
    fun getInstance(): ServerString {
      if (INSTANCE == null) {
        INSTANCE = ServerString()
      }
      return INSTANCE as ServerString
    }

    @JvmStatic
    fun <T : AppCompatActivity> bind(
      target: T
    ) {

      val targetClass = target.javaClass
      val className = targetClass.name
      try {

        val bindingClass = targetClass?.classLoader.loadClass("$className$suffix")

        val classConstructor = bindingClass.getConstructor(targetClass)
        try {
          classConstructor.newInstance(target)
        } catch (e: IllegalAccessException) {
          throw RuntimeException("Unable to invoke $classConstructor", e)
        } catch (e: InstantiationException) {
          throw RuntimeException("Unable to invoke $classConstructor", e)
        } catch (e: InvocationTargetException) {
          val cause = e.cause
          if (cause is RuntimeException) {
            throw cause
          }
          if (cause is Error) {
            throw cause
          }
          throw RuntimeException("Unable to create instance.", cause)
        }

      } catch (e: ClassNotFoundException) {
        throw RuntimeException("Unable to find Class for $className$suffix", e)
      } catch (e: NoSuchMethodException) {
        throw RuntimeException("Unable to find constructor for $className$suffix", e)
      }

    }

  }

  fun flattenJson(json: String) {
    val fromJson = gson.fromJson(json, JsonObject::class.java)
    serverDictionary.clear()
    flattenJson(fromJson, serverDictionary)
  }

  public fun flattenJson(
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
