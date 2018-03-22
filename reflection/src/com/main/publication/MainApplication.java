package com.main.publication;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainApplication {

    public static String CLASS = "reflection.in.clazz.RegularPolygon";
    
    public static void main(String[] args) {

        try {
            Class clazz = Class.forName(CLASS);
            
            System.out.println(clazz.getName());
            System.out.println(clazz.getSuperclass().getName());
            Class[] ifaces = clazz.getInterfaces();
            for (Class c : ifaces) {
                System.out.println(c.getName());
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getType().getName() + " " + field.getName());
            }
            
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                System.out.println("Имя: " + method.getName() +" Возвращаемый тип: " +
                        method.getReturnType().getName());
                Class[] paramTypes = method.getParameterTypes();
                if (paramTypes.length < 1)
                    continue;
                System.out.print("Типы параметров: ");
                for (Class paramType : paramTypes)
                    System.out.print(" " + paramType.getName());
                System.out.println();
            }
            
            for (Annotation a : clazz.getDeclaredAnnotations()) {
                System.out.println(a.annotationType().getName());
            }
            
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                Class[] paramTypes = constructor.getParameterTypes();
                for (Class paramType : paramTypes) {
                    System.out.print(paramType.getName() + " ");
                }
                System.out.println();
            }
            Constructor ctor = clazz.getConstructor(clazz.getDeclaredConstructors()[0].getParameterTypes());
            Object obj = ctor.newInstance(0, 0, 3, 10);
                        
            Method toStringMethod = clazz.getMethod("toString");
            System.out.println(toStringMethod.invoke(obj));
            
            Method areaMethod = clazz.getMethod("area");
            double area = (double) areaMethod.invoke(obj);
            System.out.println("area = " + area);
            
            Field numOfVerField = clazz.getDeclaredField("numOfVertices");
            numOfVerField.setAccessible(true);
            numOfVerField.setInt(obj, 4);
            
            toStringMethod = clazz.getMethod("toString");
            System.out.println(toStringMethod.invoke(obj));
            
            areaMethod = clazz.getMethod("area");
            area = (double) areaMethod.invoke(obj);
            System.out.println("area = " + area);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
