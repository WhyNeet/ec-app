package dev.whyneet.ec_api.core.entities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Entity {
    public static <T> T from(Object src, Class<T> dest) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        T instance = Entity.instantiate(dest);

        for (Field field : src.getClass().getFields()) instance.getClass().getField(field.getName()).set(instance, field.get(src));

        return dest.cast(instance);
    }

    private static <T> T instantiate(Class<T> c) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return c.getDeclaredConstructor().newInstance();
    }
}
