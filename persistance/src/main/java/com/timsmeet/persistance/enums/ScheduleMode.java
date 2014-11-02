package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum ScheduleMode implements StringValuedEnum {
  
  ADAPTIVE(FieldValue.ScheduleMode.Adaptive),
  STANDARD(FieldValue.ScheduleMode.Standard);

  private final String scheduleModeCode;

  private final static EnumForCodeHelper<ScheduleMode> forCodeHelper = new EnumForCodeHelper<ScheduleMode>(ScheduleMode.values());

  private ScheduleMode(String scheduleModeCode) {
    this.scheduleModeCode = scheduleModeCode;
  }

  @Override
  public String getCode() {
    return scheduleModeCode;
  }

  public static ScheduleMode forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
