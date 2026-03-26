package com.irecon.innovation.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mastercard_atm_rawdata")
public class MastercardAtmRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String msgTypeInd;

    @Column(length = 100)
    private String switchSerialNum;

    @Column(length = 100)
    private String processorAcqOrIss;

    @Column(length = 100)
    private String processorId;

    @Column(length = 100)
    private String transactionDate;

    @Column(length = 100)
    private String transactionTime;

    @Column(length = 100)
    private String panLength;

    @Column(length = 100)
    private String pan;

    @Column(length = 100)
    private String processingCode;

    @Column(length = 100)
    private String traceNumber;

    @Column(length = 100)
    private String merchantType;

    @Column(length = 100)
    private String posEntry;

    @Column(length = 100)
    private String referenceNumber;

    @Column(length = 100)
    private String acquirerInstitutionId;

    @Column(length = 100)
    private String terminalId;

    @Column(length = 100)
    private String responseCode;

    @Column(length = 100)
    private String brand;

    @Column(length = 100)
    private String adviceReasonCode;

    @Column(length = 100)
    private String interacurrencyAgreementCode;

    @Column(length = 100)
    private String authorizationId;

    @Column(length = 100)
    private String currencyCode;

    @Column(length = 100)
    private String impliedDecimal;

    @Column(length = 100)
    private String completedAmtTrans;

    @Column(length = 100)
    private String completedAmtIndicator;

    @Column(length = 100)
    private String cashBackAmt;

    @Column(length = 100)
    private String cashBackIndicator;

    @Column(length = 100)
    private String accessFee;

    @Column(length = 100)
    private String accessFeeIndicator;

    @Column(length = 100)
    private String currencyCodeSettlement;

    @Column(length = 100)
    private String impliedDecimalSettlement;

    @Column(length = 100)
    private String conversionRateSettlement;

    @Column(length = 100)
    private String completedAmtSettlement;

    @Column(length = 100)
    private String completedAmtSettlementIndicator;

    @Column(length = 100)
    private String interchangeFee;

    @Column(length = 100)
    private String interchangeFeeIndicator;

    @Column(length = 100)
    private String serviceLevelIndicator;

    @Column(length = 100)
    private String responseCode2;

    @Column(length = 100)
    private String filler;

    @Column(length = 100)
    private String positiveIdIndicator;

    @Column(length = 100)
    private String atmSurchargeFreeProgramId;

    @Column(length = 100)
    private String crossBorderIndicator;

    @Column(length = 100)
    private String crossBorderCurrencyIndicator;

    @Column(length = 100)
    private String visaIsaFeeIndicator;

    @Column(length = 100)
    private String requestAtmTransLocal;

    @Column(length = 100)
    private String fillerAdj;

    @Column(length = 100)
    private String traceNumberAdjustmentTrans;

    @Column(length = 100)
    private String reconActivity;

    @Column(length = 100)
    private String fileDate;

    @Column(length = 100)
    private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgTypeInd() {
		return msgTypeInd;
	}

	public void setMsgTypeInd(String msgTypeInd) {
		this.msgTypeInd = msgTypeInd;
	}

	public String getSwitchSerialNum() {
		return switchSerialNum;
	}

	public void setSwitchSerialNum(String switchSerialNum) {
		this.switchSerialNum = switchSerialNum;
	}

	public String getProcessorAcqOrIss() {
		return processorAcqOrIss;
	}

	public void setProcessorAcqOrIss(String processorAcqOrIss) {
		this.processorAcqOrIss = processorAcqOrIss;
	}

	public String getProcessorId() {
		return processorId;
	}

	public void setProcessorId(String processorId) {
		this.processorId = processorId;
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

	public String getPanLength() {
		return panLength;
	}

	public void setPanLength(String panLength) {
		this.panLength = panLength;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getTraceNumber() {
		return traceNumber;
	}

	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getPosEntry() {
		return posEntry;
	}

	public void setPosEntry(String posEntry) {
		this.posEntry = posEntry;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getAcquirerInstitutionId() {
		return acquirerInstitutionId;
	}

	public void setAcquirerInstitutionId(String acquirerInstitutionId) {
		this.acquirerInstitutionId = acquirerInstitutionId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAdviceReasonCode() {
		return adviceReasonCode;
	}

	public void setAdviceReasonCode(String adviceReasonCode) {
		this.adviceReasonCode = adviceReasonCode;
	}

	public String getInteracurrencyAgreementCode() {
		return interacurrencyAgreementCode;
	}

	public void setInteracurrencyAgreementCode(String interacurrencyAgreementCode) {
		this.interacurrencyAgreementCode = interacurrencyAgreementCode;
	}

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getImpliedDecimal() {
		return impliedDecimal;
	}

	public void setImpliedDecimal(String impliedDecimal) {
		this.impliedDecimal = impliedDecimal;
	}

	public String getCompletedAmtTrans() {
		return completedAmtTrans;
	}

	public void setCompletedAmtTrans(String completedAmtTrans) {
		this.completedAmtTrans = completedAmtTrans;
	}

	public String getCompletedAmtIndicator() {
		return completedAmtIndicator;
	}

	public void setCompletedAmtIndicator(String completedAmtIndicator) {
		this.completedAmtIndicator = completedAmtIndicator;
	}

	public String getCashBackAmt() {
		return cashBackAmt;
	}

	public void setCashBackAmt(String cashBackAmt) {
		this.cashBackAmt = cashBackAmt;
	}

	public String getCashBackIndicator() {
		return cashBackIndicator;
	}

	public void setCashBackIndicator(String cashBackIndicator) {
		this.cashBackIndicator = cashBackIndicator;
	}

	public String getAccessFee() {
		return accessFee;
	}

	public void setAccessFee(String accessFee) {
		this.accessFee = accessFee;
	}

	public String getAccessFeeIndicator() {
		return accessFeeIndicator;
	}

	public void setAccessFeeIndicator(String accessFeeIndicator) {
		this.accessFeeIndicator = accessFeeIndicator;
	}

	public String getCurrencyCodeSettlement() {
		return currencyCodeSettlement;
	}

	public void setCurrencyCodeSettlement(String currencyCodeSettlement) {
		this.currencyCodeSettlement = currencyCodeSettlement;
	}

	public String getImpliedDecimalSettlement() {
		return impliedDecimalSettlement;
	}

	public void setImpliedDecimalSettlement(String impliedDecimalSettlement) {
		this.impliedDecimalSettlement = impliedDecimalSettlement;
	}

	public String getConversionRateSettlement() {
		return conversionRateSettlement;
	}

	public void setConversionRateSettlement(String conversionRateSettlement) {
		this.conversionRateSettlement = conversionRateSettlement;
	}

	public String getCompletedAmtSettlement() {
		return completedAmtSettlement;
	}

	public void setCompletedAmtSettlement(String completedAmtSettlement) {
		this.completedAmtSettlement = completedAmtSettlement;
	}

	public String getCompletedAmtSettlementIndicator() {
		return completedAmtSettlementIndicator;
	}

	public void setCompletedAmtSettlementIndicator(String completedAmtSettlementIndicator) {
		this.completedAmtSettlementIndicator = completedAmtSettlementIndicator;
	}

	public String getInterchangeFee() {
		return interchangeFee;
	}

	public void setInterchangeFee(String interchangeFee) {
		this.interchangeFee = interchangeFee;
	}

	public String getInterchangeFeeIndicator() {
		return interchangeFeeIndicator;
	}

	public void setInterchangeFeeIndicator(String interchangeFeeIndicator) {
		this.interchangeFeeIndicator = interchangeFeeIndicator;
	}

	public String getServiceLevelIndicator() {
		return serviceLevelIndicator;
	}

	public void setServiceLevelIndicator(String serviceLevelIndicator) {
		this.serviceLevelIndicator = serviceLevelIndicator;
	}

	public String getResponseCode2() {
		return responseCode2;
	}

	public void setResponseCode2(String responseCode2) {
		this.responseCode2 = responseCode2;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getPositiveIdIndicator() {
		return positiveIdIndicator;
	}

	public void setPositiveIdIndicator(String positiveIdIndicator) {
		this.positiveIdIndicator = positiveIdIndicator;
	}

	public String getAtmSurchargeFreeProgramId() {
		return atmSurchargeFreeProgramId;
	}

	public void setAtmSurchargeFreeProgramId(String atmSurchargeFreeProgramId) {
		this.atmSurchargeFreeProgramId = atmSurchargeFreeProgramId;
	}

	public String getCrossBorderIndicator() {
		return crossBorderIndicator;
	}

	public void setCrossBorderIndicator(String crossBorderIndicator) {
		this.crossBorderIndicator = crossBorderIndicator;
	}

	public String getCrossBorderCurrencyIndicator() {
		return crossBorderCurrencyIndicator;
	}

	public void setCrossBorderCurrencyIndicator(String crossBorderCurrencyIndicator) {
		this.crossBorderCurrencyIndicator = crossBorderCurrencyIndicator;
	}

	public String getVisaIsaFeeIndicator() {
		return visaIsaFeeIndicator;
	}

	public void setVisaIsaFeeIndicator(String visaIsaFeeIndicator) {
		this.visaIsaFeeIndicator = visaIsaFeeIndicator;
	}

	public String getRequestAtmTransLocal() {
		return requestAtmTransLocal;
	}

	public void setRequestAtmTransLocal(String requestAtmTransLocal) {
		this.requestAtmTransLocal = requestAtmTransLocal;
	}

	public String getFillerAdj() {
		return fillerAdj;
	}

	public void setFillerAdj(String fillerAdj) {
		this.fillerAdj = fillerAdj;
	}

	public String getTraceNumberAdjustmentTrans() {
		return traceNumberAdjustmentTrans;
	}

	public void setTraceNumberAdjustmentTrans(String traceNumberAdjustmentTrans) {
		this.traceNumberAdjustmentTrans = traceNumberAdjustmentTrans;
	}

	public String getReconActivity() {
		return reconActivity;
	}

	public void setReconActivity(String reconActivity) {
		this.reconActivity = reconActivity;
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
