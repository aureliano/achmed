package com.github.aureliano.achmed.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.Model;
import com.github.aureliano.achmed.annotation.Pattern;

public class PatternValidatorTest {

	private PatternValidator validator;
	
	public PatternValidatorTest() {
		this.validator = new PatternValidator();
	}
	
	@Test
	public void testValidateWithError() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("no");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Pattern.class);
		 
		ConstraintViolation constraint = this.validator.validate(model, method, annotation).iterator().next();
		Assert.assertNotNull(constraint);
		
		Assert.assertEquals("Expected field id to match [\\d\\w]{3,5} regular expression.", constraint.getMessage());
		Assert.assertEquals(Pattern.class, constraint.getValidator());
	}
	
	@Test
	public void testValidate() throws SecurityException, NoSuchMethodException {
		Model model = new Model().withId("_ok");
		Method method = model.getClass().getMethod("getId", new Class[] {});
		Annotation annotation = method.getAnnotation(Pattern.class);
		 
		Set<ConstraintViolation> res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
		
		model = new Model().withId("yeah_");
		method = model.getClass().getMethod("getId", new Class[] {});
		annotation = method.getAnnotation(Pattern.class);
		
		res = this.validator.validate(model, method, annotation);
		Assert.assertTrue(res.isEmpty());
	}
}