package com.github.aureliano.achmed.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.aureliano.achmed.client.validation.MaxValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Constraint(validatedBy = MaxValidator.class)
public @interface Max {

	public abstract String message() default "Expected a maximum value of #{0} for field #{1} but got #{2}.";
	
	public abstract int value();
}