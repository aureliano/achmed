package com.github.aureliano.achmed.client.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.client.Model;
import com.github.aureliano.achmed.client.annotation.Min;

public class MinValidatorTest {

	private MinValidator validator;
	
	public MinValidatorTest() {
		this.validator = new MinValidator();
	}
	
	@Test
	public void testValidateStringWithError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Min.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected a minimum value of 3 for field id but got 0.", constraint.getMessage());
		Assert.assertEquals(Min.class, constraint.getValidator());
		
		model = new Model().withId("ok");
		
		constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected a minimum value of 3 for field id but got 2.", constraint.getMessage());
		Assert.assertEquals(Min.class, constraint.getValidator());
	}
	
	@Test
	public void testValidateCollectionWithError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("ok!");
		Method method = model.getClass().getMethod("getExceptions", new Class[] {});
		Annotation annotation = method.getAnnotation(Min.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected a minimum value of 1 for field exceptions but got 0.", constraint.getMessage());
		Assert.assertEquals(Min.class, constraint.getValidator());
	}
	
	@Test
	public void testValidate() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("ok!");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Min.class);
		 
		Set<ConstraintViolation> res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
		
		model = new Model().addException("test");
		method = model.getClass().getMethod("getExceptions", new Class[] {});
		annotation = method.getAnnotation(Min.class);
		
		res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
	}
}