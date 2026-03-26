package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "dfs_adjustment_rawdata")
@Getter
@Setter
public class DfsAdjustmentRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String txnuid;

    @Column(length = 100)
    private String txnType;

    @Column(length = 100)
    private String uId;

  
    private String adjDate;

    @Column(length = 100)
    private String adjType;

    @Column(length = 100)
    private String acq;

    @Column(length = 100)
    private String isr;

    @Column(length = 100)
    private String response;

 
    private String txnDate;

    @Column(length = 100)
    private String txnTime;

    @Column(length = 100)
    private String rrn;

    @Column(length = 100)
    private String atmid;

    @Column(length = 100)
    private String cardNo;

    private String chbDate;

    @Column(length = 100)
    private String chbRef;

    @Column(length = 100)
    private String txnAmount;

    @Column(length = 100)
    private String adjAmount;

    @Column(length = 100)
    private String acqFee;

    @Column(length = 100)
    private String issFee;

    @Column(length = 100)
    private String issFeeSW;

    @Column(length = 100)
    private String npciFee;

    @Column(length = 100)
    private String acqFeeTax;

    @Column(length = 100)
    private String issFeeTax;

    @Column(length = 100)
    private String npciTAX;

    @Column(length = 100)
    private String adjRef;

    @Column(length = 100)
    private String bankAdjRef;

    @Column(length = 100)
    private String adjProof;

    @Column(length = 100)
    private String reasonDesc;

    @Column(length = 100)
    private String pincode;

    @Column(length = 100)
    private String atmLocation;

    @Column(length = 500)
    private String multiDisputeGroup;

    @Column(length = 100)
    private String fcqm;

    private String adjSettlementDate;

    @Column(length = 100)
    private String customerPenalty;

    @Column(length = 100)
    private String adjTime;

    @Column(length = 100)
    private String cycle;


    private String tatExpiryDate;

    @Column(length = 100)
    private String acqSTLAmount;

    @Column(length = 100)
    private String acqCC;

    @Column(length = 100)
    private String panEntryMode;

    @Column(length = 100)
    private String serviceCode;

    @Column(length = 100)
    private String cardDatInputCapability;

    @Column(length = 100)
    private String mccCode;

    @Column(length = 100)
    private String createdBy;

  
    private String fileDate;

    @Column(length = 100)
    private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxnuid() {
		return txnuid;
	}

	public void setTxnuid(String txnuid) {
		this.txnuid = txnuid;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getAdjDate() {
		return adjDate;
	}

	public void setAdjDate(String adjDate) {
		this.adjDate = adjDate;
	}

	public String getAdjType() {
		return adjType;
	}

	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}

	public String getAcq() {
		return acq;
	}

	public void setAcq(String acq) {
		this.acq = acq;
	}

	public String getIsr() {
		return isr;
	}

	public void setIsr(String isr) {
		this.isr = isr;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getAtmid() {
		return atmid;
	}

	public void setAtmid(String atmid) {
		this.atmid = atmid;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getChbDate() {
		return chbDate;
	}

	public void setChbDate(String chbDate) {
		this.chbDate = chbDate;
	}

	public String getChbRef() {
		return chbRef;
	}

	public void setChbRef(String chbRef) {
		this.chbRef = chbRef;
	}

	public String getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getAdjAmount() {
		return adjAmount;
	}

	public void setAdjAmount(String adjAmount) {
		this.adjAmount = adjAmount;
	}

	public String getAcqFee() {
		return acqFee;
	}

	public void setAcqFee(String acqFee) {
		this.acqFee = acqFee;
	}

	public String getIssFee() {
		return issFee;
	}

	public void setIssFee(String issFee) {
		this.issFee = issFee;
	}

	public String getIssFeeSW() {
		return issFeeSW;
	}

	public void setIssFeeSW(String issFeeSW) {
		this.issFeeSW = issFeeSW;
	}

	public String getNpciFee() {
		return npciFee;
	}

	public void setNpciFee(String npciFee) {
		this.npciFee = npciFee;
	}

	public String getAcqFeeTax() {
		return acqFeeTax;
	}

	public void setAcqFeeTax(String acqFeeTax) {
		this.acqFeeTax = acqFeeTax;
	}

	public String getIssFeeTax() {
		return issFeeTax;
	}

	public void setIssFeeTax(String issFeeTax) {
		this.issFeeTax = issFeeTax;
	}

	public String getNpciTAX() {
		return npciTAX;
	}

	public void setNpciTAX(String npciTAX) {
		this.npciTAX = npciTAX;
	}

	public String getAdjRef() {
		return adjRef;
	}

	public void setAdjRef(String adjRef) {
		this.adjRef = adjRef;
	}

	public String getBankAdjRef() {
		return bankAdjRef;
	}

	public void setBankAdjRef(String bankAdjRef) {
		this.bankAdjRef = bankAdjRef;
	}

	public String getAdjProof() {
		return adjProof;
	}

	public void setAdjProof(String adjProof) {
		this.adjProof = adjProof;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAtmLocation() {
		return atmLocation;
	}

	public void setAtmLocation(String atmLocation) {
		this.atmLocation = atmLocation;
	}

	public String getMultiDisputeGroup() {
		return multiDisputeGroup;
	}

	public void setMultiDisputeGroup(String multiDisputeGroup) {
		this.multiDisputeGroup = multiDisputeGroup;
	}

	public String getFcqm() {
		return fcqm;
	}

	public void setFcqm(String fcqm) {
		this.fcqm = fcqm;
	}

	public String getAdjSettlementDate() {
		return adjSettlementDate;
	}

	public void setAdjSettlementDate(String adjSettlementDate) {
		this.adjSettlementDate = adjSettlementDate;
	}

	public String getCustomerPenalty() {
		return customerPenalty;
	}

	public void setCustomerPenalty(String customerPenalty) {
		this.customerPenalty = customerPenalty;
	}

	public String getAdjTime() {
		return adjTime;
	}

	public void setAdjTime(String adjTime) {
		this.adjTime = adjTime;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getTatExpiryDate() {
		return tatExpiryDate;
	}

	public void setTatExpiryDate(String tatExpiryDate) {
		this.tatExpiryDate = tatExpiryDate;
	}

	public String getAcqSTLAmount() {
		return acqSTLAmount;
	}

	public void setAcqSTLAmount(String acqSTLAmount) {
		this.acqSTLAmount = acqSTLAmount;
	}

	public String getAcqCC() {
		return acqCC;
	}

	public void setAcqCC(String acqCC) {
		this.acqCC = acqCC;
	}

	public String getPanEntryMode() {
		return panEntryMode;
	}

	public void setPanEntryMode(String panEntryMode) {
		this.panEntryMode = panEntryMode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getCardDatInputCapability() {
		return cardDatInputCapability;
	}

	public void setCardDatInputCapability(String cardDatInputCapability) {
		this.cardDatInputCapability = cardDatInputCapability;
	}

	public String getMccCode() {
		return mccCode;
	}

	public void setMccCode(String mccCode) {
		this.mccCode = mccCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
}
