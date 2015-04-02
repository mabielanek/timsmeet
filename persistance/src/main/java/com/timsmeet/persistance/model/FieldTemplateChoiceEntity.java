package com.timsmeet.persistance.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.timsmeet.persistance.constants.FieldValue.FieldValueType;


@Entity
@Table(name = "tm_field_template_choice",
  indexes=@Index(columnList="field_template_id", name="idx_choice_field_templ_fk")
)
public class FieldTemplateChoiceEntity {

  @Id
  @GeneratedValue(generator = "fieldTemplateChoiceGenerator")
  @GenericGenerator(name = "fieldTemplateChoiceGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_fld_templ_choice_id")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;
  
  @Column(name = "value_decimal")
  private BigDecimal valueDecimal;
  
  @Column(name = "value_integer")
  private long valueInteger;
  
  @Column(name = "value_date_time")
  private Timestamp valueDateTime;
  
  @Column(name = "value_string", length = 1024)
  private String valueString;
  
  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "field_template_id", foreignKey=@ForeignKey(name="choice_field_templ_fk"))
  private FieldTemplateEntity fieldTemplate;

  /**
   * Gets the value decimal - valid if fieldTemplate.valueType is {@link FieldValueType.Decimal}.
   *
   * @return the value decimal
   */
  public BigDecimal getValueDecimal() {
    return valueDecimal;
  }

  /**
   * Sets the value decimal - valid if fieldTemplate.valueType is {@link FieldValueType.Decimal}.
   *
   * @param valueDecimal the new value decimal
   */
  public void setValueDecimal(BigDecimal valueDecimal) {
    Preconditions.checkNotNull(fieldTemplate, "Can't set value, when fieldTemplate is not set.");
    Preconditions.checkState(FieldValueType.Decimal.equals(fieldTemplate.getValueType()),
      "Decimal value can be set only when fieldTemplate has decimal value type.");
    this.valueDecimal = valueDecimal;
  }

  /**
   * Gets the value integer - valid if fieldTemplate.valueType is {@link FieldValueType.Integer}.
   *
   * @return the value integer
   */
  public long getValueInteger() {
    return valueInteger;
  }

  /**
   * Sets the value integer - valid if fieldTemplate.valueType is {@link FieldValueType.Integer}.
   *
   * @param valueInteger the new value integer
   */
  public void setValueInteger(long valueInteger) {
    Preconditions.checkNotNull(fieldTemplate, "Can't set value, when fieldTemplate is not set.");
    Preconditions.checkState(FieldValueType.Integer.equals(fieldTemplate.getValueType()),
      "Integer value can be set only when fieldTemplate has integer value type");
    this.valueInteger = valueInteger;
  }

  /**
   * Gets the value date time - valid if fieldTemplate.valueType is {@link FieldValueType.DateTime}.
   *
   * @return the value date time
   */
  public Timestamp getValueDateTime() {
    return valueDateTime;
  }

  /**
   * Sets the value date time - valid if fieldTemplate.valueType is {@link FieldValueType.DateTime}.
   *
   * @param valueDateTime the new value date time
   */
  public void setValueDateTime(Timestamp valueDateTime) {
    Preconditions.checkNotNull(fieldTemplate, "Can't set value, when fieldTemplate is not set.");
    Preconditions.checkState(FieldValueType.DateTime.equals(fieldTemplate.getValueType()),
      "DateTime value can be set only when fieldTemplate has dateTime value type");
    this.valueDateTime = valueDateTime;
  }

  /**
   * Gets the value string - valid if fieldTemplate.valueType is {@link FieldValueType.String}.
   *
   * @return the value string
   */
  public String getValueString() {
    return valueString;
  }

  /**
   * Sets the value string - valid if fieldTemplate.valueType is {@link FieldValueType.String}.
   *
   * @param valueString the new value string
   */
  public void setValueString(String valueString) {
    Preconditions.checkNotNull(fieldTemplate, "Can't set value, when fieldTemplate is not set.");
    Preconditions.checkState(FieldValueType.String.equals(fieldTemplate.getValueType()),
      "String value can be set only when fieldTemplate has string value type");
    this.valueString = valueString;
  }

  /**
   * Gets the field template.
   *
   * @return the field template
   */
  public FieldTemplateEntity getFieldTemplate() {
    return fieldTemplate;
  }

  /**
   * Sets the field template.
   *
   * @param fieldTemplate the new field template
   */
  void setFieldTemplate(FieldTemplateEntity fieldTemplate) {
    this.fieldTemplate = fieldTemplate;
  }

  /**
   * Gets the FieldTemplateChoice record identifier.
   *
   * @return the record identifier
   */
  public long getId() {
    return id;
  }

  /**
   * Gets the last modification identifier - for optimistic concurrency locking.
   *
   * @return the last modification id
   */
  public long getLastMofidicationId() {
    return lastModificationId;
  }

  /**
   * Sets the last modification identifier - for optimistic concurrency locking.
   *
   * @param comment the last modification id
   */
  public void setLastModificationId(long lastModificationId) {
	  this.lastModificationId = lastModificationId;
  }
  
	public final static class Builder {
		private FieldTemplateChoiceEntity entity = new FieldTemplateChoiceEntity();

		public FieldTemplateChoiceEntity build() {
			return entity;
		}

		public Builder valueDecimal(BigDecimal valueDecimal) {
			entity.setValueDecimal(valueDecimal);
			return this;
		}

		public Builder valueDateTime(Timestamp valueDateTime) {
			entity.setValueDateTime(valueDateTime);
			return this;
		}

		public Builder valueInteger(long valueInteger) {
			entity.setValueInteger(valueInteger);
			return this;
		}

		public Builder valueString(String valueString) {
			entity.setValueString(valueString);
			return this;
		}

		public Builder fieldTemplate(FieldTemplateEntity fieldTemplate) {
			entity.setFieldTemplate(fieldTemplate);
			return this;
		}

	}
}
