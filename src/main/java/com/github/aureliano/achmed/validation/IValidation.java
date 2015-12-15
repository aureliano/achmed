package com.github.aureliano.achmed.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public interface IValidation {

	public abstract Set<ConstraintViolation> validate(Object object, Method method, Annotation annotation);
}