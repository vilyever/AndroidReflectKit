package com.vilyever.reflectkit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * VDReflectKit
 * AndroidReflectKit <com.vilyever.reflectkit>
 * Created by vilyever on 2015/8/18.
 * Feature:
 */
public class VDReflectKit {
    private final VDReflectKit self = this;

    /* Public Methods */
    /** @see #getFields(Class, FieldsExclusionDelegate) */
    public static ArrayList<Field> getFields(Class<?> clazz) {
        return getFields(clazz, clazz, null);
    }

    /** @see #getFields(Class, FieldsExclusionDelegate) */
    public static ArrayList<Field> getFields(Class<?> clazz, Class<?> ancestorClazz) {
        return getFields(clazz, ancestorClazz, null);
    }

    /** @see #getFields(Class, FieldsExclusionDelegate) */
    public static ArrayList<Field> getFields(Class<?> clazz, FieldsExclusionDelegate exclusionDelegate) {
        return getFields(clazz, clazz, exclusionDelegate);
    }

    /**
     * 获取指定class追溯到其某一级父class排除条件以外的所有变量field
     * @param clazz 指定class
     * @param ancestorClazz 追溯某一级父class
     * @param exclusionDelegate 排除条件回调
     * @return field集合
     */
    public static ArrayList<Field> getFields(Class<?> clazz, Class<?> ancestorClazz, FieldsExclusionDelegate exclusionDelegate) {
        ArrayList<Field> fieldList = new ArrayList<Field>();

        Class<?> cls = clazz;

        while (cls != ancestorClazz.getSuperclass()) {
            Field[] fields = cls.getDeclaredFields();
            int index = 0;
            int length = fields.length;
            for (int i = 0; i < length; i++) {
                Field field = fields[i];
                if (exclusionDelegate == null
                        || !exclusionDelegate.shouldExclude(field)) {
                    fieldList.add(index++, field);
                }
            }

            cls =  cls.getSuperclass();
        }

        return fieldList;
    }

    /** @see #getMethods(Class, MethodsExclusionDelegate) */
    public static ArrayList<Method> getMethods(Class<?> clazz) {
        return getMethods(clazz, clazz, null);
    }

    /** @see #getMethods(Class, MethodsExclusionDelegate) */
    public static ArrayList<Method> getMethods(Class<?> clazz, Class<?> ancestorClazz) {
        return getMethods(clazz, ancestorClazz, null);
    }

    /** @see #getMethods(Class, MethodsExclusionDelegate) */
    public static ArrayList<Method> getMethods(Class<?> clazz, MethodsExclusionDelegate exclusionDelegate) {
        return getMethods(clazz, clazz, exclusionDelegate);
    }

    /**
     * 获取指定class追溯到其某一级父class排除条件以外的所有方法method
     * @param clazz 指定class
     * @param ancestorClazz 追溯某一级父class
     * @param exclusionDelegate 排除条件回调
     * @return method集合
     */
    public static ArrayList<Method> getMethods(Class<?> clazz, Class<?> ancestorClazz, MethodsExclusionDelegate exclusionDelegate) {
        ArrayList<Method> methodList = new ArrayList<Method>();

        Class<?> cls = clazz;

        while (cls != ancestorClazz.getSuperclass()) {
            Method[] methods = cls.getDeclaredMethods();
            int index = 0;
            int length = methods.length;
            for (int i = 0; i < length; i++) {
                Method method = methods[i];
                if (exclusionDelegate == null
                        || !exclusionDelegate.shouldExclude(method)) {
                    methodList.add(index++, method);
                }
            }

            cls =  cls.getSuperclass();
        }

        return methodList;
    }

    /* Interfaces */
    public interface FieldsExclusionDelegate {
        boolean shouldExclude(Field field);
    }

    public interface MethodsExclusionDelegate {
        boolean shouldExclude(Method method);
    }
}