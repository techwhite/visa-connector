package com.visa.connector.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
Annotation is used to over a method. @Debug let framework know that the method has some logic needed to be processed
and the logic is defined in the DebugInterceptor
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Debug {
}
