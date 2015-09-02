package com.vilyever.reflectkit;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * VDReflectKit
 * AndroidReflectKit <com.vilyever.reflectkit>
 * Created by vilyever on 2015/8/18.
 * Feature:
 */
public class VDReflectKit {
    private final VDReflectKit self = this;


    /* #Constructors */

    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */    
    
    /* #Public Methods */
    public static ArrayList<Field> getFields(Class<?> clazz) {
        return getFields(clazz, clazz, null);
    }

    public static ArrayList<Field> getFields(Class<?> clazz, Class<?> ancestorClazz) {
        return getFields(clazz, ancestorClazz, null);
    }

    public static ArrayList<Field> getFields(Class<?> clazz, FieldsExclusionDelegate exclusionDelegate) {
        return getFields(clazz, clazz, exclusionDelegate);
    }

    /**
     *
     * @param clazz 当前类
     * @param ancestorClazz 追溯到父类
     * @param exclusionDelegate 排除的属性字段
     * @return 属性字段集合
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

    /* #Classes */

    /* #Interfaces */
    public interface FieldsExclusionDelegate {
        boolean shouldExclude(Field field);
    }
     
    /* #Annotations @interface */    
    
    /* #Enums */
}