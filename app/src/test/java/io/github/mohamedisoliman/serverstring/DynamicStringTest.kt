package io.github.mohamedisoliman.serverstring

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.github.mohamedisoliman.binder.ServerString
import org.junit.Test

/**
 * Created by Mohamed Ibrahim on 9/24/18.
 */
class DynamicStringTest {

  @Test
  fun testIsAnnotationIsPresent() {
//    val examModel = EntityFactory.getSampleModel()
//    val declaredFields = examModel.javaClass.declaredFields
//
//    var annotationPresent = false
//    for (field in declaredFields) {
//      field.isAccessible = true
//      annotationPresent = field.isAnnotationPresent(SerializedName::class.java)
//    }
//    Assert.assertEquals(true, annotationPresent)

  }

  @Test
  fun testDynamicBinding() {
//    val presenter = Presenter()
//    Assert.assertEquals(presenter.title, "S")
    //        Assert.assertEquals(presenter.description, "Standard Generalized Markup Language");
    //        Assert.assertEquals(presenter.para, "A meta-markup language, used to create markup languages such as DocBook.");
  }

  @Test
  fun testInitDictionary() {
//    val presenter = Presenter()
//    DynamicString.bind(presenter)

  }

  @Test
  fun testFlattenJson() {
    val hashMap = HashMap<String, String>()
    ServerString.getInstance()
        .flattenJson(
            Gson().fromJson(JsonSample.jsonSource, JsonObject::class.java), hashMap
        )
    println(Gson().toJson(hashMap))
  }

}