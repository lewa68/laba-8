package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotatedClass {

    public void publicMethod() {
        System.out.println("Public method called");
    }

    @Repeat(3)
    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    @Repeat(2)
    private void privateMethod() {
        System.out.println("Private method called");
    }
}
