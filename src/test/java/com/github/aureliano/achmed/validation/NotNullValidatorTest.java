package com.github.aureliano.achmed.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.client.Model;
import com.github.aureliano.achmed.client.annotation.NotNull;

public class NotNullValidatorTest {

	private NotNullValidator validator;
	
	public NotNullValidatorTest() {
		this.validator = new NotNullValidator();
	}
	
	@Test
	public void testValidateWithError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId(null);
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(NotNull.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected to find a not null value for field id.", constraint.getMessage());
		Assert.assertEquals(NotNull.class, constraint.getValidator());
	}
	
	@Test
	public void testValidate() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("custom-id");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(NotNull.class);

		Set<ConstraintViolation> res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
	}
}