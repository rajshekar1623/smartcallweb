package com.aakhya.smartcall.application.transaction.activity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sc_activitydetail")
public class ActivityDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5750905047142840128L;
	private Long activityId;
	private Date activityDateTime;
	private Integer attemptSequence;
	private Date attemptDateTime;
	private Integer attemptDuration;
	private String attemptStatus;
	private String attemptFlow;
	private String attemptNotes;
	private String scheduleType;
	private Date scheduleDateTime;
	private String status;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Date removeDateTime;
	private Long companyId;
	private Date validFrom;
	private Date validTo;
	private String genericString1;
	private String genericString2;
	private String genericString3;
	private String genericString4;
	private String genericString5;
	private String genericString6;
	private String genericString7;
	private String genericString8;
	private String genericString9;
	private String genericString10;
	private String genericString11;
	private String genericString12;
	private String genericString13;
	private String genericString14;
	private String genericString15;
	private Long genericNumber1;
	private Long genericNumber2;
	private Long genericNumber3;
	private Long genericNumber4;
	private Long genericNumber5;
	private Long genericNumber6;
	private Long genericNumber7;
	private Long genericNumber8;
	private Long genericNumber9;
	private Long genericNumber10;
	private Long genericNumber11;
	private Long genericNumber12;
	private Long genericNumber13;
	private Long genericNumber14;
	private Long genericNumber15;
	private BigDecimal genericDecimal1;
	private BigDecimal genericDecimal2;
	private BigDecimal genericDecimal3;
	private BigDecimal genericDecimal4;
	private BigDecimal genericDecimal5;
	private BigDecimal genericDecimal6;
	private BigDecimal genericDecimal7;
	private BigDecimal genericDecimal8;
	private BigDecimal genericDecimal9;
	private BigDecimal genericDecimal10;
	private BigDecimal genericDecimal11;
	private BigDecimal genericDecimal12;
	private BigDecimal genericDecimal13;
	private BigDecimal genericDecimal14;
	private BigDecimal genericDecimal15;
	private Date genericDate1;
	private Date genericDate2;
	private Date genericDate3;
	private Date genericDate4;
	private Date genericDate5;
	private Date genericDate6;
	private Date genericDate7;
	private Date genericDate8;
	private Date genericDate9;
	private Date genericDate10;
	private Date genericDate11;
	private Date genericDate12;
	private Date genericDate13;
	private Date genericDate14;
	private Date genericDate15;
	private Activity activity;
	
	@Id
	@Column(name = "activityid")
	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column(name = "activitydatetime")
	public Date getActivityDateTime() {
		return this.activityDateTime;
	}

	public void setActivityDateTime(Date activityDateTime) {
		this.activityDateTime = activityDateTime;
	}

	@Id
	@Column(name = "attemptsequence")
	public Integer getAttemptSequence() {
		return this.attemptSequence;
	}

	public void setAttemptSequence(Integer attemptSequence) {
		this.attemptSequence = attemptSequence;
	}

	@Column(name = "attemptdatetime")
	public Date getAttemptDateTime() {
		return this.attemptDateTime;
	}

	public void setAttemptDateTime(Date attemptDateTime) {
		this.attemptDateTime = attemptDateTime;
	}

	@Column(name = "attemptduration")
	public Integer getAttemptDuration() {
		return this.attemptDuration;
	}

	public void setAttemptDuration(Integer attemptDuration) {
		this.attemptDuration = attemptDuration;
	}

	@Column(name = "attemptstatus")
	public String getAttemptStatus() {
		return this.attemptStatus;
	}

	public void setAttemptStatus(String attemptStatus) {
		this.attemptStatus = attemptStatus;
	}

	@Column(name = "attemptflow")
	public String getAttemptFlow() {
		return this.attemptFlow;
	}

	public void setAttemptFlow(String attemptFlow) {
		this.attemptFlow = attemptFlow;
	}

	@Column(name = "attemptnotes")
	public String getAttemptNotes() {
		return this.attemptNotes;
	}

	public void setAttemptNotes(String attemptNotes) {
		this.attemptNotes = attemptNotes;
	}

	@Column(name = "scheduletype")
	public String getScheduleType() {
		return this.scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	@Column(name = "scheduledatetime")
	public Date getScheduleDateTime() {
		return this.scheduleDateTime;
	}

	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createdby")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "createddatetime")
	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "updatedby")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updateddatetime")
	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Column(name = "removedatetime")
	public Date getRemoveDateTime() {
		return this.removeDateTime;
	}

	public void setRemoveDateTime(Date removeDateTime) {
		this.removeDateTime = removeDateTime;
	}

	@Id
	@Column(name = "companyid")
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "validfrom")
	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	@Column(name = "validto")
	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	@Column(name = "genericstring1")
	public String getGenericString1() {
		return this.genericString1;
	}

	public void setGenericString1(String genericString1) {
		this.genericString1 = genericString1;
	}

	@Column(name = "genericstring2")
	public String getGenericString2() {
		return this.genericString2;
	}

	public void setGenericString2(String genericString2) {
		this.genericString2 = genericString2;
	}

	@Column(name = "genericstring3")
	public String getGenericString3() {
		return this.genericString3;
	}

	public void setGenericString3(String genericString3) {
		this.genericString3 = genericString3;
	}

	@Column(name = "genericstring4")
	public String getGenericString4() {
		return this.genericString4;
	}

	public void setGenericString4(String genericString4) {
		this.genericString4 = genericString4;
	}

	@Column(name = "genericstring5")
	public String getGenericString5() {
		return this.genericString5;
	}

	public void setGenericString5(String genericString5) {
		this.genericString5 = genericString5;
	}

	@Column(name = "genericstring6")
	public String getGenericString6() {
		return this.genericString6;
	}

	public void setGenericString6(String genericString6) {
		this.genericString6 = genericString6;
	}

	@Column(name = "genericstring7")
	public String getGenericString7() {
		return this.genericString7;
	}

	public void setGenericString7(String genericString7) {
		this.genericString7 = genericString7;
	}

	@Column(name = "genericstring8")
	public String getGenericString8() {
		return this.genericString8;
	}

	public void setGenericString8(String genericString8) {
		this.genericString8 = genericString8;
	}

	@Column(name = "genericstring9")
	public String getGenericString9() {
		return this.genericString9;
	}

	public void setGenericString9(String genericString9) {
		this.genericString9 = genericString9;
	}

	@Column(name = "genericstring10")
	public String getGenericString10() {
		return this.genericString10;
	}

	public void setGenericString10(String genericString10) {
		this.genericString10 = genericString10;
	}

	@Column(name = "genericstring11")
	public String getGenericString11() {
		return this.genericString11;
	}

	public void setGenericString11(String genericString11) {
		this.genericString11 = genericString11;
	}

	@Column(name = "genericstring12")
	public String getGenericString12() {
		return this.genericString12;
	}

	public void setGenericString12(String genericString12) {
		this.genericString12 = genericString12;
	}

	@Column(name = "genericstring13")
	public String getGenericString13() {
		return this.genericString13;
	}

	public void setGenericString13(String genericString13) {
		this.genericString13 = genericString13;
	}

	@Column(name = "genericstring14")
	public String getGenericString14() {
		return this.genericString14;
	}

	public void setGenericString14(String genericString14) {
		this.genericString14 = genericString14;
	}

	@Column(name = "genericstring15")
	public String getGenericString15() {
		return this.genericString15;
	}

	public void setGenericString15(String genericString15) {
		this.genericString15 = genericString15;
	}

	@Column(name = "genericnumber1")
	public Long getGenericNumber1() {
		return this.genericNumber1;
	}

	public void setGenericNumber1(Long genericNumber1) {
		this.genericNumber1 = genericNumber1;
	}

	@Column(name = "genericnumber2")
	public Long getGenericNumber2() {
		return this.genericNumber2;
	}

	public void setGenericNumber2(Long genericNumber2) {
		this.genericNumber2 = genericNumber2;
	}

	@Column(name = "genericnumber3")
	public Long getGenericNumber3() {
		return this.genericNumber3;
	}

	public void setGenericNumber3(Long genericNumber3) {
		this.genericNumber3 = genericNumber3;
	}

	@Column(name = "genericnumber4")
	public Long getGenericNumber4() {
		return this.genericNumber4;
	}

	public void setGenericNumber4(Long genericNumber4) {
		this.genericNumber4 = genericNumber4;
	}

	@Column(name = "genericnumber5")
	public Long getGenericNumber5() {
		return this.genericNumber5;
	}

	public void setGenericNumber5(Long genericNumber5) {
		this.genericNumber5 = genericNumber5;
	}

	@Column(name = "genericnumber6")
	public Long getGenericNumber6() {
		return this.genericNumber6;
	}

	public void setGenericNumber6(Long genericNumber6) {
		this.genericNumber6 = genericNumber6;
	}

	@Column(name = "genericnumber7")
	public Long getGenericNumber7() {
		return this.genericNumber7;
	}

	public void setGenericNumber7(Long genericNumber7) {
		this.genericNumber7 = genericNumber7;
	}

	@Column(name = "genericnumber8")
	public Long getGenericNumber8() {
		return this.genericNumber8;
	}

	public void setGenericNumber8(Long genericNumber8) {
		this.genericNumber8 = genericNumber8;
	}

	@Column(name = "genericnumber9")
	public Long getGenericNumber9() {
		return this.genericNumber9;
	}

	public void setGenericNumber9(Long genericNumber9) {
		this.genericNumber9 = genericNumber9;
	}

	@Column(name = "genericnumber10")
	public Long getGenericNumber10() {
		return this.genericNumber10;
	}

	public void setGenericNumber10(Long genericNumber10) {
		this.genericNumber10 = genericNumber10;
	}

	@Column(name = "genericnumber11")
	public Long getGenericNumber11() {
		return this.genericNumber11;
	}

	public void setGenericNumber11(Long genericNumber11) {
		this.genericNumber11 = genericNumber11;
	}

	@Column(name = "genericnumber12")
	public Long getGenericNumber12() {
		return this.genericNumber12;
	}

	public void setGenericNumber12(Long genericNumber12) {
		this.genericNumber12 = genericNumber12;
	}

	@Column(name = "genericnumber13")
	public Long getGenericNumber13() {
		return this.genericNumber13;
	}

	public void setGenericNumber13(Long genericNumber13) {
		this.genericNumber13 = genericNumber13;
	}

	@Column(name = "genericnumber14")
	public Long getGenericNumber14() {
		return this.genericNumber14;
	}

	public void setGenericNumber14(Long genericNumber14) {
		this.genericNumber14 = genericNumber14;
	}

	@Column(name = "genericnumber15")
	public Long getGenericNumber15() {
		return this.genericNumber15;
	}

	public void setGenericNumber15(Long genericNumber15) {
		this.genericNumber15 = genericNumber15;
	}

	@Column(name = "genericdecimal1")
	public BigDecimal getGenericDecimal1() {
		return this.genericDecimal1;
	}

	public void setGenericDecimal1(BigDecimal genericDecimal1) {
		this.genericDecimal1 = genericDecimal1;
	}

	@Column(name = "genericdecimal2")
	public BigDecimal getGenericDecimal2() {
		return this.genericDecimal2;
	}

	public void setGenericDecimal2(BigDecimal genericDecimal2) {
		this.genericDecimal2 = genericDecimal2;
	}

	@Column(name = "genericdecimal3")
	public BigDecimal getGenericDecimal3() {
		return this.genericDecimal3;
	}

	public void setGenericDecimal3(BigDecimal genericDecimal3) {
		this.genericDecimal3 = genericDecimal3;
	}

	@Column(name = "genericdecimal4")
	public BigDecimal getGenericDecimal4() {
		return this.genericDecimal4;
	}

	public void setGenericDecimal4(BigDecimal genericDecimal4) {
		this.genericDecimal4 = genericDecimal4;
	}

	@Column(name = "genericdecimal5")
	public BigDecimal getGenericDecimal5() {
		return this.genericDecimal5;
	}

	public void setGenericDecimal5(BigDecimal genericDecimal5) {
		this.genericDecimal5 = genericDecimal5;
	}

	@Column(name = "genericdecimal6")
	public BigDecimal getGenericDecimal6() {
		return this.genericDecimal6;
	}

	public void setGenericDecimal6(BigDecimal genericDecimal6) {
		this.genericDecimal6 = genericDecimal6;
	}

	@Column(name = "genericdecimal7")
	public BigDecimal getGenericDecimal7() {
		return this.genericDecimal7;
	}

	public void setGenericDecimal7(BigDecimal genericDecimal7) {
		this.genericDecimal7 = genericDecimal7;
	}

	@Column(name = "genericdecimal8")
	public BigDecimal getGenericDecimal8() {
		return this.genericDecimal8;
	}

	public void setGenericDecimal8(BigDecimal genericDecimal8) {
		this.genericDecimal8 = genericDecimal8;
	}

	@Column(name = "genericdecimal9")
	public BigDecimal getGenericDecimal9() {
		return this.genericDecimal9;
	}

	public void setGenericDecimal9(BigDecimal genericDecimal9) {
		this.genericDecimal9 = genericDecimal9;
	}

	@Column(name = "genericdecimal10")
	public BigDecimal getGenericDecimal10() {
		return this.genericDecimal10;
	}

	public void setGenericDecimal10(BigDecimal genericDecimal10) {
		this.genericDecimal10 = genericDecimal10;
	}

	@Column(name = "genericdecimal11")
	public BigDecimal getGenericDecimal11() {
		return this.genericDecimal11;
	}

	public void setGenericDecimal11(BigDecimal genericDecimal11) {
		this.genericDecimal11 = genericDecimal11;
	}

	@Column(name = "genericdecimal12")
	public BigDecimal getGenericDecimal12() {
		return this.genericDecimal12;
	}

	public void setGenericDecimal12(BigDecimal genericDecimal12) {
		this.genericDecimal12 = genericDecimal12;
	}

	@Column(name = "genericdecimal13")
	public BigDecimal getGenericDecimal13() {
		return this.genericDecimal13;
	}

	public void setGenericDecimal13(BigDecimal genericDecimal13) {
		this.genericDecimal13 = genericDecimal13;
	}

	@Column(name = "genericdecimal14")
	public BigDecimal getGenericDecimal14() {
		return this.genericDecimal14;
	}

	public void setGenericDecimal14(BigDecimal genericDecimal14) {
		this.genericDecimal14 = genericDecimal14;
	}

	@Column(name = "genericdecimal15")
	public BigDecimal getGenericDecimal15() {
		return this.genericDecimal15;
	}

	public void setGenericDecimal15(BigDecimal genericDecimal15) {
		this.genericDecimal15 = genericDecimal15;
	}

	@Column(name = "genericdate1")
	public Date getGenericDate1() {
		return this.genericDate1;
	}

	public void setGenericDate1(Date genericDate1) {
		this.genericDate1 = genericDate1;
	}

	@Column(name = "genericdate2")
	public Date getGenericDate2() {
		return this.genericDate2;
	}

	public void setGenericDate2(Date genericDate2) {
		this.genericDate2 = genericDate2;
	}

	@Column(name = "genericdate3")
	public Date getGenericDate3() {
		return this.genericDate3;
	}

	public void setGenericDate3(Date genericDate3) {
		this.genericDate3 = genericDate3;
	}

	@Column(name = "genericdate4")
	public Date getGenericDate4() {
		return this.genericDate4;
	}

	public void setGenericDate4(Date genericDate4) {
		this.genericDate4 = genericDate4;
	}

	@Column(name = "genericdate5")
	public Date getGenericDate5() {
		return this.genericDate5;
	}

	public void setGenericDate5(Date genericDate5) {
		this.genericDate5 = genericDate5;
	}

	@Column(name = "genericdate6")
	public Date getGenericDate6() {
		return this.genericDate6;
	}

	public void setGenericDate6(Date genericDate6) {
		this.genericDate6 = genericDate6;
	}

	@Column(name = "genericdate7")
	public Date getGenericDate7() {
		return this.genericDate7;
	}

	public void setGenericDate7(Date genericDate7) {
		this.genericDate7 = genericDate7;
	}

	@Column(name = "genericdate8")
	public Date getGenericDate8() {
		return this.genericDate8;
	}

	public void setGenericDate8(Date genericDate8) {
		this.genericDate8 = genericDate8;
	}

	@Column(name = "genericdate9")
	public Date getGenericDate9() {
		return this.genericDate9;
	}

	public void setGenericDate9(Date genericDate9) {
		this.genericDate9 = genericDate9;
	}

	@Column(name = "genericdate10")
	public Date getGenericDate10() {
		return this.genericDate10;
	}

	public void setGenericDate10(Date genericDate10) {
		this.genericDate10 = genericDate10;
	}

	@Column(name = "genericdate11")
	public Date getGenericDate11() {
		return this.genericDate11;
	}

	public void setGenericDate11(Date genericDate11) {
		this.genericDate11 = genericDate11;
	}

	@Column(name = "genericdate12")
	public Date getGenericDate12() {
		return this.genericDate12;
	}

	public void setGenericDate12(Date genericDate12) {
		this.genericDate12 = genericDate12;
	}

	@Column(name = "genericdate13")
	public Date getGenericDate13() {
		return this.genericDate13;
	}

	public void setGenericDate13(Date genericDate13) {
		this.genericDate13 = genericDate13;
	}

	@Column(name = "genericdate14")
	public Date getGenericDate14() {
		return this.genericDate14;
	}

	public void setGenericDate14(Date genericDate14) {
		this.genericDate14 = genericDate14;
	}

	@Column(name = "genericdate15")
	public Date getGenericDate15() {
		return this.genericDate15;
	}

	public void setGenericDate15(Date genericDate15) {
		this.genericDate15 = genericDate15;
	}

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "activityid",referencedColumnName = "activityid",insertable = false,updatable = false),
			@JoinColumn(name = "companyid",referencedColumnName = "companyid",insertable = false,updatable = false)
	})
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	

}
