package org.example;

import java.lang.reflect.Method;

public class Caller {

    public static void main(String[] args) throws Exception {
        AnnotatedClass annotatedClass = new AnnotatedClass();

        // Получаем все методы класса
        Method[] methods = annotatedClass.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                Repeat repeatAnnotation = method.getAnnotation(Repeat.class);
                int repeatCount = repeatAnnotation.value();

                // Устанавливаем доступ к приватному методу
                method.setAccessible(true);

                for (int i = 0; i < repeatCount; i++) {
                    method.invoke(annotatedClass);
                }
            }
        }
    }
}
