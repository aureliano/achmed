package com.github.aureliano.achmed.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.client.Model;
import com.github.aureliano.achmed.client.annotation.NotEmpty;

public class NotEmptyValidatorTest {

	private NotEmptyValidator validator;
	
	public NotEmptyValidatorTest() {
		this.validator = new NotEmptyValidator();
	}
	
	@Test
	public void testValidateNullWithError() throws SecurityException, NoSuchMethodException {
		Model configuration = new Model().withId(null);
		Method method = configuration.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(NotEmpty.class);
		 
		ConstraintViolation constraint = this.validator.validate(configuration, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected to find a not empty text for field id.", constraint.getMessage());
		Assert.assertEquals(NotEmpty.class, constraint.getValidator());
	}
	
	@Test
	public void testValidateEmptyWithError() throws SecurityException, NoSuchMethodException {
		Model configuration = new Model().withId("");
		Method method = configuration.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(NotEmpty.class);
		 
		ConstraintViolation constraint = this.validator.validate(configuration, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected to find a not empty text for field id.", constraint.getMessage());
		Assert.assertEquals(NotEmpty.class, constraint.getValidator());
	}
	
	@Test
	public void testValidate() throws SecurityException, NoSuchMethodException {
		Model configuration = new Model().withId("custom-id");
		Method method = configuration.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(NotEmpty.class);
		
		Set<ConstraintViolation> res = this.validator.validate(configuration, method, annotation);
		Assert.assertTrue(res.isEmpty());
	}
}