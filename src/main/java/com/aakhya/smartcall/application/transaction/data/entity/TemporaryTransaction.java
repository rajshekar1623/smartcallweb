package com.aakhya.smartcall.application.transaction.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temp_transaction")
public class TemporaryTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6847896614089938442L;
	private String firstname;
	private Date dob;
	private Long gender;
	private Long religion;
	private Long socialcategory;
	private String voterid;
	private String drivinglicense;
	private String rationcard;
	private String pancard;
	private String address;
	private String gp;
	private String pincode;
	private String village;
	private String branchcode;
	private String branchname;
	private String fathersname;
	private String product;
	private String mobilenumber;
	private String loanaccountnumber;
	private Long dpdqueue;
	private BigDecimal currentoutstandingbalance;
	private BigDecimal principledue;
	private BigDecimal interestdue;
	private BigDecimal interestrate;
	private Date lastinterestapplieddate;
	private Date npadate;
	@Column(name = "firstname")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Column(name = "dob")
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Column(name = "gender")
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	@Column(name = "religion")
	public Long getReligion() {
		return religion;
	}
	public void setReligion(Long religion) {
		this.religion = religion;
	}
	@Column(name = "socialcategory")
	public Long getSocialcategory() {
		return socialcategory;
	}
	public void setSocialcategory(Long socialcategory) {
		this.socialcategory = socialcategory;
	}
	@Column(name = "voterid")
	public String getVoterid() {
		return voterid;
	}
	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}
	@Column(name = "drivinglicense")
	public String getDrivinglicense() {
		return drivinglicense;
	}
	public void setDrivinglicense(String drivinglicense) {
		this.drivinglicense = drivinglicense;
	}
	@Column(name = "rationcard")
	public String getRationcard() {
		return rationcard;
	}
	public void setRationcard(String rationcard) {
		this.rationcard = rationcard;
	}
	@Column(name = "pancard")
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "gp")
	public String getGp() {
		return gp;
	}
	public void setGp(String gp) {
		this.gp = gp;
	}
	@Column(name = "pincode")
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Column(name = "village")
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	@Column(name = "branchcode")
	public String getBranchcode() {
		return branchcode;
	}
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	@Column(name = "branchname")
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	@Column(name = "fathersname")
	public String getFathersname() {
		return fathersname;
	}
	public void setFathersname(String fathersname) {
		this.fathersname = fathersname;
	}
	@Column(name = "product")
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	@Column(name = "mobilenumber")
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	@Id
	@Column(name = "loanaccountnumber")
	public String getLoanaccountnumber() {
		return loanaccountnumber;
	}
	public void setLoanaccountnumber(String loanaccountnumber) {
		this.loanaccountnumber = loanaccountnumber;
	}
	@Column(name = "dpdqueue")
	public Long getDpdqueue() {
		return dpdqueue;
	}
	public void setDpdqueue(Long dpdqueue) {
		this.dpdqueue = dpdqueue;
	}
	@Column(name = "currentoutstandingbalance")
	public BigDecimal getCurrentoutstandingbalance() {
		return currentoutstandingbalance;
	}
	public void setCurrentoutstandingbalance(BigDecimal currentoutstandingbalance) {
		this.currentoutstandingbalance = currentoutstandingbalance;
	}
	@Column(name = "principledue")
	public BigDecimal getPrincipledue() {
		return principledue;
	}
	public void setPrincipledue(BigDecimal principledue) {
		this.principledue = principledue;
	}
	@Column(name = "interestdue")
	public BigDecimal getInterestdue() {
		return interestdue;
	}
	public void setInterestdue(BigDecimal interestdue) {
		this.interestdue = interestdue;
	}
	@Column(name = "interestrate")
	public BigDecimal getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(BigDecimal interestrate) {
		this.interestrate = interestrate;
	}
	@Column(name = "lastinterestapplieddate")
	public Date getLastinterestapplieddate() {
		return lastinterestapplieddate;
	}
	public void setLastinterestapplieddate(Date lastinterestapplieddate) {
		this.lastinterestapplieddate = lastinterestapplieddate;
	}
	@Column(name = "npadate")
	public Date getNpadate() {
		return npadate;
	}
	public void setNpadate(Date npadate) {
		this.npadate = npadate;
	}
}
