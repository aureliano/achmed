package com.github.aureliano.achmed.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.client.Model;
import com.github.aureliano.achmed.client.annotation.Max;

public class MaxValidatorTest {

	private MaxValidator validator;
	
	public MaxValidatorTest() {
		this.validator = new MaxValidator();
	}
	
	@Test
	public void testValidateStringWithError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("123456");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Max.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected a maximum value of 5 for field id but got 6.", constraint.getMessage());
		Assert.assertEquals(Max.class, constraint.getValidator());
	}
	
	@Test
	public void testValidateCollectionWithError() throws SecurityException, NoSuchMethodException {
		Model configuration = new Model()
			.addException("1")
			.addException("2");
		Method method = configuration.getClass().getMethod("getExceptions", new Class[] {});
		Annotation annotation = method.getAnnotation(Max.class);
		 
		ConstraintViolation constraint = this.validator.validate(configuration, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected a maximum value of 1 for field exceptions but got 2.", constraint.getMessage());
		Assert.assertEquals(Max.class, constraint.getValidator());
	}
	
	@Test
	public void testValidate() throws SecurityException, NoSuchMethodException {
		Model configuration = new Model().withId("12345")
				.addException("1");
		Method method = configuration.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Max.class);
		 
		Set<ConstraintViolation> res = this.validator.validate(configuration, method, annotation);
		Assert.assertTrue(res.isEmpty());
		
		method = configuration.getClass().getMethod("getExceptions", new Class[] {});
		annotation = method.getAnnotation(Max.class);
		 
		res = this.validator.validate(configuration, method, annotation);
		Assert.assertTrue(res.isEmpty());
	}
}