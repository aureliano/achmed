package com.github.aureliano.achmed.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.aureliano.achmed.client.validation.MinValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Constraint(validatedBy = MinValidator.class)
public @interface Min {

	public abstract String message() default "Expected a minimum value of #{0} for field #{1} but got #{2}.";
	
	public abstract int value();
}