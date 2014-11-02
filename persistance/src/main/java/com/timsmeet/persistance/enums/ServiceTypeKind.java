package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum ServiceTypeKind implements StringValuedEnum {
  
  PERSONEL_TYPE(FieldValue.ServiceTypeKind.PersonelType),
  CAPACITY_TYPE(FieldValue.ServiceTypeKind.CapacityType);

  private final String serviceTypeKindCode;

  private final static EnumForCodeHelper<ServiceTypeKind> forCodeHelper = new EnumForCodeHelper<ServiceTypeKind>(ServiceTypeKind.values());

  private ServiceTypeKind(String serviceTypeKindCode) {
    this.serviceTypeKindCode = serviceTypeKindCode;
  }

  @Override
  public String getCode() {
    return serviceTypeKindCode;
  }

  public static ServiceTypeKind forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
