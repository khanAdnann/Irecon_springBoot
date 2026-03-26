package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rupay_rupay_86_rawdata")
@Data
public class Rupay86DomesticRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70)
    private String messageType;
    @Column(length = 70)
    private String productId;
    @Column(length = 70)
    private String transactionType;
    @Column(length = 70)
    private String fromAccountType;
    @Column(length = 70)
    private String toAccountType;
    @Column(length = 70)
    private String actionCode;
    @Column(length = 70)
    private String responseCode;
    @Column(length = 70)
    private String panNumber;
    @Column(length = 70)
    private String approvalNumber;
    @Column(length = 70)
    private String retrivalReference;
    @Column(length = 70)
    private String transactionDate;
    @Column(length = 70)
    private String transactionTime;
    @Column(length = 70)
    private String merchantCatagoryCode;
    @Column(length = 70)
    private String cardAcceptorId;
    @Column(length = 70)
    private String cardAcceptorTerminalId;
    @Column(length = 70)
    private String cardAcceptorTerminalLocation;
    @Column(length = 70)
    private String acquirerId;
    @Column(length = 70)
    private String transactionCurrencyCode;
    @Column(length = 70)
    private String transactionAmount;
    @Column(length = 70)
    private String cardHolderBillingCurrency;
    @Column(length = 70)
    private String cardHolderBillingAmount;
    @Column(length = 70)
    private String panEntryMode;
    @Column(length = 70)
    private String panEntryCapability;
    @Column(length = 70)
    private String pinCaptureCode;
    @Column(length = 70)
    private String acquirerCountryCode;
    @Column(length = 70)
    private String additionalAmount;
    @Column(length = 70)
    private String rupayProduct;
    @Column(length = 70)
    private String cvd2MatchResult;
    @Column(length = 70)
    private String cvdICVDMatchResult;
    @Column(length = 70)
    private String reccuringPaymentIndicator;
    @Column(length = 70)
    private String eciIndicator;
    @Column(length = 70)
    private String ics1ResultCode;
    @Column(length = 70)
    private String fraudScore;
    @Column(length = 70)
    private String emiAmount;
    @Column(length = 70)
    private String arqcAuthorization;
    @Column(length = 70)
    private String transactionId;
    @Column(length = 70)
    private String loyaltyPoint;
    @Column(length = 70)
    private String ics2ResultCode;
    @Column(length = 70)
    private String custMobileNumber;
    @Column(length = 70)
    private String imageCode;
    @Column(length = 70)
    private String personalPhase;
    @Column(length = 70)
    private String uidNumber;
    @Column(length = 70)
    private String cardDataInputCapability;
    @Column(length = 70)
    private String cardHolderAuthCapability;
    @Column(length = 70)
    private String cardCaptureCapability;
    @Column(length = 70)
    private String terminalOperatingEnviroment;
    @Column(length = 70)
    private String cardPresentData;
    @Column(length = 70)
    private String cardHolderPresentData;
    @Column(length = 70)
    private String cardDataInputMode;
    @Column(length = 70)
    private String cardHolderAuthMode;
    @Column(length = 70)
    private String cardHolderAuthEntity;
    @Column(length = 70)
    private String cardDataOpCapability;
    @Column(length = 70)
    private String terminalDataOpCapability;
    @Column(length = 70)
    private String pinCaptureCapability;
    @Column(length = 70)
    private String zipCode;
    @Column(length = 70)
    private String adviceReasonCode;
    @Column(length = 70)
    private String itPan;
    @Column(length = 70)
    private String intrauthnw;
    @Column(length = 70)
    private String otpIndicator;
    @Column(length = 70)
    private String icsTxnId;
    @Column(length = 70)
    private String nwData;
    @Column(length = 70)
    private String serviceCode;
    @Column(length = 70)
    private String currencyCodeActualTxnAmt;
    @Column(length = 70)
    private String actualTxnAmt;
    @Column(length = 70)
    private String fileDate;
    @Column(length = 70)
    private String filename;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
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
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public String getRetrivalReference() {
		return retrivalReference;
	}
	public void setRetrivalReference(String retrivalReference) {
		this.retrivalReference = retrivalReference;
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
	public String getMerchantCatagoryCode() {
		return merchantCatagoryCode;
	}
	public void setMerchantCatagoryCode(String merchantCatagoryCode) {
		this.merchantCatagoryCode = merchantCatagoryCode;
	}
	public String getCardAcceptorId() {
		return cardAcceptorId;
	}
	public void setCardAcceptorId(String cardAcceptorId) {
		this.cardAcceptorId = cardAcceptorId;
	}
	public String getCardAcceptorTerminalId() {
		return cardAcceptorTerminalId;
	}
	public void setCardAcceptorTerminalId(String cardAcceptorTerminalId) {
		this.cardAcceptorTerminalId = cardAcceptorTerminalId;
	}
	public String getCardAcceptorTerminalLocation() {
		return cardAcceptorTerminalLocation;
	}
	public void setCardAcceptorTerminalLocation(String cardAcceptorTerminalLocation) {
		this.cardAcceptorTerminalLocation = cardAcceptorTerminalLocation;
	}
	public String getAcquirerId() {
		return acquirerId;
	}
	public void setAcquirerId(String acquirerId) {
		this.acquirerId = acquirerId;
	}
	public String getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}
	public void setTransactionCurrencyCode(String transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getCardHolderBillingCurrency() {
		return cardHolderBillingCurrency;
	}
	public void setCardHolderBillingCurrency(String cardHolderBillingCurrency) {
		this.cardHolderBillingCurrency = cardHolderBillingCurrency;
	}
	public String getCardHolderBillingAmount() {
		return cardHolderBillingAmount;
	}
	public void setCardHolderBillingAmount(String cardHolderBillingAmount) {
		this.cardHolderBillingAmount = cardHolderBillingAmount;
	}
	public String getPanEntryMode() {
		return panEntryMode;
	}
	public void setPanEntryMode(String panEntryMode) {
		this.panEntryMode = panEntryMode;
	}
	public String getPanEntryCapability() {
		return panEntryCapability;
	}
	public void setPanEntryCapability(String panEntryCapability) {
		this.panEntryCapability = panEntryCapability;
	}
	public String getPinCaptureCode() {
		return pinCaptureCode;
	}
	public void setPinCaptureCode(String pinCaptureCode) {
		this.pinCaptureCode = pinCaptureCode;
	}
	public String getAcquirerCountryCode() {
		return acquirerCountryCode;
	}
	public void setAcquirerCountryCode(String acquirerCountryCode) {
		this.acquirerCountryCode = acquirerCountryCode;
	}
	public String getAdditionalAmount() {
		return additionalAmount;
	}
	public void setAdditionalAmount(String additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
	public String getRupayProduct() {
		return rupayProduct;
	}
	public void setRupayProduct(String rupayProduct) {
		this.rupayProduct = rupayProduct;
	}
	public String getCvd2MatchResult() {
		return cvd2MatchResult;
	}
	public void setCvd2MatchResult(String cvd2MatchResult) {
		this.cvd2MatchResult = cvd2MatchResult;
	}
	public String getCvdICVDMatchResult() {
		return cvdICVDMatchResult;
	}
	public void setCvdICVDMatchResult(String cvdICVDMatchResult) {
		this.cvdICVDMatchResult = cvdICVDMatchResult;
	}
	public String getReccuringPaymentIndicator() {
		return reccuringPaymentIndicator;
	}
	public void setReccuringPaymentIndicator(String reccuringPaymentIndicator) {
		this.reccuringPaymentIndicator = reccuringPaymentIndicator;
	}
	public String getEciIndicator() {
		return eciIndicator;
	}
	public void setEciIndicator(String eciIndicator) {
		this.eciIndicator = eciIndicator;
	}
	public String getIcs1ResultCode() {
		return ics1ResultCode;
	}
	public void setIcs1ResultCode(String ics1ResultCode) {
		this.ics1ResultCode = ics1ResultCode;
	}
	public String getFraudScore() {
		return fraudScore;
	}
	public void setFraudScore(String fraudScore) {
		this.fraudScore = fraudScore;
	}
	public String getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}
	public String getArqcAuthorization() {
		return arqcAuthorization;
	}
	public void setArqcAuthorization(String arqcAuthorization) {
		this.arqcAuthorization = arqcAuthorization;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getLoyaltyPoint() {
		return loyaltyPoint;
	}
	public void setLoyaltyPoint(String loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}
	public String getIcs2ResultCode() {
		return ics2ResultCode;
	}
	public void setIcs2ResultCode(String ics2ResultCode) {
		this.ics2ResultCode = ics2ResultCode;
	}
	public String getCustMobileNumber() {
		return custMobileNumber;
	}
	public void setCustMobileNumber(String custMobileNumber) {
		this.custMobileNumber = custMobileNumber;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getPersonalPhase() {
		return personalPhase;
	}
	public void setPersonalPhase(String personalPhase) {
		this.personalPhase = personalPhase;
	}
	public String getUidNumber() {
		return uidNumber;
	}
	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}
	public String getCardDataInputCapability() {
		return cardDataInputCapability;
	}
	public void setCardDataInputCapability(String cardDataInputCapability) {
		this.cardDataInputCapability = cardDataInputCapability;
	}
	public String getCardHolderAuthCapability() {
		return cardHolderAuthCapability;
	}
	public void setCardHolderAuthCapability(String cardHolderAuthCapability) {
		this.cardHolderAuthCapability = cardHolderAuthCapability;
	}
	public String getCardCaptureCapability() {
		return cardCaptureCapability;
	}
	public void setCardCaptureCapability(String cardCaptureCapability) {
		this.cardCaptureCapability = cardCaptureCapability;
	}
	public String getTerminalOperatingEnviroment() {
		return terminalOperatingEnviroment;
	}
	public void setTerminalOperatingEnviroment(String terminalOperatingEnviroment) {
		this.terminalOperatingEnviroment = terminalOperatingEnviroment;
	}
	public String getCardPresentData() {
		return cardPresentData;
	}
	public void setCardPresentData(String cardPresentData) {
		this.cardPresentData = cardPresentData;
	}
	public String getCardHolderPresentData() {
		return cardHolderPresentData;
	}
	public void setCardHolderPresentData(String cardHolderPresentData) {
		this.cardHolderPresentData = cardHolderPresentData;
	}
	public String getCardDataInputMode() {
		return cardDataInputMode;
	}
	public void setCardDataInputMode(String cardDataInputMode) {
		this.cardDataInputMode = cardDataInputMode;
	}
	public String getCardHolderAuthMode() {
		return cardHolderAuthMode;
	}
	public void setCardHolderAuthMode(String cardHolderAuthMode) {
		this.cardHolderAuthMode = cardHolderAuthMode;
	}
	public String getCardHolderAuthEntity() {
		return cardHolderAuthEntity;
	}
	public void setCardHolderAuthEntity(String cardHolderAuthEntity) {
		this.cardHolderAuthEntity = cardHolderAuthEntity;
	}
	public String getCardDataOpCapability() {
		return cardDataOpCapability;
	}
	public void setCardDataOpCapability(String cardDataOpCapability) {
		this.cardDataOpCapability = cardDataOpCapability;
	}
	public String getTerminalDataOpCapability() {
		return terminalDataOpCapability;
	}
	public void setTerminalDataOpCapability(String terminalDataOpCapability) {
		this.terminalDataOpCapability = terminalDataOpCapability;
	}
	public String getPinCaptureCapability() {
		return pinCaptureCapability;
	}
	public void setPinCaptureCapability(String pinCaptureCapability) {
		this.pinCaptureCapability = pinCaptureCapability;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAdviceReasonCode() {
		return adviceReasonCode;
	}
	public void setAdviceReasonCode(String adviceReasonCode) {
		this.adviceReasonCode = adviceReasonCode;
	}
	public String getItPan() {
		return itPan;
	}
	public void setItPan(String itPan) {
		this.itPan = itPan;
	}
	public String getIntrauthnw() {
		return intrauthnw;
	}
	public void setIntrauthnw(String intrauthnw) {
		this.intrauthnw = intrauthnw;
	}
	public String getOtpIndicator() {
		return otpIndicator;
	}
	public void setOtpIndicator(String otpIndicator) {
		this.otpIndicator = otpIndicator;
	}
	public String getIcsTxnId() {
		return icsTxnId;
	}
	public void setIcsTxnId(String icsTxnId) {
		this.icsTxnId = icsTxnId;
	}
	public String getNwData() {
		return nwData;
	}
	public void setNwData(String nwData) {
		this.nwData = nwData;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getCurrencyCodeActualTxnAmt() {
		return currencyCodeActualTxnAmt;
	}
	public void setCurrencyCodeActualTxnAmt(String currencyCodeActualTxnAmt) {
		this.currencyCodeActualTxnAmt = currencyCodeActualTxnAmt;
	}
	public String getActualTxnAmt() {
		return actualTxnAmt;
	}
	public void setActualTxnAmt(String actualTxnAmt) {
		this.actualTxnAmt = actualTxnAmt;
	}
	
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
    
    
}
