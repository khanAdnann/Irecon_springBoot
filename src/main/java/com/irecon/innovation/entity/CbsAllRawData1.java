package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cbs_rupay_all_rawdata1")
@Data
public class CbsAllRawData1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String tranParticular1;

    @Column(length = 100)
    private String seqNum;

    @Column(length = 100)
    private String cardNo;

    @Column(length = 100)
    private String tranAmount;

    @Column(length = 100)
    private String valueDate;

    @Column(length = 100)
    private String rcreTime;

    @Column(length = 100)
    private String solId;

    @Column(length = 100)
    private String tranDate;

    @Column(length = 100)
    private String partTranType;

    @Column(length = 100)
    private String atmGl;

    @Column(length = 100)
    private String tranRemarks;

    @Column(length = 100)
    private String cummulativeBalance;

    @Column(length = 100)
    private String tranParticular;

    @Column(length = 100)
    private String narration;

    @Column(length = 100)
    private String tranId;

    @Column(length = 100)
    private String uid;

    @Column(length = 100)
    private String tranRemarks1;

    @Column(length = 100)
    private String postedTime;

    @Column(length = 100)
    private String verifiedTime;

    @Column(length = 100)
    private String approvalCode;

    @Column(length = 100)
    private String flag;

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

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(String tranAmount) {
		this.tranAmount = tranAmount;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getRcreTime() {
		return rcreTime;
	}

	public void setRcreTime(String rcreTime) {
		this.rcreTime = rcreTime;
	}

	public String getSolId() {
		return solId;
	}

	public void setSolId(String solId) {
		this.solId = solId;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getPartTranType() {
		return partTranType;
	}

	public void setPartTranType(String partTranType) {
		this.partTranType = partTranType;
	}

	public String getAtmGl() {
		return atmGl;
	}

	public void setAtmGl(String atmGl) {
		this.atmGl = atmGl;
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

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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