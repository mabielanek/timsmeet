package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.FieldDisplayType;

public class FieldDisplayTypeTest {
	
	@Test
	public void fieldDisplayTypeForCodeTest() {
		Assert.assertTrue("Should parse as Boolean.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.Boolean) == FieldDisplayType.BOOLEAN);
		Assert.assertTrue("Should parse as CheckBox.",
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.CheckBox) == FieldDisplayType.CHECK_BOX);
		Assert.assertTrue("Should parse as DateTime.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.DateTime) == FieldDisplayType.DATE_TIME);
		Assert.assertTrue("Should parse as Decimal.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.Decimal) == FieldDisplayType.DECIMAL);
		Assert.assertTrue("Should parse as Integer.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.Integer) == FieldDisplayType.INTEGER);
		Assert.assertTrue("Should parse as Lookup.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.Lookup) == FieldDisplayType.LOOKUP);
		Assert.assertTrue("Should parse as RadioButton.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.RadioButton) == FieldDisplayType.RADIO_BUTTON);
		Assert.assertTrue("Should parse as TextMultiline.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.TextMultiLine) == FieldDisplayType.TEXT_MULTILINE);
		Assert.assertTrue("Should parse as TextSinglline.", 
				FieldDisplayType.forCode(com.timsmeet.persistance.constants.FieldValue.FieldDisplayType.TextSingleLine) == FieldDisplayType.TEXT_SINGLE_LINE);
		Assert.assertNull("Should parse to null.", FieldDisplayType.forCode("XX"));
	}

}
