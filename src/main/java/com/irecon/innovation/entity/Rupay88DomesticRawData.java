package com.irecon.innovation.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "rupay_rupay_88_rawdata")
@Data
@NoArgsConstructor
public class Rupay88DomesticRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Add an auto-generated ID if the table doesn't already have a PK.

    @Column(length = 70) 
    private String mti;

    @Column(length = 70) 
    private String functionCode;

    @Column(length = 70) 
    private String recordNumber;

    @Column(length = 70) 
    private String memberInstitutionIdCode;

    @Column(length = 70) 
    private String uniqueFileName;
    @Column(length = 70) 
 
    private String dateSettlement;

    @Column(length = 70) 
    private String productCode;

    @Column(length = 70) 
    private String settlementBIN;

    @Column(length = 70) 
    private String fileCategory;

    @Column(length = 70) 
    private String versionNumber;

    @Column(length = 70) 
    private String entireFileRejectIndicator;

    @Column(length = 70) 
    private String fileRejectReasonCode;
    @Column(length = 70) 
    private String transactionsCount;

    @Column(length = 70) 
    private String runTotalAmount;

    @Column(length = 70) 
    private String acquirerInstitutionIdCode;
    @Column(length = 70) 
    private String amountSettlement;

    @Column(length = 70) 
    private String amountTransaction;

    @Column(length = 70) 
    private String approvalCode;

    @Column(length = 70) 
    private String acquirerReferenceData;

    @Column(length = 70) 
    private String caseNumber;

    @Column(length = 70) 
    private String currencyCodeSettlement;

    @Column(length = 70) 
    private String currencyCodeTransaction;

    @Column(length = 70) 
    private String conversionRateSettlement;

    @Column(length = 70) 
    private String cardAcceptorAddiAddr;

    @Column(length = 70) 
    private String cardAcceptorTerminalID;

    @Column(length = 70) 
    private String cardAcceptorZipCode;

    @Column(length = 70) 
    private String dateAndTimeLocalTransaction;
    @Column(length = 70) 
    private String txnFunctionCode;

    @Column(length = 70) 
    private String latePresentmentIndicator;

    @Column(length = 70) 
    private String txnMTI;

    @Column(length = 70) 
    private String primaryAccountNumber;

    @Column(length = 70) 
    private String txnRecordNumber;

    @Column(length = 70) 
  
    private String rgcsReceivedDate;

    @Column(length = 70) 
    private String settlementDRCRIndicator;
    @Column(length = 70) 
    private String txnDestiInstiIdCode;
    @Column(length = 70) 
    private String txnOriginInstiIdCode;

    @Column(length = 70) 
    private String cardHolderUID;

    @Column(length = 70) 
    private String amountBilling;

    @Column(length = 70) 
    private String currencyCodeBilling;

    @Column(length = 70) 
    private String conversionRateBilling;

    @Column(length = 70) 
    private String messageReasonCode;
    @Column(length = 70) 
    private String feeDRCRIndicator1;

    @Column(length = 70) 
    private String feeAmount1;
    @Column(length = 70) 
    private String feeCurrency1;

    @Column(length = 70) 
    private String feeTypeCode1;
    @Column(length = 70) 
    private String interchangeCategory1;

    @Column(length = 70) 
    private String feeDRCRIndicator2;

    @Column(length = 70) 
    private String feeAmount2;
    @Column(length = 70) 
    private String feeCurrency2;

    @Column(length = 70) 
    private String feeTypeCode2;
    @Column(length = 70) 
    private String interchangeCategory2;

    @Column(length = 70) 
    private String feeDRCRIndicator3;
    @Column(length = 70) 
    private String feeAmount3;
    @Column(length = 70) 
    private String feeCurrency3;
    @Column(length = 70) 
    private String feeTypeCode3;

    @Column(length = 70) 
    private String interchangeCategory3;

    @Column(length = 70) 
    private String feeDRCRIndicator4;
    @Column(length = 70) 
    private String feeAmount4;

    @Column(length = 70) 
    private String feeCurrency4;
    @Column(length = 70) 
    private String feeTypeCode4;

    @Column(length = 70) 
    private String interchangeCategory4;

    @Column(length = 70) 
    private String feeDRCRIndicator5;

    @Column(length = 70) 
    private String feeAmount5;

    @Column(length = 70) 
    private String feeCurrency5;
    @Column(length = 70) 
    private String feeTypeCode5;

    @Column(length = 70) 
    private String interchangeCategory5;

    @Column(length = 70) 
    private String trlFunctionCode;
    @Column(length = 70) 
    private String trlRecordNumber;

    @Column(length = 70) 
    private String flag;

    @Column(length = 70) 
    private String fileDate;

    @Column(length = 70) 
    private String pan;
    @Column(length = 70) 
    private Date createdDate;

    @Column(length = 70) 
    private String rrn;

    @Column(length = 70) 
    private String filename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMti() {
		return mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getMemberInstitutionIdCode() {
		return memberInstitutionIdCode;
	}

	public void setMemberInstitutionIdCode(String memberInstitutionIdCode) {
		this.memberInstitutionIdCode = memberInstitutionIdCode;
	}

	public String getUniqueFileName() {
		return uniqueFileName;
	}

	public void setUniqueFileName(String uniqueFileName) {
		this.uniqueFileName = uniqueFileName;
	}

	public String getDateSettlement() {
		return dateSettlement;
	}

	public void setDateSettlement(String dateSettlement) {
		this.dateSettlement = dateSettlement;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSettlementBIN() {
		return settlementBIN;
	}

	public void setSettlementBIN(String settlementBIN) {
		this.settlementBIN = settlementBIN;
	}

	public String getFileCategory() {
		return fileCategory;
	}

	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getEntireFileRejectIndicator() {
		return entireFileRejectIndicator;
	}

	public void setEntireFileRejectIndicator(String entireFileRejectIndicator) {
		this.entireFileRejectIndicator = entireFileRejectIndicator;
	}

	public String getFileRejectReasonCode() {
		return fileRejectReasonCode;
	}

	public void setFileRejectReasonCode(String fileRejectReasonCode) {
		this.fileRejectReasonCode = fileRejectReasonCode;
	}

	public String getTransactionsCount() {
		return transactionsCount;
	}

	public void setTransactionsCount(String transactionsCount) {
		this.transactionsCount = transactionsCount;
	}

	public String getRunTotalAmount() {
		return runTotalAmount;
	}

	public void setRunTotalAmount(String runTotalAmount) {
		this.runTotalAmount = runTotalAmount;
	}

	public String getAcquirerInstitutionIdCode() {
		return acquirerInstitutionIdCode;
	}

	public void setAcquirerInstitutionIdCode(String acquirerInstitutionIdCode) {
		this.acquirerInstitutionIdCode = acquirerInstitutionIdCode;
	}

	public String getAmountSettlement() {
		return amountSettlement;
	}

	public void setAmountSettlement(String amountSettlement) {
		this.amountSettlement = amountSettlement;
	}

	public String getAmountTransaction() {
		return amountTransaction;
	}

	public void setAmountTransaction(String amountTransaction) {
		this.amountTransaction = amountTransaction;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getAcquirerReferenceData() {
		return acquirerReferenceData;
	}

	public void setAcquirerReferenceData(String acquirerReferenceData) {
		this.acquirerReferenceData = acquirerReferenceData;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCurrencyCodeSettlement() {
		return currencyCodeSettlement;
	}

	public void setCurrencyCodeSettlement(String currencyCodeSettlement) {
		this.currencyCodeSettlement = currencyCodeSettlement;
	}

	public String getCurrencyCodeTransaction() {
		return currencyCodeTransaction;
	}

	public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
		this.currencyCodeTransaction = currencyCodeTransaction;
	}

	public String getConversionRateSettlement() {
		return conversionRateSettlement;
	}

	public void setConversionRateSettlement(String conversionRateSettlement) {
		this.conversionRateSettlement = conversionRateSettlement;
	}

	public String getCardAcceptorAddiAddr() {
		return cardAcceptorAddiAddr;
	}

	public void setCardAcceptorAddiAddr(String cardAcceptorAddiAddr) {
		this.cardAcceptorAddiAddr = cardAcceptorAddiAddr;
	}

	public String getCardAcceptorTerminalID() {
		return cardAcceptorTerminalID;
	}

	public void setCardAcceptorTerminalID(String cardAcceptorTerminalID) {
		this.cardAcceptorTerminalID = cardAcceptorTerminalID;
	}

	public String getCardAcceptorZipCode() {
		return cardAcceptorZipCode;
	}

	public void setCardAcceptorZipCode(String cardAcceptorZipCode) {
		this.cardAcceptorZipCode = cardAcceptorZipCode;
	}

	public String getDateAndTimeLocalTransaction() {
		return dateAndTimeLocalTransaction;
	}

	public void setDateAndTimeLocalTransaction(String dateAndTimeLocalTransaction) {
		this.dateAndTimeLocalTransaction = dateAndTimeLocalTransaction;
	}

	public String getTxnFunctionCode() {
		return txnFunctionCode;
	}

	public void setTxnFunctionCode(String txnFunctionCode) {
		this.txnFunctionCode = txnFunctionCode;
	}

	public String getLatePresentmentIndicator() {
		return latePresentmentIndicator;
	}

	public void setLatePresentmentIndicator(String latePresentmentIndicator) {
		this.latePresentmentIndicator = latePresentmentIndicator;
	}

	public String getTxnMTI() {
		return txnMTI;
	}

	public void setTxnMTI(String txnMTI) {
		this.txnMTI = txnMTI;
	}

	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public String getTxnRecordNumber() {
		return txnRecordNumber;
	}

	public void setTxnRecordNumber(String txnRecordNumber) {
		this.txnRecordNumber = txnRecordNumber;
	}

	public String getRgcsReceivedDate() {
		return rgcsReceivedDate;
	}

	public void setRgcsReceivedDate(String rgcsReceivedDate) {
		this.rgcsReceivedDate = rgcsReceivedDate;
	}

	public String getSettlementDRCRIndicator() {
		return settlementDRCRIndicator;
	}

	public void setSettlementDRCRIndicator(String settlementDRCRIndicator) {
		this.settlementDRCRIndicator = settlementDRCRIndicator;
	}

	public String getTxnDestiInstiIdCode() {
		return txnDestiInstiIdCode;
	}

	public void setTxnDestiInstiIdCode(String txnDestiInstiIdCode) {
		this.txnDestiInstiIdCode = txnDestiInstiIdCode;
	}

	public String getTxnOriginInstiIdCode() {
		return txnOriginInstiIdCode;
	}

	public void setTxnOriginInstiIdCode(String txnOriginInstiIdCode) {
		this.txnOriginInstiIdCode = txnOriginInstiIdCode;
	}

	public String getCardHolderUID() {
		return cardHolderUID;
	}

	public void setCardHolderUID(String cardHolderUID) {
		this.cardHolderUID = cardHolderUID;
	}

	public String getAmountBilling() {
		return amountBilling;
	}

	public void setAmountBilling(String amountBilling) {
		this.amountBilling = amountBilling;
	}

	public String getCurrencyCodeBilling() {
		return currencyCodeBilling;
	}

	public void setCurrencyCodeBilling(String currencyCodeBilling) {
		this.currencyCodeBilling = currencyCodeBilling;
	}

	public String getConversionRateBilling() {
		return conversionRateBilling;
	}

	public void setConversionRateBilling(String conversionRateBilling) {
		this.conversionRateBilling = conversionRateBilling;
	}

	public String getMessageReasonCode() {
		return messageReasonCode;
	}

	public void setMessageReasonCode(String messageReasonCode) {
		this.messageReasonCode = messageReasonCode;
	}

	public String getFeeDRCRIndicator1() {
		return feeDRCRIndicator1;
	}

	public void setFeeDRCRIndicator1(String feeDRCRIndicator1) {
		this.feeDRCRIndicator1 = feeDRCRIndicator1;
	}

	public String getFeeAmount1() {
		return feeAmount1;
	}

	public void setFeeAmount1(String feeAmount1) {
		this.feeAmount1 = feeAmount1;
	}

	public String getFeeCurrency1() {
		return feeCurrency1;
	}

	public void setFeeCurrency1(String feeCurrency1) {
		this.feeCurrency1 = feeCurrency1;
	}

	public String getFeeTypeCode1() {
		return feeTypeCode1;
	}

	public void setFeeTypeCode1(String feeTypeCode1) {
		this.feeTypeCode1 = feeTypeCode1;
	}

	public String getInterchangeCategory1() {
		return interchangeCategory1;
	}

	public void setInterchangeCategory1(String interchangeCategory1) {
		this.interchangeCategory1 = interchangeCategory1;
	}

	public String getFeeDRCRIndicator2() {
		return feeDRCRIndicator2;
	}

	public void setFeeDRCRIndicator2(String feeDRCRIndicator2) {
		this.feeDRCRIndicator2 = feeDRCRIndicator2;
	}

	public String getFeeAmount2() {
		return feeAmount2;
	}

	public void setFeeAmount2(String feeAmount2) {
		this.feeAmount2 = feeAmount2;
	}

	public String getFeeCurrency2() {
		return feeCurrency2;
	}

	public void setFeeCurrency2(String feeCurrency2) {
		this.feeCurrency2 = feeCurrency2;
	}

	public String getFeeTypeCode2() {
		return feeTypeCode2;
	}

	public void setFeeTypeCode2(String feeTypeCode2) {
		this.feeTypeCode2 = feeTypeCode2;
	}

	public String getInterchangeCategory2() {
		return interchangeCategory2;
	}

	public void setInterchangeCategory2(String interchangeCategory2) {
		this.interchangeCategory2 = interchangeCategory2;
	}

	public String getFeeDRCRIndicator3() {
		return feeDRCRIndicator3;
	}

	public void setFeeDRCRIndicator3(String feeDRCRIndicator3) {
		this.feeDRCRIndicator3 = feeDRCRIndicator3;
	}

	public String getFeeAmount3() {
		return feeAmount3;
	}

	public void setFeeAmount3(String feeAmount3) {
		this.feeAmount3 = feeAmount3;
	}

	public String getFeeCurrency3() {
		return feeCurrency3;
	}

	public void setFeeCurrency3(String feeCurrency3) {
		this.feeCurrency3 = feeCurrency3;
	}

	public String getFeeTypeCode3() {
		return feeTypeCode3;
	}

	public void setFeeTypeCode3(String feeTypeCode3) {
		this.feeTypeCode3 = feeTypeCode3;
	}

	public String getInterchangeCategory3() {
		return interchangeCategory3;
	}

	public void setInterchangeCategory3(String interchangeCategory3) {
		this.interchangeCategory3 = interchangeCategory3;
	}

	public String getFeeDRCRIndicator4() {
		return feeDRCRIndicator4;
	}

	public void setFeeDRCRIndicator4(String feeDRCRIndicator4) {
		this.feeDRCRIndicator4 = feeDRCRIndicator4;
	}

	public String getFeeAmount4() {
		return feeAmount4;
	}

	public void setFeeAmount4(String feeAmount4) {
		this.feeAmount4 = feeAmount4;
	}

	public String getFeeCurrency4() {
		return feeCurrency4;
	}

	public void setFeeCurrency4(String feeCurrency4) {
		this.feeCurrency4 = feeCurrency4;
	}

	public String getFeeTypeCode4() {
		return feeTypeCode4;
	}

	public void setFeeTypeCode4(String feeTypeCode4) {
		this.feeTypeCode4 = feeTypeCode4;
	}

	public String getInterchangeCategory4() {
		return interchangeCategory4;
	}

	public void setInterchangeCategory4(String interchangeCategory4) {
		this.interchangeCategory4 = interchangeCategory4;
	}

	public String getFeeDRCRIndicator5() {
		return feeDRCRIndicator5;
	}

	public void setFeeDRCRIndicator5(String feeDRCRIndicator5) {
		this.feeDRCRIndicator5 = feeDRCRIndicator5;
	}

	public String getFeeAmount5() {
		return feeAmount5;
	}

	public void setFeeAmount5(String feeAmount5) {
		this.feeAmount5 = feeAmount5;
	}

	public String getFeeCurrency5() {
		return feeCurrency5;
	}

	public void setFeeCurrency5(String feeCurrency5) {
		this.feeCurrency5 = feeCurrency5;
	}

	public String getFeeTypeCode5() {
		return feeTypeCode5;
	}

	public void setFeeTypeCode5(String feeTypeCode5) {
		this.feeTypeCode5 = feeTypeCode5;
	}

	public String getInterchangeCategory5() {
		return interchangeCategory5;
	}

	public void setInterchangeCategory5(String interchangeCategory5) {
		this.interchangeCategory5 = interchangeCategory5;
	}

	public String getTrlFunctionCode() {
		return trlFunctionCode;
	}

	public void setTrlFunctionCode(String trlFunctionCode) {
		this.trlFunctionCode = trlFunctionCode;
	}

	public String getTrlRecordNumber() {
		return trlRecordNumber;
	}

	public void setTrlRecordNumber(String trlRecordNumber) {
		this.trlRecordNumber = trlRecordNumber;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
    

}
