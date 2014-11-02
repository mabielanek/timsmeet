package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.FieldDisplayType;
import com.timsmeet.persistance.enums.FieldValueType;

@Entity
@Table(name = "tm_field_template")
public class FieldTemplateEntity {

  @Id
  @GeneratedValue(generator = "fieldTemplateGenerator")
  @GenericGenerator(name = "fieldTemplateGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_field_templ_id")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;
  
  @Column(name = "status", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
  private String status;

  @Column(name = "name", length = 255, nullable = false)
  private String name;
  
  @Column(name = "value_type", length = 1, nullable = false)
  @org.hibernate.annotations.Check(constraints = "value_type IN('I','F','D','S')")
  private String valueType;
  
  @Column(name = "display_type", length = 1, nullable = false)
  @org.hibernate.annotations.Check(constraints = "display_type IN('B','S','I','F','D','M','C','L','X')")
  private String displayType;

  /**
   * Gets the record status.
   *
   * @return the status
   */
  public ActivityStatus getStatus() {
    return ActivityStatus.forCode(status);
  }

  /**
   * Sets the record status.
   *
   * @param status the new status
   */
  public void setStatus(ActivityStatus status) {
    this.status = status.getCode();
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the value type.
   *
   * @return the value type
   */
  public FieldValueType getValueType() {
    return FieldValueType.forCode(valueType);
  }

  /**
   * Sets the value type.
   *
   * @param valueType the new value type
   */
  public void setValueType(FieldValueType valueType) {
    this.valueType = valueType.getCode();
  }

  /**
   * Gets the display type.
   *
   * @return the display type
   */
  public FieldDisplayType getDisplayType() {
    return FieldDisplayType.forCode(displayType);
  }

  /**
   * Sets the display type.
   *
   * @param displayType the new display type
   */
  public void setDisplayType(FieldDisplayType displayType) {
    this.displayType = displayType.getCode();
  }

  /**
   * Gets the Employee record identifier.
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

	public final static class Builder {

		private FieldTemplateEntity entity = new FieldTemplateEntity();

		public FieldTemplateEntity build() {
			return entity;
		}

		public Builder(ActivityStatus status, String name,
				FieldValueType valueType, FieldDisplayType displayType) {
			entity.setStatus(status);
			entity.setName(name);
			entity.setValueType(valueType);
			entity.setDisplayType(displayType);
		}

		public Builder status(ActivityStatus status) {
			entity.setStatus(status);
			return this;
		}

		public Builder name(String name) {
			entity.setName(name);
			return this;
		}

		public Builder valueType(FieldValueType valueType) {
			entity.setValueType(valueType);
			return this;
		}

		public Builder displayType(FieldDisplayType displayType) {
			entity.setDisplayType(displayType);
			return this;
		}
	}
}
