package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "rupay_international_adjustment") // Adjust table name if necessary
public class RupayInternationalAdjustmentRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unqId;

    @Column(length = 100)
    private String reportDate;

    @Column(length = 100)
    private String disputeRaiseDate;

    @Column(length = 100)
    private String disputeRaisedSettlDate;

    @Column(length = 100)
    private String caseNumber;

    @Column(length = 100)
    private String schemeName;

    @Column(length = 100)
    private String transactionFlag;

    @Column(length = 100)
    private String functionCode;

    @Column(length = 100)
    private String functionCodeDescription;

    @Column(length = 100)
    private String primaryAccountNumber;

    @Column(length = 100)
    private String processingCode;

    @Column(length = 100)
    private String transactionDate;

    @Column(length = 100)
    private String transactionAmount;

    @Column(length = 100)
    private String txnCurrencyCode;

    @Column(length = 100)
    private String settlementAmount;

    @Column(length = 100)
    private String settlementCcyCode;

    @Column(length = 100)
    private String conversionRate;

    @Column(length = 100)
    private String txnSettlementDate;

    @Column(length = 100)
    private String amountsAdditional;

    @Column(length = 100)
    private String controlNumber;

    @Column(length = 100)
    private String disputeOriginatorPid;

    @Column(length = 100)
    private String disputeDestinationPid;

    @Column(length = 100)
    private String acquireRefData;

    @Column(length = 100)
    private String approvalCode;

    @Column(length = 100)
    private String originatorPoint;

    @Column(length = 100)
    private String posEntryMode;

    @Column(length = 100)
    private String posConditionCode;

    @Column(length = 100)
    private String acquirerInstituteidCode;

    @Column(length = 100)
    private String acquirerNameCountry;

    @Column(length = 100)
    private String issuerInstiIdCode;

    @Column(length = 100)
    private String issuerNameCountry;

    @Column(length = 100)
    private String cardType;

    @Column(length = 100)
    private String cardBrand;

    @Column(length = 100)
    private String cardAcceptorTerminalid;

    @Column(length = 100)
    private String cardAcceptorName;

    @Column(length = 100)
    private String cardAcceptLocationAdd;

    @Column(length = 100)
    private String cardAcceptCountryCode;

    @Column(length = 100)
    private String cardAcceptBussCode;

    @Column(length = 100)
    private String disputeReasonCode;

    @Column(length = 100)
    private String disputeReasonCdDesc;

    @Column(length = 100)
    private String disputeAmt;

    @Column(length = 100)
    private String fullPartialIndicator;

    @Column(length = 100)
    private String disputeMemberMsgText;

    @Column(length = 100)
    private String disputeDocumentIndicator;

    @Column(length = 100)
    private String documentAttachedDate;

    @Column(length = 100)
    private String mti;

    @Column(length = 100)
    private String incentiveAmount;

    @Column(length = 100)
    private String tierCdNonfullfill;

    @Column(length = 100)
    private String tierCdFulfill;

    @Column(length = 100)
    private String deadlineDate;

    @Column(length = 100)
    private String daysToAct;

    @Column(length = 100)
    private String directionIwOw;

    private String fileDate;

    @Column(length = 100)
    private String createdBy;

    @Column(length = 100)
    private String cycle;

    @Column(length = 100)
    private String filename;

	public Long getUnqId() {
		return unqId;
	}

	public void setUnqId(Long unqId) {
		this.unqId = unqId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getDisputeRaiseDate() {
		return disputeRaiseDate;
	}

	public void setDisputeRaiseDate(String disputeRaiseDate) {
		this.disputeRaiseDate = disputeRaiseDate;
	}

	public String getDisputeRaisedSettlDate() {
		return disputeRaisedSettlDate;
	}

	public void setDisputeRaisedSettlDate(String disputeRaisedSettlDate) {
		this.disputeRaisedSettlDate = disputeRaisedSettlDate;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getTransactionFlag() {
		return transactionFlag;
	}

	public void setTransactionFlag(String transactionFlag) {
		this.transactionFlag = transactionFlag;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionCodeDescription() {
		return functionCodeDescription;
	}

	public void setFunctionCodeDescription(String functionCodeDescription) {
		this.functionCodeDescription = functionCodeDescription;
	}

	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTxnCurrencyCode() {
		return txnCurrencyCode;
	}

	public void setTxnCurrencyCode(String txnCurrencyCode) {
		this.txnCurrencyCode = txnCurrencyCode;
	}

	public String getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(String settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public String getSettlementCcyCode() {
		return settlementCcyCode;
	}

	public void setSettlementCcyCode(String settlementCcyCode) {
		this.settlementCcyCode = settlementCcyCode;
	}

	public String getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}

	public String getTxnSettlementDate() {
		return txnSettlementDate;
	}

	public void setTxnSettlementDate(String txnSettlementDate) {
		this.txnSettlementDate = txnSettlementDate;
	}

	public String getAmountsAdditional() {
		return amountsAdditional;
	}

	public void setAmountsAdditional(String amountsAdditional) {
		this.amountsAdditional = amountsAdditional;
	}

	public String getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	public String getDisputeOriginatorPid() {
		return disputeOriginatorPid;
	}

	public void setDisputeOriginatorPid(String disputeOriginatorPid) {
		this.disputeOriginatorPid = disputeOriginatorPid;
	}

	public String getDisputeDestinationPid() {
		return disputeDestinationPid;
	}

	public void setDisputeDestinationPid(String disputeDestinationPid) {
		this.disputeDestinationPid = disputeDestinationPid;
	}

	public String getAcquireRefData() {
		return acquireRefData;
	}

	public void setAcquireRefData(String acquireRefData) {
		this.acquireRefData = acquireRefData;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getOriginatorPoint() {
		return originatorPoint;
	}

	public void setOriginatorPoint(String originatorPoint) {
		this.originatorPoint = originatorPoint;
	}

	public String getPosEntryMode() {
		return posEntryMode;
	}

	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

	public String getPosConditionCode() {
		return posConditionCode;
	}

	public void setPosConditionCode(String posConditionCode) {
		this.posConditionCode = posConditionCode;
	}

	public String getAcquirerInstituteidCode() {
		return acquirerInstituteidCode;
	}

	public void setAcquirerInstituteidCode(String acquirerInstituteidCode) {
		this.acquirerInstituteidCode = acquirerInstituteidCode;
	}

	public String getAcquirerNameCountry() {
		return acquirerNameCountry;
	}

	public void setAcquirerNameCountry(String acquirerNameCountry) {
		this.acquirerNameCountry = acquirerNameCountry;
	}

	public String getIssuerInstiIdCode() {
		return issuerInstiIdCode;
	}

	public void setIssuerInstiIdCode(String issuerInstiIdCode) {
		this.issuerInstiIdCode = issuerInstiIdCode;
	}

	public String getIssuerNameCountry() {
		return issuerNameCountry;
	}

	public void setIssuerNameCountry(String issuerNameCountry) {
		this.issuerNameCountry = issuerNameCountry;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public String getCardAcceptorTerminalid() {
		return cardAcceptorTerminalid;
	}

	public void setCardAcceptorTerminalid(String cardAcceptorTerminalid) {
		this.cardAcceptorTerminalid = cardAcceptorTerminalid;
	}

	public String getCardAcceptorName() {
		return cardAcceptorName;
	}

	public void setCardAcceptorName(String cardAcceptorName) {
		this.cardAcceptorName = cardAcceptorName;
	}

	public String getCardAcceptLocationAdd() {
		return cardAcceptLocationAdd;
	}

	public void setCardAcceptLocationAdd(String cardAcceptLocationAdd) {
		this.cardAcceptLocationAdd = cardAcceptLocationAdd;
	}

	public String getCardAcceptCountryCode() {
		return cardAcceptCountryCode;
	}

	public void setCardAcceptCountryCode(String cardAcceptCountryCode) {
		this.cardAcceptCountryCode = cardAcceptCountryCode;
	}

	public String getCardAcceptBussCode() {
		return cardAcceptBussCode;
	}

	public void setCardAcceptBussCode(String cardAcceptBussCode) {
		this.cardAcceptBussCode = cardAcceptBussCode;
	}

	public String getDisputeReasonCode() {
		return disputeReasonCode;
	}

	public void setDisputeReasonCode(String disputeReasonCode) {
		this.disputeReasonCode = disputeReasonCode;
	}

	public String getDisputeReasonCdDesc() {
		return disputeReasonCdDesc;
	}

	public void setDisputeReasonCdDesc(String disputeReasonCdDesc) {
		this.disputeReasonCdDesc = disputeReasonCdDesc;
	}

	public String getDisputeAmt() {
		return disputeAmt;
	}

	public void setDisputeAmt(String disputeAmt) {
		this.disputeAmt = disputeAmt;
	}

	public String getFullPartialIndicator() {
		return fullPartialIndicator;
	}

	public void setFullPartialIndicator(String fullPartialIndicator) {
		this.fullPartialIndicator = fullPartialIndicator;
	}

	public String getDisputeMemberMsgText() {
		return disputeMemberMsgText;
	}

	public void setDisputeMemberMsgText(String disputeMemberMsgText) {
		this.disputeMemberMsgText = disputeMemberMsgText;
	}

	public String getDisputeDocumentIndicator() {
		return disputeDocumentIndicator;
	}

	public void setDisputeDocumentIndicator(String disputeDocumentIndicator) {
		this.disputeDocumentIndicator = disputeDocumentIndicator;
	}

	public String getDocumentAttachedDate() {
		return documentAttachedDate;
	}

	public void setDocumentAttachedDate(String documentAttachedDate) {
		this.documentAttachedDate = documentAttachedDate;
	}

	public String getMti() {
		return mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
	}

	public String getIncentiveAmount() {
		return incentiveAmount;
	}

	public void setIncentiveAmount(String incentiveAmount) {
		this.incentiveAmount = incentiveAmount;
	}

	public String getTierCdNonfullfill() {
		return tierCdNonfullfill;
	}

	public void setTierCdNonfullfill(String tierCdNonfullfill) {
		this.tierCdNonfullfill = tierCdNonfullfill;
	}

	public String getTierCdFulfill() {
		return tierCdFulfill;
	}

	public void setTierCdFulfill(String tierCdFulfill) {
		this.tierCdFulfill = tierCdFulfill;
	}

	public String getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getDaysToAct() {
		return daysToAct;
	}

	public void setDaysToAct(String daysToAct) {
		this.daysToAct = daysToAct;
	}

	public String getDirectionIwOw() {
		return directionIwOw;
	}

	public void setDirectionIwOw(String directionIwOw) {
		this.directionIwOw = directionIwOw;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
