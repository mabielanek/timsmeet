package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.ScheduleMode;

public class ScheduleModeTest {
	
	@Test
	public void scheduleModeForCodeTest() {
		Assert.assertTrue("Should parse as Adaptive.", 
				ScheduleMode.forCode(com.timsmeet.persistance.constants.FieldValue.ScheduleMode.Adaptive) == ScheduleMode.ADAPTIVE);
		Assert.assertTrue("Should parse as Standard.", 
				ScheduleMode.forCode(com.timsmeet.persistance.constants.FieldValue.ScheduleMode.Standard) == ScheduleMode.STANDARD);
		Assert.assertNull("Should parse to null.", ScheduleMode.forCode("XX"));
	}

}
