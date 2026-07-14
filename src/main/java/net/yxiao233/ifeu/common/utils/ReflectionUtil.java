package net.yxiao233.ifeu.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    @SuppressWarnings("unchecked")
    public static <T, C> T getPrivateField(String memberName, Class<T> typeClass, Object targetObject, Class<C> targetClass){
        try {
            if(!targetClass.isAssignableFrom(targetObject.getClass())){
                return null;
            }
            Field field = targetClass.getDeclaredField(memberName);
            field.setAccessible(true);
            Object object = field.get(targetObject);
            if(object == null){
                return null;
            }
            if(typeClass.isAssignableFrom(object.getClass())){
                return (T) object;
            }else{
                return null;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, C> void setPrivateField(String memberName, Class<T> typeClass, Object targetObject, Class<C> targetClass, T newValue){
        try {
            if(!targetClass.isAssignableFrom(targetObject.getClass())){
                return;
            }
            Field field = targetClass.getDeclaredField(memberName);
            field.setAccessible(true);
            Object object = field.get(targetObject);
            if(object == null || typeClass.isAssignableFrom(object.getClass())){
                field.set(targetObject,newValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <C> Method getPrivateMethod(String methodName, Class<?>[] parameterTypeClasses, Class<C> targetClass){
        try {
            Method method = targetClass.getDeclaredMethod(methodName, parameterTypeClasses);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <C, R> R runPrivateMethod(String methodName, Class<?>[] parameterTypeClasses, C targetObject, Class<C> targetClass, Object[] methodParameterValues, Class<R> returnTypeClass){
        Method method = getPrivateMethod(methodName, parameterTypeClasses, targetClass);
        try {
            Object invoke = method.invoke(targetObject, methodParameterValues);
            if(returnTypeClass.isAssignableFrom(invoke.getClass())){
                return (R) invoke;
            }else{
                return null;
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
