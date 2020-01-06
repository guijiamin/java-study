package com.study.tinyspringmvc.annotation;

import java.lang.annotation.*;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Qualifier {
    public String value();
}
