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

//    getDeclaredMethods(): Этот метод возвращает массив объектов Method, представляющих все методы, объявленные в классе или интерфейсе.
//        isAnnotationPresent(): Этот метод проверяет, присутствует ли аннотация на методе. В данном случае аннотация Repeat проверяется на присутствие на методе.
//        getAnnotation(): Этот метод возвращает объект аннотации, если аннотация присутствует на методе. В данном случае возвращается объект аннотации Repeat.
//        setAccessible(): Этот метод устанавливает доступ к приватному методу. Если метод является приватным, этот метод позволяет вызвать его через рефлексию.
//        invoke(): Этот метод вызывает метод на объекте. В данном случае метод вызывается на объекте annotatedClass