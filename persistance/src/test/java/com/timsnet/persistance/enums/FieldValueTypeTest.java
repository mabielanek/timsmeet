package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.FieldValueType;

public class FieldValueTypeTest {
	
	@Test
	public void fieldValueTypeForCodeTest() {
		Assert.assertTrue("Should parse as DateTime.", 
				FieldValueType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldValueType.DateTime) == FieldValueType.DATE_TIME);
		Assert.assertTrue("Should parse as decimal.", 
				FieldValueType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldValueType.Decimal) == FieldValueType.DECIMAL);
		Assert.assertTrue("Should parse as integer.", 
				FieldValueType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldValueType.Integer) == FieldValueType.INTEGER);
		Assert.assertTrue("Should parse as string.", 
				FieldValueType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldValueType.String) == FieldValueType.STRING);
		Assert.assertNull("Should parse to null.", 
				FieldValueType.forCode("XX"));
	}

}
