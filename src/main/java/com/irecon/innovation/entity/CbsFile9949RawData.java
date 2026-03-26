package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cbs_nfs_acq_rawdata")
@Data
public class CbsFile9949RawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tran_particular1", length = 100)
    private String tranParticular1;

    @Column(name = "seq_num", length = 100)
    private String seqNum;

    @Column(name = "card_no", length = 100)
    private String cardNo;

    @Column(name = "tran_amount", length = 100)
    private String tranAmount;

    @Column(name = "value_date", length = 100)
    private String valueDate;

    @Column(name = "rcre_time", length = 100)
    private String rcreTime;

    @Column(name = "sol_id", length = 100)
    private String solId;

    @Column(name = "tran_date", length = 100)
    private String tranDate;

    @Column(name = "part_tran_type", length = 100)
    private String partTranType;

    @Column(name = "atm_gl", length = 100)
    private String atmGl;

    @Column(name = "tran_remarks", length = 100)
    private String tranRemarks;

    @Column(name = "cummulative_balance", length = 100)
    private String cummulativeBalance;

    @Column(name = "tran_particular", length = 100)
    private String tranParticular;

    @Column(length = 100)
    private String narration;

    @Column(name = "tran_id", length = 100)
    private String tranId;

    @Column(length = 100)
    private String uid;

    @Column(name = "tran_remarks1", length = 100)
    private String tranRemarks1;

    @Column(name = "posted_time", length = 100)
    private String postedTime;

    @Column(name = "verified_time", length = 100)
    private String verifiedTime;

    @Column(name = "approval_code", length = 100)
    private String approvalCode;

    @Column(name = "reference_number", length = 100)
    private String referenceNumber;

    @Column( length = 100)
    private String fileDate;

    @Column(length = 100)
    private String fileName;

    @Column(name = "CREATEDDATE", length = 100)
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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
