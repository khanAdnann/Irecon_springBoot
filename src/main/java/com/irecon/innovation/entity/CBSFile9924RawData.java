package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cbs_rupay_card_tran")
@Data
@Setter
@Getter

public class CBSFile9924RawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String tranParticular1;

    @Column(length = 100)
    private String tranParticular2;

    @Column(length = 100)
    private String tranParticular3;

    @Column(length = 100)
    private String tranAmount;

    @Column
    private String tranDate;

    @Column
    private String rcreTime;

    @Column(length = 20)
    private String initSolId;

    @Column
    private String valueDate;

    @Column(length = 10)
    private String partTranType;

    @Column(length = 50)
    private String foracid;

    @Column(length = 200)
    private String tranRemarks;

    @Column(length = 100)
    private String cummulativeBalance;

    @Column(length = 200)
    private String tranParticular;

    @Column(length = 200)
    private String narration;

    @Column(length = 20)
    private String solId;

    @Column(length = 100)
    private String tranId;

    @Column(length = 100)
    private String tranRemarks1;

    @Column(length = 100)
    private String postedTime;

    @Column(length = 100)
    private String verifiedTime;

    @Column(length = 100)
    private String fileDate;

    @Column(length = 100)
    private String fileName;

    @Column(length = 100)
    private String createddate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTranParticular1() {
		return tranParticular1;
	}

	public void setTranParticular1(String tranParticular1) {
		this.tranParticular1 = tranParticular1;
	}

	public String getTranParticular2() {
		return tranParticular2;
	}

	public void setTranParticular2(String tranParticular2) {
		this.tranParticular2 = tranParticular2;
	}

	public String getTranParticular3() {
		return tranParticular3;
	}

	public void setTranParticular3(String tranParticular3) {
		this.tranParticular3 = tranParticular3;
	}

	public String getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(String tranAmount) {
		this.tranAmount = tranAmount;
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

	public String getTranRemarks() {
		return tranRemarks;
	}

	public void setTranRemarks(String tranRemarks) {
		this.tranRemarks = tranRemarks;
	}

	public String getCummulativeBalance() {
		return cummulativeBalance;
	}

	public void setCummulativeBalance(String cummulativeBalance) {
		this.cummulativeBalance = cummulativeBalance;
	}

	public String getTranParticular() {
		return tranParticular;
	}

	public void setTranParticular(String tranParticular) {
		this.tranParticular = tranParticular;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getSolId() {
		return solId;
	}

	public void setSolId(String solId) {
		this.solId = solId;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getTranRemarks1() {
		return tranRemarks1;
	}

	public void setTranRemarks1(String tranRemarks1) {
		this.tranRemarks1 = tranRemarks1;
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

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
    
    
}
