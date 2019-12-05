package com.MohamedTaha.Imagine.Quran.informationInrto;

import java.lang.reflect.Field;

/**
 * Created by ManasatPC on 04/01/18.
 */

public class ReflectUtil {
    ReflectUtil() {
    }

    /** Returns the value of the given private field from the source object **/
    static Object getPrivateField(Object source, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        final Field objectField = source.getClass().getDeclaredField(fieldName);
        objectField.setAccessible(true);
        return objectField.get(source);
    }
}
