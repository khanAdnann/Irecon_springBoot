package com.irecon.innovation.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dfs_iss_rawdata")
@Data
@NoArgsConstructor
public class DfsIssRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 70) 
    private String participantId;

    @Column(length = 70) 
    private String transactionType;

    @Column(length = 70) 
    private String fromAccountType;

    @Column(length = 70) 
    private String toAccountType;

    @Column(length = 70) 
    private String txnSerialNo;
    @Column(length = 70) 
    private String responseCode;

    @Column(length = 70) 
    private String panNumber;
    @Column(length = 70) 
    private String memberNumber;

    @Column(length = 70) 
    private String approvalNumber;

    @Column(length = 70) 
    private String sysTraceAuditNo;

    @Column(length = 70) 
    private String transactionDate;
    @Column(length = 70) 
    private String transactionTime;
    @Column(length = 70) 
    private String merchantCategoryCd;
    @Column(length = 70) 
    private String cardAccSettleDt;

    @Column(length = 70) 
    private String cardAccId;
    @Column(length = 70) 
    private String cardAccTerminalId;

    @Column(length = 70) 
    private String cardAccTerminalLoc;

    @Column(length = 70) 
    private String acquirerId;

    @Column(length = 70) 
    private String networkId;

    @Column(length = 70) 
    private String account1Number;

    @Column(length = 70) 
    private String account1BranchId;

    @Column(length = 70) 
    private String account2Number;
    @Column(length = 70) 
    private String account2BranchId;
    @Column(length = 70) 
    private String txnCurrencyCode;

    @Column(length = 70) 
    private String txnAmount;
    @Column(length = 70) 
    private String actualTxnAmt;

    @Column(length = 70) 
    private String txnActivityFee;
    @Column(length = 70) 
    private String issSettleCurrencyCd;
    @Column(length = 70) 
    private String issSettleAmnt;

    @Column(length = 70) 
    private String issSettleFee;

    @Column(length = 70) 
    private String issSettleProcessFee;

    @Column(length = 70) 
    private String cardholderBillCurrncyC;

    @Column(length = 70) 
    private String cardholderBillAmount;

    @Column(length = 70) 
    private String cardholderBillActFee;

    @Column(length = 70) 
    private String cardholderBillProcessF;

    @Column(length = 70) 
    private String cardholderBillServFee;

    @Column(length = 70) 
    private String txnIssConversionRt;

    @Column(length = 70) 
    private String txnCardholderConvRt;
    @Column(length = 70) 
    private String partId;

    @Column(length = 70) 
    private String dcrsTranNo;

    @Column(length = 70) 
    private Date createdDate;
    @Column(length = 70) 
    private String createdBy;

    @Column(length = 70) 
    private String fileDate;

    @Column(length = 70) 
    private String fpan;

    @Column(length = 70) 
    private String cycle;
    @Column(length = 70) 
    private String filename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getFromAccountType() {
		return fromAccountType;
	}

	public void setFromAccountType(String fromAccountType) {
		this.fromAccountType = fromAccountType;
	}

	public String getToAccountType() {
		return toAccountType;
	}

	public void setToAccountType(String toAccountType) {
		this.toAccountType = toAccountType;
	}

	public String getTxnSerialNo() {
		return txnSerialNo;
	}

	public void setTxnSerialNo(String txnSerialNo) {
		this.txnSerialNo = txnSerialNo;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getSysTraceAuditNo() {
		return sysTraceAuditNo;
	}

	public void setSysTraceAuditNo(String sysTraceAuditNo) {
		this.sysTraceAuditNo = sysTraceAuditNo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getMerchantCategoryCd() {
		return merchantCategoryCd;
	}

	public void setMerchantCategoryCd(String merchantCategoryCd) {
		this.merchantCategoryCd = merchantCategoryCd;
	}

	public String getCardAccSettleDt() {
		return cardAccSettleDt;
	}

	public void setCardAccSettleDt(String cardAccSettleDt) {
		this.cardAccSettleDt = cardAccSettleDt;
	}

	public String getCardAccId() {
		return cardAccId;
	}

	public void setCardAccId(String cardAccId) {
		this.cardAccId = cardAccId;
	}

	public String getCardAccTerminalId() {
		return cardAccTerminalId;
	}

	public void setCardAccTerminalId(String cardAccTerminalId) {
		this.cardAccTerminalId = cardAccTerminalId;
	}

	public String getCardAccTerminalLoc() {
		return cardAccTerminalLoc;
	}

	public void setCardAccTerminalLoc(String cardAccTerminalLoc) {
		this.cardAccTerminalLoc = cardAccTerminalLoc;
	}

	public String getAcquirerId() {
		return acquirerId;
	}

	public void setAcquirerId(String acquirerId) {
		this.acquirerId = acquirerId;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getAccount1Number() {
		return account1Number;
	}

	public void setAccount1Number(String account1Number) {
		this.account1Number = account1Number;
	}

	public String getAccount1BranchId() {
		return account1BranchId;
	}

	public void setAccount1BranchId(String account1BranchId) {
		this.account1BranchId = account1BranchId;
	}

	public String getAccount2Number() {
		return account2Number;
	}

	public void setAccount2Number(String account2Number) {
		this.account2Number = account2Number;
	}

	public String getAccount2BranchId() {
		return account2BranchId;
	}

	public void setAccount2BranchId(String account2BranchId) {
		this.account2BranchId = account2BranchId;
	}

	public String getTxnCurrencyCode() {
		return txnCurrencyCode;
	}

	public void setTxnCurrencyCode(String txnCurrencyCode) {
		this.txnCurrencyCode = txnCurrencyCode;
	}

	public String getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getActualTxnAmt() {
		return actualTxnAmt;
	}

	public void setActualTxnAmt(String actualTxnAmt) {
		this.actualTxnAmt = actualTxnAmt;
	}

	public String getTxnActivityFee() {
		return txnActivityFee;
	}

	public void setTxnActivityFee(String txnActivityFee) {
		this.txnActivityFee = txnActivityFee;
	}

	public String getIssSettleCurrencyCd() {
		return issSettleCurrencyCd;
	}

	public void setIssSettleCurrencyCd(String issSettleCurrencyCd) {
		this.issSettleCurrencyCd = issSettleCurrencyCd;
	}

	public String getIssSettleAmnt() {
		return issSettleAmnt;
	}

	public void setIssSettleAmnt(String issSettleAmnt) {
		this.issSettleAmnt = issSettleAmnt;
	}

	public String getIssSettleFee() {
		return issSettleFee;
	}

	public void setIssSettleFee(String issSettleFee) {
		this.issSettleFee = issSettleFee;
	}

	public String getIssSettleProcessFee() {
		return issSettleProcessFee;
	}

	public void setIssSettleProcessFee(String issSettleProcessFee) {
		this.issSettleProcessFee = issSettleProcessFee;
	}

	public String getCardholderBillCurrncyC() {
		return cardholderBillCurrncyC;
	}

	public void setCardholderBillCurrncyC(String cardholderBillCurrncyC) {
		this.cardholderBillCurrncyC = cardholderBillCurrncyC;
	}

	public String getCardholderBillAmount() {
		return cardholderBillAmount;
	}

	public void setCardholderBillAmount(String cardholderBillAmount) {
		this.cardholderBillAmount = cardholderBillAmount;
	}

	public String getCardholderBillActFee() {
		return cardholderBillActFee;
	}

	public void setCardholderBillActFee(String cardholderBillActFee) {
		this.cardholderBillActFee = cardholderBillActFee;
	}

	public String getCardholderBillProcessF() {
		return cardholderBillProcessF;
	}

	public void setCardholderBillProcessF(String cardholderBillProcessF) {
		this.cardholderBillProcessF = cardholderBillProcessF;
	}

	public String getCardholderBillServFee() {
		return cardholderBillServFee;
	}

	public void setCardholderBillServFee(String cardholderBillServFee) {
		this.cardholderBillServFee = cardholderBillServFee;
	}

	public String getTxnIssConversionRt() {
		return txnIssConversionRt;
	}

	public void setTxnIssConversionRt(String txnIssConversionRt) {
		this.txnIssConversionRt = txnIssConversionRt;
	}

	public String getTxnCardholderConvRt() {
		return txnCardholderConvRt;
	}

	public void setTxnCardholderConvRt(String txnCardholderConvRt) {
		this.txnCardholderConvRt = txnCardholderConvRt;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getDcrsTranNo() {
		return dcrsTranNo;
	}

	public void setDcrsTranNo(String dcrsTranNo) {
		this.dcrsTranNo = dcrsTranNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getFpan() {
		return fpan;
	}

	public void setFpan(String fpan) {
		this.fpan = fpan;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

    
}
