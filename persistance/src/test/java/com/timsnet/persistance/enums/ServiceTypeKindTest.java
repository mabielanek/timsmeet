package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.ServiceTypeKind;

public class ServiceTypeKindTest {
  
  @Test
  public void ServiceTypeKindForCodeTest() {
    Assert.assertTrue("Should parse as Capacity.", 
      ServiceTypeKind.forCode(com.timsmeet.persistance.constants.FieldValue.ServiceTypeKind.CapacityType) == ServiceTypeKind.CAPACITY_TYPE);
  Assert.assertTrue("Should parse as Personel.", 
    ServiceTypeKind.forCode(com.timsmeet.persistance.constants.FieldValue.ServiceTypeKind.PersonelType) == ServiceTypeKind.PERSONEL_TYPE);
  Assert.assertNull("Should parse to null.", ServiceTypeKind.forCode("XX"));
  }

}
