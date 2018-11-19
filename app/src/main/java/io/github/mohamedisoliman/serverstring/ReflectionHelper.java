package io.github.mohamedisoliman.serverstring;

import java.lang.reflect.Field;

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
public class ReflectionHelper {

    /**
     * Get the specified field on the class. If the field is not found on the class itself will recursively check
     * the superclass.
     *
     * @param clazz     the class definition containing the field
     * @param fieldName the field to locate
     * @return the field if found, otherwise an exception is thrown
     */
    private static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException nsf) {
            if (clazz.getSuperclass() != null) {
                return getField(clazz.getSuperclass(), fieldName);
            }

            throw new IllegalStateException("Could not locate field '" + fieldName + "' on class " + clazz);
        }
    }

    /**
     * Returns the value of a (nested) field on a bean.
     *
     * @param bean      the object
     * @param fieldName the field name, with "." separating nested properties
     * @return the value
     */
//    public static Object get(Object bean, String fieldName) {
//        String[] nestedFields = StringUtils.split(fieldName, ".");
//        Class<?> componentClass = bean.getClass();
//        Object value = bean;
//
//        try {
//            for (String nestedField : nestedFields) {
//                Field field = getField(componentClass, nestedField);
//                field.setAccessible(true);
//                value = field.get(value);
//                if (value != null) {
//                    componentClass = value.getClass();
//                }
//            }
//        } catch (IllegalAccessException iae) {
//            throw new IllegalStateException(iae);
//        }
//
//        return value;
//
//    }

    /**
     * Sets the value of a (nested) field on a bean.
     *
     * @param bean      the object
     * @param fieldName the field name, with "." separating nested properties
     * @param newValue  the value to set
     */
//    public static void set(Object bean, String fieldName, Object newValue) {
//        String[] nestedFields = StringUtils.split(fieldName, ".");
//        Class<?> componentClass = bean.getClass();
//        Object value = bean;
//        Field field = null;
//
//        try {
//            for (int i = 0; i < nestedFields.length; i++) {
//                String nestedField = nestedFields[i];
//                field = getField(componentClass, nestedField);
//                field.setAccessible(true);
//
//                if (i == nestedFields.length - 1) {
//                    break;
//                }
//
//                value = field.get(value);
//                if (value != null) {
//                    componentClass = value.getClass();
//                }
//            }
//
//            field.set(value, newValue);
//        } catch (IllegalAccessException iae) {
//            throw new IllegalStateException(iae);
//        }
//    }
}
