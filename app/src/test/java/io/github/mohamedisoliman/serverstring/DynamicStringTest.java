package io.github.mohamedisoliman.serverstring;

import com.google.gson.annotations.SerializedName;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import io.github.mohamedisoliman.serverstring.entity.ExamModel;

/**
 * Created by Mohamed Ibrahim on 9/24/18.
 */
public class DynamicStringTest {

    @Test
    public void testIsAnnotationIsPresent() {
        ExamModel examModel = EntityFactory.INSTANCE.getSampleModel();
        Field[] declaredFields = examModel.getClass().getDeclaredFields();

        boolean annotationPresent = false;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            annotationPresent = field.isAnnotationPresent(SerializedName.class);
        }
        Assert.assertEquals(true, annotationPresent);

    }

    @Test
    public void testDynamicBinding() {
        Presenter presenter = new Presenter();
        Assert.assertEquals(presenter.title, "S");
//        Assert.assertEquals(presenter.description, "Standard Generalized Markup Language");
//        Assert.assertEquals(presenter.para, "A meta-markup language, used to create markup languages such as DocBook.");
    }

    @Test
    public void testInitDictionary() {
        Presenter presenter = new Presenter();
        DynamicString.Companion.bind(presenter);

    }

}