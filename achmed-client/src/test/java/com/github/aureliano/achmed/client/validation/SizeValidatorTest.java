package com.github.aureliano.achmed.client.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.client.Model;
import com.github.aureliano.achmed.client.annotation.Size;

public class SizeValidatorTest {

	private SizeValidator validator;
	
	public SizeValidatorTest() {
		this.validator = new SizeValidator();
	}
	
	@Test
	public void testValidateWithStringMinError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("no");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Size.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected field id to have size between 3 and 5 but got 2.", constraint.getMessage());
		Assert.assertEquals(Size.class, constraint.getValidator());
	}
	
	@Test
	public void testValidateWithCollectionMinError() throws SecurityException, NoSuchMethodException {
		Model model = new Model();
		Method method = model.getClass().getMethod("getExceptions", new Class[] {});
		Annotation annotation = method.getAnnotation(Size.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected field exceptions to have size between 1 and 1 but got 0.", constraint.getMessage());
		Assert.assertEquals(Size.class, constraint.getValidator());
	}
	
	@Test
	public void testValidateWithStringMaxError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("Is it ok?");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Size.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected field id to have size between 3 and 5 but got 9.", constraint.getMessage());
		Assert.assertEquals(Size.class, constraint.getValidator());
	}
	
	@Test
	public void testValidateWithCollectionMaxError() throws SecurityException, NoSuchMethodException {
		Model model = new Model()
			.addException("1")
			.addException("2");
		Method method = model.getClass().getMethod("getExceptions", new Class[] {});
		Annotation annotation = method.getAnnotation(Size.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected field exceptions to have size between 1 and 1 but got 2.", constraint.getMessage());
		Assert.assertEquals(Size.class, constraint.getValidator());
	}
	
	@Test
	public void testValidate() throws SecurityException, NoSuchMethodException {
		Model model = new Model()
			.withId("yes!")
			.addException("1");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Size.class);
		 
		Set<ConstraintViolation> res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
		
		method = model.getClass().getMethod("getExceptions", new Class[] {});
		annotation = method.getAnnotation(Size.class);
		
		res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
	}
}