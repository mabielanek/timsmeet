/*
 * 
 */
package com.timsmeet.persistance.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.ScheduleMode;
import com.timsmeet.persistance.enums.ServiceTypeKind;
import com.timsmeet.persistance.enums.TimeUnit;
import com.timsmeet.persistance.enums.YesNo;


@Entity
@Table(name = "tm_service_type",
  indexes=@Index(columnList="company_id", name="idx_serv_type_comopany_fk")
)
public class ServiceTypeEntity {
  
  @Id
  @GeneratedValue(generator = "serviceTypeGenerator")
  @GenericGenerator(name = "serviceTypeGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_service_type_id")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;

  @Column(name = "status", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
  private String status;
  
  @Column(name = "name", nullable = false, length = 255)
  private String name;
  
  @Column(name = "description", length = 1024)
  private String description;
  
  @ManyToOne
  @JoinColumn(name = "company_id", foreignKey=@ForeignKey(name="serv_type_company_fk"))
  private CompanyEntity company;
  
  @Column(name = "kind", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "kind IN('P','C')")
  private String kind;
  
  @Column(name = "allow_int_regular", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "allow_int_regular IN('Y','N')")
  private String allowInternalRegular;

  @Column(name = "allow_int_recurring", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "allow_int_recurring IN('Y','N')")
  private String allowInternalRecurring;
 
  @Column(name = "allow_int_reschedule", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "allow_int_reschedule IN('Y','N')")
  private String allowInternalReschedule;
  
  @Column(name = "allow_ext_regular", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "allow_ext_regular IN('Y','N')")
  private String allowExternalRegular;
  
  @Column(name = "allow_ext_recurring", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "allow_ext_recurring IN('Y','N')")
  private String allowExternalRecurring;
  
  @Column(name = "allow_ext_reschedule", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "allow_ext_reschedule IN('Y','N')")
  private String allowExternalReschedule;

  @Column(name = "schedule_mode", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "schedule_mode IN('A','S')")
  private String scheduleMode;
  
  @Column(name = "appointment_interval", nullable = false)
  private Integer appointmentInterval;

  @Column(name = "default_service_duration")
  private Integer defaultServiceDuration;
  
  @Column(name = "lead_in")
  private Integer leadIn;
  
  @Column(name = "lead_out")
  private Integer leadOut;
  
  @Column(name = "concurrency_limit")
  private Integer concurrencyLimit;
  
  @Column(name = "valid_from")
  private Timestamp validFrom;

  @Column(name = "valid_to")
  private Timestamp validTo;
  
  @Column(name = "int_res_cap")
  private Integer internalReservationAdvanceRange;
  
  @Column(name = "int_res_cap_unit", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_res_cap_unit IN('M','H','D')")
  private String internalAdvanceRangeUnit;
  
  @Column(name = "ext_res_cap")
  private Integer externalReservationAdvanceRange;

  @Column(name = "ext_res_cap_unit", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_res_cap_unit IN('M','H','D')")
  private String externalAdvanceRangeUnit;
  
  @Column(name = "int_reschedule_dedl")
  private Integer internalReschedulingDeadline;

  @Column(name = "int_reschedule_dedl_unit", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_reschedule_dedl IN('M','H','D')")
  private String internalReschedulingUnit;
  
  @Column(name = "ext_reschedule_dedl")
  private Integer externalReschedulingDeadline;

  @Column(name = "ext_reschedule_dedl_unit", length = 1)
  @org.hibernate.annotations.Check(constraints = "ext_reschedule_dedl IN('M','H','D')")
  private String externalReschedulingUnit;

  @Column(name = "int_cancel_dedl")
  private Integer internalCancellationDeadline;
  
  @Column(name = "int_cancel_dedl_unit", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_cancel_dedl_unit IN('M','H','D')")
  private String internalCancellationUnit;
  
  @Column(name = "ext_cancel_dedl")
  private Integer externalCancellationDeadline;

  @Column(name = "ext_cancel_dedl_unit", length = 1)
  @org.hibernate.annotations.Check(constraints = "ext_cancel_dedl_unit IN('M','H','D')")
  private String externalCancellationUnit;
  
  @Column(name = "int_auto_confirm", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_auto_confirm IN('Y','N')")
  private String internalAutoConfirmation;

  @Column(name = "int_auto_cancell", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_auto_cancell IN('Y','N')")
  private String internalAutoCancel;
  
  @Column(name = "int_allow_delete", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_allow_delete IN('Y','N')")
  private String internalAllowDelete;

  @Column(name = "int_res_disclosure", length = 1)
  @org.hibernate.annotations.Check(constraints = "int_res_disclosure IN('Y','N')")
  private String internalReservationDisclosure;

  @Column(name = "ext_auto_confirm", length = 1)
  @org.hibernate.annotations.Check(constraints = "ext_auto_confirm IN('Y','N')")
  private String externalAutoConfirmation;
  
  @Column(name = "ext_auto_cancell", length = 1)
  @org.hibernate.annotations.Check(constraints = "ext_auto_cancell IN('Y','N')")
  private String externalAutoCancel;
  
  @Column(name = "ext_allow_delete", length = 1)
  @org.hibernate.annotations.Check(constraints = "ext_allow_delete IN('Y','N')")
  private String externalAllowDelete;

  @Column(name = "ext_res_disclosure", length = 1)
  @org.hibernate.annotations.Check(constraints = "ext_res_disclosure IN('Y','N')")
  private String externalReservationDisclosure;

  @Column(name = "personel_selection", length = 1)
  @org.hibernate.annotations.Check(constraints = "personel_selection IN('Y','N')")
  private String personelSelection;

  @Column(name = "notify_client", length = 1)
  @org.hibernate.annotations.Check(constraints = "notify_client IN('Y','N')")
  private String notifyClient;
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "service_type_id")
  private List<EmployeeServiceTypeEntity> employeeServiceTypes = new ArrayList<EmployeeServiceTypeEntity>();

  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "service_type_id")
  private List<ServiceLocationServiceTypeEntity> serviceLocationServiceTypes = new ArrayList<ServiceLocationServiceTypeEntity>();

  /**
   * Gets the status.
   *
   * @return the status
   */
  public ActivityStatus getStatus() {
    return ActivityStatus.forCode(status);
  }

  /**
   * Sets the status.
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
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the company.
   *
   * @return the company
   */
  public CompanyEntity getCompany() {
    return company;
  }

  /**
   * Sets the company.
   *
   * @param company the new company
   */
  void setCompany(CompanyEntity company) {
    this.company = company;
  }

  /**
   * Gets the kind (type) of schedule.
   *
   * @return the kind
   */
  public ServiceTypeKind getKind() {
    return ServiceTypeKind.forCode(kind);
  }

  /**
   * Sets the kind (type) of schedule.
   *
   * @param kind the new kind
   */
  public void setKind(ServiceTypeKind kind) {
    this.kind = kind.getCode();
  }

  /**
   * Gets the allow internal regular.
   *
   * @return the allow internal regular
   */
  public YesNo getAllowInternalRegular() {
    return YesNo.forCode(allowInternalRegular);
  }

  /**
   * Sets the allow internal regular.
   *
   * @param allowInternalRegular the new allow internal regular
   */
  public void setAllowInternalRegular(YesNo allowInternalRegular) {
    this.allowInternalRegular = allowInternalRegular.getCode();
  }

  /**
   * Gets the allow internal recurring.
   *
   * @return the allow internal recurring
   */
  public YesNo getAllowInternalRecurring() {
    return YesNo.forCode(allowInternalRecurring);
  }

  /**
   * Sets the allow internal recurring.
   *
   * @param allowInternalRecurring the new allow internal recurring
   */
  public void setAllowInternalRecurring(YesNo allowInternalRecurring) {
    this.allowInternalRecurring = allowInternalRecurring.getCode();
  }

  /**
   * Gets the allow internal reschedule.
   *
   * @return the allow internal reschedule
   */
  public YesNo getAllowInternalReschedule() {
    return YesNo.forCode(allowInternalReschedule);
  }

  /**
   * Sets the allow internal reschedule.
   *
   * @param allowInternalReschedule the new allow internal reschedule
   */
  public void setAllowInternalReschedule(YesNo allowInternalReschedule) {
    this.allowInternalReschedule = allowInternalReschedule.getCode();
  }

  /**
   * Gets the allow external regular.
   *
   * @return the allow external regular
   */
  public YesNo getAllowExternalRegular() {
    return YesNo.forCode(allowExternalRegular);
  }

  /**
   * Sets the allow external regular.
   *
   * @param allowExternalRegular the new allow external regular
   */
  public void setAllowExternalRegular(YesNo allowExternalRegular) {
    this.allowExternalRegular = allowExternalRegular.getCode();
  }

  /**
   * Gets the allow external recurring.
   *
   * @return the allow external recurring
   */
  public YesNo getAllowExternalRecurring() {
    return YesNo.forCode(allowExternalRecurring);
  }

  /**
   * Sets the allow external recurring.
   *
   * @param allowExternalRecurring the new allow external recurring
   */
  public void setAllowExternalRecurring(YesNo allowExternalRecurring) {
    this.allowExternalRecurring = allowExternalRecurring.getCode();
  }

  /**
   * Gets the allow external reschedule.
   *
   * @return the allow external reschedule
   */
  public YesNo getAllowExternalReschedule() {
    return YesNo.forCode(allowExternalReschedule);
  }

  /**
   * Sets the allow external reschedule.
   *
   * @param allowExternalReschedule the new allow external reschedule
   */
  public void setAllowExternalReschedule(YesNo allowExternalReschedule) {
    this.allowExternalReschedule = allowExternalReschedule.getCode();
  }

  /**
   * Gets the schedule mode.
   *
   * @return the schedule mode
   */
  public ScheduleMode getScheduleMode() {
    return ScheduleMode.forCode(scheduleMode);
  }

  /**
   * Sets the schedule mode.
   *
   * @param scheduleMode the new schedule mode
   */
  public void setScheduleMode(ScheduleMode scheduleMode) {
    this.scheduleMode = scheduleMode.getCode();
  }

  /**
   * Gets the appointment interval (in minutes).
   *
   * @return the appointment interval
   */
  public Integer getAppointmentInterval() {
    return appointmentInterval;
  }

  /**
   * Sets the appointment interval (in minutes).
   *
   * @param appointmentInterval the new appointment interval
   */
  public void setAppointmentInterval(Integer appointmentInterval) {
    this.appointmentInterval = appointmentInterval;
  }

  /**
   * Gets the default service duration (in minutes).
   *
   * @return the default service duration
   */
  public Integer getDefaultServiceDuration() {
    return defaultServiceDuration;
  }

  /**
   * Sets the default service duration (in minutes).
   *
   * @param defaultServiceDuration the new default service duration
   */
  public void setDefaultServiceDuration(Integer defaultServiceDuration) {
    this.defaultServiceDuration = defaultServiceDuration;
  }

  /**
   * Gets the lead in (in minutes).
   *
   * @return the lead in
   */
  public Integer getLeadIn() {
    return leadIn;
  }

  /**
   * Sets the lead in (in minutes).
   *
   * @param leadIn the new lead in
   */
  public void setLeadIn(Integer leadIn) {
    this.leadIn = leadIn;
  }

  /**
   * Gets the lead out (in minutes).
   *
   * @return the lead out
   */
  public Integer getLeadOut() {
    return leadOut;
  }

  /**
   * Sets the lead out (in minutes).
   *
   * @param leadOut the new lead out
   */
  public void setLeadOut(Integer leadOut) {
    this.leadOut = leadOut;
  }

  /**
   * Gets the concurrency limit.
   *
   * @return the concurrency limit
   */
  public Integer getConcurrencyLimit() {
    return concurrencyLimit;
  }

  /**
   * Sets the concurrency limit.
   *
   * @param concurencyLimit the new concurrency limit
   */
  public void setConcurrencyLimit(Integer concurrencyLimit) {
    this.concurrencyLimit = concurrencyLimit;
  }

  /**
   * Gets the valid from.
   *
   * @return the valid from
   */
  public Timestamp getValidFrom() {
    return validFrom;
  }

  /**
   * Sets the valid from.
   *
   * @param validFrom the new valid from
   */
  public void setValidFrom(Timestamp validFrom) {
    this.validFrom = validFrom;
  }

  /**
   * Gets the valid to.
   *
   * @return the valid to
   */
  public Timestamp getValidTo() {
    return validTo;
  }

  /**
   * Sets the valid to.
   *
   * @param validTo the new valid to
   */
  public void setValidTo(Timestamp validTo) {
    this.validTo = validTo;
  }

  /**
   * Gets the internal reservation advance range.
   *
   * @return the internal reservation advance range
   */
  public Integer getInternalReservationAdvanceRange() {
    return internalReservationAdvanceRange;
  }

  /**
   * Sets the internal reservation advance range.
   *
   * @param internalReservationAdvanceRange the new internal reservation advance range
   */
  public void setInternalReservationAdvanceRange(Integer internalReservationAdvanceRange) {
    this.internalReservationAdvanceRange = internalReservationAdvanceRange;
  }

  /**
   * Gets the internal advance range unit.
   *
   * @return the internal advance range unit
   */
  public TimeUnit getInternalAdvanceRangeUnit() {
    return TimeUnit.forCode(internalAdvanceRangeUnit);
  }

  /**
   * Sets the internal advance range unit.
   *
   * @param internalAdvanceRangeUnit the new internal advance range unit
   */
  public void setInternalAdvanceRangeUnit(TimeUnit internalAdvanceRangeUnit) {
    this.internalAdvanceRangeUnit = internalAdvanceRangeUnit.getCode();
  }

  /**
   * Gets the external reservation advance range.
   *
   * @return the external reservation advance range
   */
  public Integer getExternalReservationAdvanceRange() {
    return externalReservationAdvanceRange;
  }

  /**
   * Sets the external reservation advance range.
   *
   * @param externalReservationAdvanceRange the new external reservation advance range
   */
  public void setExternalReservationAdvanceRange(Integer externalReservationAdvanceRange) {
    this.externalReservationAdvanceRange = externalReservationAdvanceRange;
  }

  /**
   * Gets the external advance range unit.
   *
   * @return the external advance range unit
   */
  public TimeUnit getExternalAdvanceRangeUnit() {
    return TimeUnit.forCode(externalAdvanceRangeUnit);
  }

  /**
   * Sets the external advance range unit.
   *
   * @param externalAdvanceRangeUnit the new external advance range unit
   */
  public void setExternalAdvanceRangeUnit(TimeUnit externalAdvanceRangeUnit) {
    this.externalAdvanceRangeUnit = externalAdvanceRangeUnit.getCode();
  }

  /**
   * Gets the internal rescheduling deadline.
   *
   * @return the internal rescheduling deadline
   */
  public Integer getInternalReschedulingDeadline() {
    return internalReschedulingDeadline;
  }

  /**
   * Sets the internal rescheduling deadline.
   *
   * @param internalReschedulingDeadline the new internal rescheduling deadline
   */
  public void setInternalReschedulingDeadline(Integer internalReschedulingDeadline) {
    this.internalReschedulingDeadline = internalReschedulingDeadline;
  }

  /**
   * Gets the internal rescheduling unit.
   *
   * @return the internal rescheduling unit
   */
  public TimeUnit getInternalReschedulingUnit() {
    return TimeUnit.forCode(internalReschedulingUnit);
  }

  /**
   * Sets the internal rescheduling unit.
   *
   * @param internalReschedulingUnit the new internal rescheduling unit
   */
  public void setInternalReschedulingUnit(TimeUnit internalReschedulingUnit) {
    this.internalReschedulingUnit = internalReschedulingUnit.getCode();
  }

  /**
   * Gets the external rescheduling deadline.
   *
   * @return the external rescheduling deadline
   */
  public Integer getExternalReschedulingDeadline() {
    return externalReschedulingDeadline;
  }

  /**
   * Sets the external rescheduling deadline.
   *
   * @param externalReschedulingDeadline the new external rescheduling deadline
   */
  public void setExternalReschedulingDeadline(Integer externalReschedulingDeadline) {
    this.externalReschedulingDeadline = externalReschedulingDeadline;
  }

  public TimeUnit getExternalReschedulingUnit() {
    return TimeUnit.forCode(externalReschedulingUnit);
  }

  public void setExternalReschedulingUnit(TimeUnit externalReschedulingUnit) {
    this.externalReschedulingUnit = externalReschedulingUnit.getCode();
  }

  /**
   * Gets the internal cancellation deadline.
   *
   * @return the internal cancellation deadline
   */
  public Integer getInternalCancellationDeadline() {
    return internalCancellationDeadline;
  }

  /**
   * Sets the internal cancellation deadline.
   *
   * @param internalCancellationDeadline the new internal cancellation deadline
   */
  public void setInternalCancellationDeadline(Integer internalCancellationDeadline) {
    this.internalCancellationDeadline = internalCancellationDeadline;
  }

  /**
   * Gets the internal cancellation unit.
   *
   * @return the internal cancellation unit
   */
  public TimeUnit getInternalCancellationUnit() {
    return TimeUnit.forCode(internalCancellationUnit);
  }

  /**
   * Sets the internal cancellation unit.
   *
   * @param internalCancellationUnit the new internal cancellation unit
   */
  public void setInternalCancellationUnit(TimeUnit internalCancellationUnit) {
    this.internalCancellationUnit = internalCancellationUnit.getCode();
  }

  /**
   * Gets the external cancellation deadline.
   *
   * @return the external cancellation deadline
   */
  public Integer getExternalCancellationDeadline() {
    return externalCancellationDeadline;
  }

  /**
   * Sets the external cancellation deadline.
   *
   * @param externalCancellationDeadline the new external cancellation deadline
   */
  public void setExternalCancellationDeadline(Integer externalCancellationDeadline) {
    this.externalCancellationDeadline = externalCancellationDeadline;
  }

  /**
   * Gets the external cancellation unit.
   *
   * @return the external cancellation unit
   */
  public TimeUnit getExternalCancellationUnit() {
    return TimeUnit.forCode(externalCancellationUnit);
  }

  /**
   * Sets the external cancellation unit.
   *
   * @param externalCancellationUnit the new external cancellation unit
   */
  public void setExternalCancellationUnit(TimeUnit externalCancellationUnit) {
    this.externalCancellationUnit = externalCancellationUnit.getCode();
  }

  /**
   * Gets the internal auto confirmation.
   *
   * @return the internal auto confirmation
   */
  public YesNo getInternalAutoConfirmation() {
    return YesNo.forCode(internalAutoConfirmation);
  }

  /**
   * Sets the internal auto confirmation.
   *
   * @param internalAutoConfirmation the new internal auto confirmation
   */
  public void setInternalAutoConfirmation(YesNo internalAutoConfirmation) {
    this.internalAutoConfirmation = internalAutoConfirmation.getCode();
  }

  /**
   * Gets the internal auto cancel.
   *
   * @return the internal auto cancel
   */
  public YesNo getInternalAutoCancel() {
    return YesNo.forCode(internalAutoCancel);
  }

  /**
   * Sets the internal auto cancel.
   *
   * @param internalAutoCancel the new internal auto cancel
   */
  public void setInternalAutoCancel(YesNo internalAutoCancel) {
    this.internalAutoCancel = internalAutoCancel.getCode();
  }

  /**
   * Gets the internal allow delete.
   *
   * @return the internal allow delete
   */
  public YesNo getInternalAllowDelete() {
    return YesNo.forCode(internalAllowDelete);
  }

  /**
   * Sets the internal allow delete.
   *
   * @param internalAllowDelete the new internal allow delete
   */
  public void setInternalAllowDelete(YesNo internalAllowDelete) {
    this.internalAllowDelete = internalAllowDelete.getCode();
  }

  /**
   * Gets the internal reservation disclosure.
   *
   * @return the internal reservation disclosure
   */
  public YesNo getInternalReservationDisclosure() {
    return YesNo.forCode(internalReservationDisclosure);
  }

  /**
   * Sets the internal reservation disclosure.
   *
   * @param internalReservationDisclosure the new internal reservation disclosure
   */
  public void setInternalReservationDisclosure(YesNo internalReservationDisclosure) {
    this.internalReservationDisclosure = internalReservationDisclosure.getCode();
  }

  /**
   * Gets the external auto confirmation.
   *
   * @return the external auto confirmation
   */
  public YesNo getExternalAutoConfirmation() {
    return YesNo.forCode(externalAutoConfirmation);
  }

  /**
   * Sets the external auto confirmation.
   *
   * @param externalAutoConfirmation the new external auto confirmation
   */
  public void setExternalAutoConfirmation(YesNo externalAutoConfirmation) {
    this.externalAutoConfirmation = externalAutoConfirmation.getCode();
  }

  /**
   * Gets the external auto cancel.
   *
   * @return the external auto cancel
   */
  public YesNo getExternalAutoCancel() {
    return YesNo.forCode(externalAutoCancel);
  }

  /**
   * Sets the external auto cancel.
   *
   * @param externalAutoCancel the new external auto cancel
   */
  public void setExternalAutoCancel(YesNo externalAutoCancel) {
    this.externalAutoCancel = externalAutoCancel.getCode();
  }

  /**
   * Gets the external allow delete.
   *
   * @return the external allow delete
   */
  public YesNo getExternalAllowDelete() {
    return YesNo.forCode(externalAllowDelete);
  }

  /**
   * Sets the external allow delete.
   *
   * @param externalAllowDelete the new external allow delete
   */
  public void setExternalAllowDelete(YesNo externalAllowDelete) {
    this.externalAllowDelete = externalAllowDelete.getCode();
  }

  /**
   * Gets the external reservation disclosure.
   *
   * @return the external reservation disclosure
   */
  public YesNo getExternalReservationDisclosure() {
    return YesNo.forCode(externalReservationDisclosure);
  }

  /**
   * Sets the external reservation disclosure.
   *
   * @param externalReservationDisclosure the new external reservation disclosure
   */
  public void setExternalReservationDisclosure(YesNo externalReservationDisclosure) {
    this.externalReservationDisclosure = externalReservationDisclosure.getCode();
  }

  /**
   * Gets the personel selection.
   *
   * @return the personel selection
   */
  public YesNo getPersonelSelection() {
    return YesNo.forCode(personelSelection);
  }

  /**
   * Sets the personel selection.
   *
   * @param personelSelection the new personel selection
   */
  public void setPersonelSelection(YesNo personelSelection) {
    this.personelSelection = personelSelection.getCode();
  }

  /**
   * Gets the notify client.
   *
   * @return the notify client
   */
  public YesNo getNotifyClient() {
    return YesNo.forCode(notifyClient);
  }

  /**
   * Sets the notify client.
   *
   * @param notifyClient the new notify client
   */
  public void setNotifyClient(YesNo notifyClient) {
    this.notifyClient = notifyClient.getCode();
  }

  /**
   * Gets the employees service types.
   *
   * @return the employees service types
   */
  public List<EmployeeServiceTypeEntity> getEmployeeServiceTypes() {
    return employeeServiceTypes;
  }
  
  /**
   * Adds the employee service type.
   *
   * @param employeeServiceType the employee service type
   * @return true, if collection changed as a result of the call
   */
  public boolean addEmployeeServiceType(EmployeeServiceTypeEntity employeeServiceType) {
    if (this.employeeServiceTypes == null) {
      this.employeeServiceTypes = Lists.newArrayList();
    }
    employeeServiceType.setServiceType(this);
    return this.employeeServiceTypes.add(employeeServiceType);
  }
  
  /**
   * Removes the employee service type.
   *
   * @param employeeServieType the employee service type
   * @return true, if list contained the specified element
   */
  public boolean removeEmployeeServiceType(EmployeeServiceTypeEntity employeeServiceType) {
    Preconditions.checkNotNull(employeeServiceType);
    employeeServiceType.setServiceType(null);
    if (this.employeeServiceTypes != null) {
      return this.employeeServiceTypes.remove(employeeServiceType);
    }
    return false;
  }

  /**
   * Gets the service location service types.
   *
   * @return the service location service types
   */
  public List<ServiceLocationServiceTypeEntity> getServiceLocationServiceTypes() {
    return serviceLocationServiceTypes;
  }
  
  /**
   * Adds the service location service type.
   *
   * @param serviceLocationServiceType the service location service type
   * @return true, if collection changed as a result of the call
   */
  public boolean addServiceLocationServiceType(ServiceLocationServiceTypeEntity serviceLocationServiceType) {
    if (this.serviceLocationServiceTypes == null) {
      this.serviceLocationServiceTypes = Lists.newArrayList();
    }
    serviceLocationServiceType.setServiceType(this);
    return this.serviceLocationServiceTypes.add(serviceLocationServiceType);
  }
  
  /**
   * Removes the service location service type.
   *
   * @param serviceLocationServiceType the service location service type
   * @return true, if list contained the specified element
   */
  public boolean removeServiceLocationServiceType(ServiceLocationServiceTypeEntity serviceLocationServiceType) {
    Preconditions.checkNotNull(serviceLocationServiceType);
    serviceLocationServiceType.setServiceType(null);
    if (this.serviceLocationServiceTypes != null) {
      return this.serviceLocationServiceTypes.remove(serviceLocationServiceType);
    }
    return false;
  }
  
  /**
   * Gets the ServiceType record identifier.
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
	  private ServiceTypeEntity entity = new ServiceTypeEntity();
	  
	  public ServiceTypeEntity build() {
		  return entity;
	  }

	public Builder status(ActivityStatus status) {
		entity.setStatus(status);
		return this;
	}

	public Builder name(String name) {
		entity.setName(name);
		return this;
	}

	public Builder description(String description) {
		entity.setDescription(description);
		return this;
	}

	public Builder company(CompanyEntity company) {
		entity.setCompany(company);
		return this;
	}

	public Builder kind(ServiceTypeKind kind) {
		entity.setKind(kind);
		return this;
	}

	public Builder allowInternalRegular(YesNo allowInternalRegular) {
		entity.setAllowInternalRegular(allowInternalRegular);
		return this;
	}

	public Builder allowInternalRecurring(YesNo allowInternalRecurring) {
		entity.setAllowInternalRecurring(allowInternalRecurring);
		return this;
	}

	public Builder allowInternalReschedule(YesNo allowInternalReschedule) {
		entity.setAllowInternalReschedule(allowInternalReschedule);
		return this;
	}

	public Builder allowExternalRegular(YesNo allowExternalRegular) {
		entity.setAllowExternalRegular(allowExternalRegular);
		return this;
	}

	public Builder allowExternalRecurring(YesNo allowExternalRecurring) {
		entity.setAllowExternalRecurring(allowExternalRecurring);
		return this;
	}

	public Builder allowExternalReschedule(YesNo allowExternalReschedule) {
		entity.setAllowExternalReschedule(allowExternalReschedule);
		return this;
	}

	public Builder scheduleMode(ScheduleMode scheduleMode) {
		entity.setScheduleMode(scheduleMode);
		return this;
	}

	public Builder appointmentInterval(Integer appointmentInterval) {
		entity.setAppointmentInterval(appointmentInterval);
		return this;
	}

	public Builder defaultServiceDuration(Integer defaultServiceDuration) {
		entity.setDefaultServiceDuration(defaultServiceDuration);
		return this;
	}

	public Builder leadIn(Integer leadIn) {
		entity.setLeadIn(leadIn);
		return this;
	}

	public Builder leadOut(Integer leadOut) {
		entity.setLeadOut(leadOut);
		return this;
	}

	public Builder concurrencyLimit(Integer concurrencyLimit) {
		entity.setConcurrencyLimit(concurrencyLimit);
		return this;
	}

	public Builder validFrom(Timestamp validFrom) {
		entity.setValidFrom(validFrom);
		return this;
	}

	public Builder validTo(Timestamp validTo) {
		entity.setValidTo(validTo);
		return this;
	}

	public Builder internalReservationAdvanceRange(Integer internalReservationAdvanceRange) {
		entity.setInternalReservationAdvanceRange(internalReservationAdvanceRange);
		return this;
	}

	public Builder internalAdvanceRangeUnit(TimeUnit internalAdvanceRangeUnit) {
		entity.setInternalAdvanceRangeUnit(internalAdvanceRangeUnit);
		return this;
	}

	public Builder externalReservationAdvanceRange(Integer externalReservationAdvanceRange) {
		entity.setExternalReservationAdvanceRange(externalReservationAdvanceRange);
		return this;
	}

	public Builder externalAdvanceRangeUnit(TimeUnit externalAdvanceRangeUnit) {
		entity.setExternalAdvanceRangeUnit(externalAdvanceRangeUnit);
		return this;
	}

	public Builder internalReschedulingDeadline(Integer internalReschedulingDeadline) {
		entity.setInternalReschedulingDeadline(internalReschedulingDeadline);
		return this;
	}

	public Builder internalReschedulingUnit(TimeUnit internalReschedulingUnit) {
		entity.setInternalReschedulingUnit(internalReschedulingUnit);
		return this;
	}

	public Builder externalReschedulingDeadline(Integer externalReschedulingDeadline) {
		entity.setExternalReschedulingDeadline(externalReschedulingDeadline);
		return this;
	}

	public Builder externalReschedulingUnit(TimeUnit externalReschedulingUnit) {
		entity.setExternalReschedulingUnit(externalReschedulingUnit);
		return this;
	}

	public Builder internalCancellationDeadline(Integer internalCancellationDeadline) {
		entity.setInternalCancellationDeadline(internalCancellationDeadline);
		return this;
	}

	public Builder internalCancellationUnit(TimeUnit internalCancellationUnit) {
		entity.setInternalCancellationUnit(internalCancellationUnit);
		return this;
	}

	public Builder externalCancellationDeadline(Integer externalCancellationDeadline) {
		entity.setExternalCancellationDeadline(externalCancellationDeadline);
		return this;
	}

	public Builder externalCancellationUnit(TimeUnit externalCancellationUnit) {
		entity.setExternalCancellationUnit(externalCancellationUnit);
		return this;
	}

	public Builder internalAutoConfirmation(YesNo internalAutoConfirmation) {
		entity.setInternalAutoConfirmation(internalAutoConfirmation);
		return this;
	}

	public Builder internalAutoCancel(YesNo internalAutoCancel) {
		entity.setInternalAutoCancel(internalAutoCancel);
		return this;
	}

	public Builder internalAllowDelete(YesNo internalAllowDelete) {
		entity.setInternalAllowDelete(internalAllowDelete);
		return this;
	}

	public Builder internalReservationDisclosure(YesNo internalReservationDisclosure) {
		entity.setInternalReservationDisclosure(internalReservationDisclosure);
		return this;
	}

	public Builder externalAutoConfirmation(YesNo externalAutoConfirmation) {
		entity.setExternalAutoConfirmation(externalAutoConfirmation);
		return this;
	}

	public Builder externalAutoCancel(YesNo externalAutoCancel) {
		entity.setExternalAutoCancel(externalAutoCancel);
		return this;
	}

	public Builder externalAllowDelete(YesNo externalAllowDelete) {
		entity.setExternalAllowDelete(externalAllowDelete);
		return this;
	}

	public Builder externalReservationDisclosure(YesNo externalReservationDisclosure) {
		entity.setExternalReservationDisclosure(externalReservationDisclosure);
		return this;
	}

	public Builder personelSelection(YesNo personelSelection) {
		entity.setPersonelSelection(personelSelection);
		return this;
	}

	public Builder notifyClient(YesNo notifyClient) {
		entity.setNotifyClient(notifyClient);
		return this;
	}

	public Builder addEmployeeServiceType(EmployeeServiceTypeEntity... employeeServiceTypes) {
		for(EmployeeServiceTypeEntity employeeServiceType : employeeServiceTypes) {
			entity.addEmployeeServiceType(employeeServiceType);
		}
		return this;
	}

	public Builder addServiceLocationServiceType(ServiceLocationServiceTypeEntity... serviceLocationServiceTypes) {
		for(ServiceLocationServiceTypeEntity serviceLocationServiceType : serviceLocationServiceTypes) {
			entity.addServiceLocationServiceType(serviceLocationServiceType);
		}
		return this;
	}
	  
	  
  }
  

}
