package com.github.aureliano.achmed.client.validation;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.achmed.client.Model;

public class ObjectValidatorTest {

	private ObjectValidator validator;
	
	public ObjectValidatorTest() {
		this.validator = ObjectValidator.instance();;
	}
	
	@Test
	public void testValidate() {
		Model configuration = new Model().withId(null);
		Set<ConstraintViolation> violations = this.validator.validate(configuration);
		
		Assert.assertEquals(6, violations.size());
	}
}