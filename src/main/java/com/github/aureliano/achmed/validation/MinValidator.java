package com.github.aureliano.achmed.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.github.aureliano.achmed.annotation.Min;
import com.github.aureliano.achmed.helper.ReflectionHelper;
import com.github.aureliano.achmed.helper.StringHelper;

public class MinValidator implements IValidator {

	public MinValidator() {
		super();
	}

	@Override
	public Set<ConstraintViolation> validate(Object object, Method method, Annotation annotation) {
		String property = ReflectionHelper.getSimpleAccessMethodName(method);
		Object returnedValue = ReflectionHelper.callMethod(object, method.getName(), null, null);
		Set<ConstraintViolation> violations = new HashSet<ConstraintViolation>();
		
		if (returnedValue == null) {
			returnedValue = "";
		}
		
		String message = ((Min) annotation).message();
		int minSize = ((Min) annotation).value();
		int objectSize;
		
		if (Collection.class.isAssignableFrom(returnedValue.getClass())) {
			objectSize = ((Collection<?>) returnedValue).size();
		} else if (!(returnedValue instanceof String) && StringHelper.isNumeric(returnedValue.toString())) {
			Double d = Double.parseDouble(returnedValue.toString());
			objectSize = d.intValue();
		} else {
			objectSize = returnedValue.toString().length();
		}
		
		if (minSize > objectSize) {
			violations.add(new ConstraintViolation()
				.withValidator(Min.class)
				.withMessage(message
					.replaceFirst("#\\{0\\}", String.valueOf(minSize))
					.replaceFirst("#\\{1\\}", property)
					.replaceFirst("#\\{2\\}", String.valueOf(objectSize))));
		}
		
		return violations;
	}
}