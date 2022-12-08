package edu.upc.eetac.dsa.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Method[] methods = theClass.getDeclaredMethods();

        String[] sMethods = new String[methods.length];
        int i=0;

        for (Method m: methods) sMethods[i++]=m.getName().substring(3);

        return sMethods;

    }


    public static void setter(Object object, String property, Object value) {
        System.out.println("Empieza el setter");
        Class theClass = object.getClass();
        Method[] methods = theClass.getDeclaredMethods();
        try {
            for (Method m: methods) { //Buscamos todos los metodos de la classe (getter, setter, etc)
                if (m.getName().equals("set" + property.substring(0, 1).toUpperCase() + property.substring(1))) {
                    try {
                        m.invoke(object, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Set " + m.getName() + ": " + value);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getter(Object object, String property) {
        System.out.println("Empieza el getter");
        Object value = null;
        Class clase = object.getClass();
        Method[] methods = clase.getDeclaredMethods();
        for (Method m: methods) {
            if (m.getName().equals("get"+property.substring(0,1).toUpperCase()+property.substring(1))){
                try {
                    value=m.invoke(object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Trobat " + m.getName());
                return value;
            }

        }

        return null;
    }
}
