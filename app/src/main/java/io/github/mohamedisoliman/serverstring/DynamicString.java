package io.github.mohamedisoliman.serverstring;

import java.lang.reflect.Field;

import io.github.mohamedisoliman.server_string_annotations.ServerString;
import timber.log.Timber;

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
public class DynamicString {

    private Object object;

    private DynamicString(Object object) {
        this.object = object;
    }

    public static void bind(Object activity) {
        DynamicString dynamicString = new DynamicString(activity);
        try {
            dynamicString.bindStringsToFields();
        } catch (Exception e) {
            Timber.e(e);
        }

    }

    private void bindStringsToFields() throws IllegalAccessException {
        Field[] fields = object.getClass().getFields();
        int counter = 0;
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(String.class)) {
                ServerString annotation = field.getAnnotation(ServerString.class);
                field.set(object, annotation.value());
            }

        }
    }
}
