package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum FieldValueType implements StringValuedEnum {

  INTEGER(FieldValue.FieldValueType.Integer),
  DECIMAL(FieldValue.FieldValueType.Decimal),
  DATE_TIME(FieldValue.FieldValueType.DateTime),
  STRING(FieldValue.FieldValueType.String);

  private final String fieldValueTypeCode;

  private final static EnumForCodeHelper<FieldValueType> forCodeHelper = new EnumForCodeHelper<FieldValueType>(FieldValueType.values());

  private FieldValueType(String fieldValueTypeCode) {
    this.fieldValueTypeCode = fieldValueTypeCode;
  }

  @Override
  public String getCode() {
    return fieldValueTypeCode;
  }

  public static FieldValueType forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
