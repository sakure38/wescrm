package com.we.scrm.common.freemarker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
*@author QiCong
*@version 2021-04-17
*Freemarker 自定义标签
**/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface FreemarkerTag {
	String value() default "";
}

