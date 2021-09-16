package com.songyuwong.sqldbmetadatamanager.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Doc {

    private static ClassLoader systemClassLoader;

    public static void main(String[] args) {
        Method[] methods = Object.class.getMethods();
        systemClassLoader = ClassLoader.getSystemClassLoader();
        try {
            Class<?> aClass = systemClassLoader.loadClass("com.jhids4se.core.controller.CommonServerController");
            Arrays
                    .stream(aClass.getMethods())
                    .filter(v -> Arrays
                            .stream(methods)
                            .noneMatch(v1 -> v1.getName().equals(v.getName()) && !v1.getName().contains("Mapping")))
                    .forEach(v -> {
                        System.out.println("======= methods =======\n".concat(v.getName()));
                        dealMethodParametersInfo(v.getParameterTypes());
                        dealMethodResultInfo(v.getGenericReturnType());
                    });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Field> getObjectFields(Class<?> parameterType, List<Field> fields) {
        if (Object.class.getName().equals(parameterType.getName())) {
            return fields;
        }
        if (Object.class.getName().equals(parameterType.getSuperclass().getName())) {
            fields.addAll(Arrays.asList(parameterType.getDeclaredFields()));
        } else {
            fields.addAll(getObjectFields(parameterType.getSuperclass(), fields));
        }
        return fields;
    }

    private static void dealMethodParametersInfo(Class<?>[] parameterTypes){
        for (Class<?> parameterType : parameterTypes) {
            System.out.println("======= parameters\n\t\t".concat(parameterType.getName()));
            List<Field> fields = new LinkedList<>();
            List<Field> parameterFields = getObjectFields(parameterType, fields);
            parameterFields.forEach(v2 -> System.out.println("\t\t\t" + v2.getName()));
        }
    }

    private static void dealMethodResultInfo(Type genericReturnType){
        String className = "";
        String typeName = genericReturnType.getTypeName();
        if (typeName.contains("<")) {
            className = typeName.substring(typeName.indexOf("<") + 1, typeName.indexOf(">"));
        } else {
            className = typeName;
        }
        System.out.println("======= return type\n\t\t".concat(className));
        try {
            Class<?> resultTypeClass = systemClassLoader.loadClass(className);
            List<Field> fields = new LinkedList<>();
            List<Field> resultTypeFields = getObjectFields(resultTypeClass, fields);
            resultTypeFields.forEach(field -> System.out.println("\t\t\t" + field.getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
