package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum FieldDisplayType implements StringValuedEnum {

  BOOLEAN(FieldValue.FieldDisplayType.Boolean),
  TEXT_SINGLE_LINE(FieldValue.FieldDisplayType.TextSingleLine),
  INTEGER(FieldValue.FieldDisplayType.Integer),
  DECIMAL(FieldValue.FieldDisplayType.Decimal),
  DATE_TIME(FieldValue.FieldDisplayType.DateTime),
  TEXT_MULTILINE(FieldValue.FieldDisplayType.TextMultiLine),
  RADIO_BUTTON(FieldValue.FieldDisplayType.RadioButton),
  LOOKUP(FieldValue.FieldDisplayType.Lookup),
  CHECK_BOX(FieldValue.FieldDisplayType.CheckBox);

  private final String fieldDisplayTypeCode;

  private final static EnumForCodeHelper<FieldDisplayType> forCodeHelper = new EnumForCodeHelper<FieldDisplayType>(
    FieldDisplayType.values());

  private FieldDisplayType(String fieldDisplayTypeCode) {
    this.fieldDisplayTypeCode = fieldDisplayTypeCode;
  }

  @Override
  public String getCode() {
    return fieldDisplayTypeCode;
  }

  public static FieldDisplayType forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
