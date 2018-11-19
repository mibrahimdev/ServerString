package io.github.mohamedisoliman.serverstring

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import io.github.mohamedisoliman.server_string_annotations.ServerString
import timber.log.Timber
import java.util.HashMap

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
class DynamicString private constructor(private val target: Any) {
  private val examModel = EntityFactory.getSampleModel()//for testing
  private var dictionary = HashMap<String, String>()

  init {
    try {
      initDictionary(examModel.javaClass)
    } catch (e: IllegalAccessException) {
      e.printStackTrace()
    }

  }

  @Throws(IllegalAccessException::class)
  private fun bindStringsToFields() {
    val fields = target.javaClass.fields
    for (field in fields) {
      field.isAccessible = true
      if (field.type.isAssignableFrom(String::class.java) && field.isAnnotationPresent(
              ServerString::class.java
          )
      ) {

        val annotation = field.getAnnotation(ServerString::class.java)
        val stringValue = dictionary[annotation.value]
        field.set(target, stringValue)
      }
    }
  }

  @Throws(IllegalAccessException::class)
  private fun initDictionary(aClass: Class<*>) {
    val declaredFields = aClass.declaredFields
    var nestingCounter = 0
    for (field in declaredFields) {
      field.isAccessible = true
      val isStringType = field.type.isAssignableFrom(String::class.java)
      if (isStringType) {
        if (field.isAnnotationPresent(SerializedName::class.java)) {
          val annotation = field.getAnnotation(SerializedName::class.java)
          val key = annotation.value
          val value = field.get(examModel) as String
          dictionary[key] = value
        }
      } else {
        nestingCounter++
        initDictionary(field.type)
      }
      if (nestingCounter > 5) break
    }
  }

  private fun toMap(jsoObject: JsonObject): HashMap<String, Any> {
    TODO()
  }

  companion object {

    fun bind(activity: Any) {
      val dynamicString = DynamicString(activity)
      try {
        dynamicString.bindStringsToFields()
      } catch (e: Exception) {
        Timber.e(e)
      }

    }
  }
}
