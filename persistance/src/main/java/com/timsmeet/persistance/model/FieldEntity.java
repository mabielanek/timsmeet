package com.timsmeet.persistance.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
@Table(name = "tm_field",
  indexes = {@Index(columnList="field_template_id", name="idx_field_field_templ_fk"),
             @Index(columnList="company_id", name="idx_field_company_fk"),
             @Index(columnList="employee_id", name="idx_field_employee_fk"),
             @Index(columnList="service_location_id", name="idx_field_serv_loc_fk")}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "owner_type", discriminatorType = DiscriminatorType.STRING, length = 1)
public class FieldEntity {

  @Id
  @GeneratedValue(generator = "fieldGenerator")
  @GenericGenerator(name = "fieldGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_field_id")
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
  
  @ManyToOne
  @JoinColumn(name = "field_template_id", foreignKey=@ForeignKey(name="field_field_templ_fk"))
  //@ForeignKey(name = "field_field_templ_fk")
  //@Index(name = "idx_field_field_templ_fk")
  private FieldTemplateEntity fieldTemplate;

  /**
   * Gets the decimal value if field (valid when field template indicates decimal value).
   *
   * @return the value decimal
   */
  public BigDecimal getValueDecimal() {
    return valueDecimal;
  }

  /**
   * Sets the value decimal (valid when field template indicates decimal value).
   *
   * @param valueDecimal the new value decimal
   */
  public void setValueDecimal(BigDecimal valueDecimal) {
    this.valueDecimal = valueDecimal;
  }

  /**
   * Gets the value integer (valid when field template indicates integer value).
   *
   * @return the value integer
   */
  public long getValueInteger() {
    return valueInteger;
  }

  /**
   * Sets the value integer (valid when field template indicates integer value).
   *
   * @param valueInteger the new value integer
   */
  public void setValueInteger(long valueInteger) {
    this.valueInteger = valueInteger;
  }

  /**
   * Gets the value date time (valid when field template indicates date/time value).
   *
   * @return the value date time
   */
  public Timestamp getValueDateTime() {
    return valueDateTime;
  }

  /**
   * Sets the value date time (valid when field template indicates date/time value).
   *
   * @param valueDateTime the new value date time
   */
  public void setValueDateTime(Timestamp valueDateTime) {
    this.valueDateTime = valueDateTime;
  }

  /**
   * Gets the value string (valid when field template indicates string value).
   *
   * @return the value string
   */
  public String getValueString() {
    return valueString;
  }

  /**
   * Sets the value string (valid when field template indicates string value).
   *
   * @param valueString the new value string
   */
  public void setValueString(String valueString) {
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
  public void setFieldTemplate(FieldTemplateEntity fieldTemplate) {
    this.fieldTemplate = fieldTemplate;
  }

  /**
   * Gets the Field record identifier.
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
  public long getLastModificationId() {
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

  static class Builder<T extends FieldEntity> {

	  private final T entity;
	  
	  public Builder(T entity) {
	    this.entity = entity;
	  }

	  public Builder<T> valueDecimal(BigDecimal valueDecimal) {
	    entity.setValueDecimal(valueDecimal);
	    return this;
	  }

	  public Builder<T> valueInteger(long valueInteger) {
	    entity.setValueInteger(valueInteger);
	    return this;
	  }

	  public Builder<T> valueDateTime(Timestamp valueDateTime) {
	    entity.setValueDateTime(valueDateTime);
	    return this;
	  }

	  public Builder<T> valueString(String valueString) {
	    entity.setValueString(valueString);
	    return this;
	  }

	  public Builder<T> fieldTemplate(FieldTemplateEntity fieldTemplate) {
	    entity.setFieldTemplate(fieldTemplate);
	    return this;
	  }
	  
	  public T build() {
	    return entity;
	  }
  }
  
  
}
