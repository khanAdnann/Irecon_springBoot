package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "all_cbs_rawdata")
@Data
@NoArgsConstructor
public class FN100CBSRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70) 
    private String atmId;

    @Column(length = 70) 
    private String refNum;

    @Column(length = 70) 
    private String cardNumber;

    @Column(length = 70) 
    private String tranAmt;

    @Column(length = 70) 
    private String tranDate;

    @Column(length = 70) 

    private String rcreTime;

    @Column(length = 70) 
    private String initSolId;

    @Column(length = 70) 
  
    private String valueDate;

    @Column(length = 70) 
    private String partTranType;

    @Column(length = 70) 
    private String foracid;

    @Column(length = 70) 
    private String bankId;

    @Column(length = 70) 
    private String cummulativeBalance;
    @Column(length = 70) 
    private String entryUser;

    @Column(length = 70) 
    private String tranParticular;

    @Column(length = 70) 
    private String tranId;
    @Column(length = 70) 
    private String contraForacid;

    @Column(length = 70) 
    private String tranRemark;
    @Column(length = 70) 

    private String postedTime;

    @Column(length = 70) 

    private String verifiedTime;

    @Column(length = 70) 
    private String tranPart1;

    @Column(length = 70) 
    private String tranPart2;

    @Column(length = 70) 
   
    private String fileDate;
    @Column(length = 70) 
    private String fileName;

    @Column(length = 70) 
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtmId() {
		return atmId;
	}

	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getRcreTime() {
		return rcreTime;
	}

	public void setRcreTime(String rcreTime) {
		this.rcreTime = rcreTime;
	}

	public String getInitSolId() {
		return initSolId;
	}

	public void setInitSolId(String initSolId) {
		this.initSolId = initSolId;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getPartTranType() {
		return partTranType;
	}

	public void setPartTranType(String partTranType) {
		this.partTranType = partTranType;
	}

	public String getForacid() {
		return foracid;
	}

	public void setForacid(String foracid) {
		this.foracid = foracid;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCummulativeBalance() {
		return cummulativeBalance;
	}

	public void setCummulativeBalance(String cummulativeBalance) {
		this.cummulativeBalance = cummulativeBalance;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getTranParticular() {
		return tranParticular;
	}

	public void setTranParticular(String tranParticular) {
		this.tranParticular = tranParticular;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getContraForacid() {
		return contraForacid;
	}

	public void setContraForacid(String contraForacid) {
		this.contraForacid = contraForacid;
	}

	public String getTranRemark() {
		return tranRemark;
	}

	public void setTranRemark(String tranRemark) {
		this.tranRemark = tranRemark;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

	public String getVerifiedTime() {
		return verifiedTime;
	}

	public void setVerifiedTime(String verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	public String getTranPart1() {
		return tranPart1;
	}

	public void setTranPart1(String tranPart1) {
		this.tranPart1 = tranPart1;
	}

	public String getTranPart2() {
		return tranPart2;
	}

	public void setTranPart2(String tranPart2) {
		this.tranPart2 = tranPart2;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
}
