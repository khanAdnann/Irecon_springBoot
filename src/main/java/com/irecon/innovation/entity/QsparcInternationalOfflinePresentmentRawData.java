package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "offline_qsparc_int_presentment")
public class QsparcInternationalOfflinePresentmentRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportDate;


    private String presentmentRaiseDate;


    private String presentmentSettlementDate;

    @Column(length = 100)
    private String functionCodeAndDescription;

    @Column(length = 100)
    private String primaryAccountNumber;

    private String dateLocalTransaction;

    @Column(length = 100)
    private String acquirerReferenceDataRRN;

    @Column(length = 100)
    private String rrn;

    @Column(length = 100)
    private String processingCode;

    @Column(length = 100)
    private String actionCode;

    @Column(length = 100)
    private String currencyCodeTransaction;

    @Column(length = 100)
    private String eCommerceIndicator;

    private String amountTransaction;
    private String amountAdditional;
    private String settlementAmountTransaction;
    private String settlementAmountAdditional;

    @Column(length = 100)
    private String approvalCode;

    @Column(length = 100)
    private String originatorPoint;

    @Column(length = 100)
    private String posEntryMode;

    @Column(length = 100)
    private String posConditionCode;

    @Column(length = 100)
    private String acquirerInstitutionIdCode;

    @Column(length = 100)
    private String transactionOriginatorInstIdCode;

    @Column(length = 100)
    private String acquirerNameAndCountry;

    @Column(length = 100)
    private String issuerInstitutionIdCode;

    @Column(length = 100)
    private String transDestInstIdCode;

    @Column(length = 100)
    private String issuerNameAndCountry;

    @Column(length = 100)
    private String cardType;

    @Column(length = 100)
    private String cardBrand;

    @Column(length = 100)
    private String cardAcceptorTerminalId;

    @Column(length = 100)
    private String cardAcceptorName;

    @Column(length = 100)
    private String cardAcceptorLocationAddress;

    @Column(length = 100)
    private String cardAcceptorCountryCode;

    @Column(length = 100)
    private String cardAcceptorBusinessCode;

    @Column(length = 100)
    private String cardAcceptorIdCode;

    @Column(length = 100)
    private String cardAcceptorStateName;

    @Column(length = 100)
    private String cardAcceptorCity;

    @Column(length = 100)
    private String serviceCode;

    private String aged;

    @Column(length = 100)
    private String mti;

    private String fileDate;

    @Column(length = 100, name = "FILENAME")
  
    private String filename;
    @Column(length = 100)
    private String cycle;
    

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getPresentmentRaiseDate() {
		return presentmentRaiseDate;
	}

	public void setPresentmentRaiseDate(String presentmentRaiseDate) {
		this.presentmentRaiseDate = presentmentRaiseDate;
	}

	public String getPresentmentSettlementDate() {
		return presentmentSettlementDate;
	}

	public void setPresentmentSettlementDate(String presentmentSettlementDate) {
		this.presentmentSettlementDate = presentmentSettlementDate;
	}

	public String getFunctionCodeAndDescription() {
		return functionCodeAndDescription;
	}

	public void setFunctionCodeAndDescription(String functionCodeAndDescription) {
		this.functionCodeAndDescription = functionCodeAndDescription;
	}

	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public String getDateLocalTransaction() {
		return dateLocalTransaction;
	}

	public void setDateLocalTransaction(String dateLocalTransaction) {
		this.dateLocalTransaction = dateLocalTransaction;
	}

	public String getAcquirerReferenceDataRRN() {
		return acquirerReferenceDataRRN;
	}

	public void setAcquirerReferenceDataRRN(String acquirerReferenceDataRRN) {
		this.acquirerReferenceDataRRN = acquirerReferenceDataRRN;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getCurrencyCodeTransaction() {
		return currencyCodeTransaction;
	}

	public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
		this.currencyCodeTransaction = currencyCodeTransaction;
	}

	public String geteCommerceIndicator() {
		return eCommerceIndicator;
	}

	public void seteCommerceIndicator(String eCommerceIndicator) {
		this.eCommerceIndicator = eCommerceIndicator;
	}

	public String getAmountTransaction() {
		return amountTransaction;
	}

	public void setAmountTransaction(String amountTransaction) {
		this.amountTransaction = amountTransaction;
	}

	public String getAmountAdditional() {
		return amountAdditional;
	}

	public void setAmountAdditional(String amountAdditional) {
		this.amountAdditional = amountAdditional;
	}

	public String getSettlementAmountTransaction() {
		return settlementAmountTransaction;
	}

	public void setSettlementAmountTransaction(String settlementAmountTransaction) {
		this.settlementAmountTransaction = settlementAmountTransaction;
	}

	public String getSettlementAmountAdditional() {
		return settlementAmountAdditional;
	}

	public void setSettlementAmountAdditional(String settlementAmountAdditional) {
		this.settlementAmountAdditional = settlementAmountAdditional;
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

	public String getAcquirerInstitutionIdCode() {
		return acquirerInstitutionIdCode;
	}

	public void setAcquirerInstitutionIdCode(String acquirerInstitutionIdCode) {
		this.acquirerInstitutionIdCode = acquirerInstitutionIdCode;
	}

	public String getTransactionOriginatorInstIdCode() {
		return transactionOriginatorInstIdCode;
	}

	public void setTransactionOriginatorInstIdCode(String transactionOriginatorInstIdCode) {
		this.transactionOriginatorInstIdCode = transactionOriginatorInstIdCode;
	}

	public String getAcquirerNameAndCountry() {
		return acquirerNameAndCountry;
	}

	public void setAcquirerNameAndCountry(String acquirerNameAndCountry) {
		this.acquirerNameAndCountry = acquirerNameAndCountry;
	}

	public String getIssuerInstitutionIdCode() {
		return issuerInstitutionIdCode;
	}

	public void setIssuerInstitutionIdCode(String issuerInstitutionIdCode) {
		this.issuerInstitutionIdCode = issuerInstitutionIdCode;
	}

	public String getTransDestInstIdCode() {
		return transDestInstIdCode;
	}

	public void setTransDestInstIdCode(String transDestInstIdCode) {
		this.transDestInstIdCode = transDestInstIdCode;
	}

	public String getIssuerNameAndCountry() {
		return issuerNameAndCountry;
	}

	public void setIssuerNameAndCountry(String issuerNameAndCountry) {
		this.issuerNameAndCountry = issuerNameAndCountry;
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

	public String getCardAcceptorTerminalId() {
		return cardAcceptorTerminalId;
	}

	public void setCardAcceptorTerminalId(String cardAcceptorTerminalId) {
		this.cardAcceptorTerminalId = cardAcceptorTerminalId;
	}

	public String getCardAcceptorName() {
		return cardAcceptorName;
	}

	public void setCardAcceptorName(String cardAcceptorName) {
		this.cardAcceptorName = cardAcceptorName;
	}

	public String getCardAcceptorLocationAddress() {
		return cardAcceptorLocationAddress;
	}

	public void setCardAcceptorLocationAddress(String cardAcceptorLocationAddress) {
		this.cardAcceptorLocationAddress = cardAcceptorLocationAddress;
	}

	public String getCardAcceptorCountryCode() {
		return cardAcceptorCountryCode;
	}

	public void setCardAcceptorCountryCode(String cardAcceptorCountryCode) {
		this.cardAcceptorCountryCode = cardAcceptorCountryCode;
	}

	public String getCardAcceptorBusinessCode() {
		return cardAcceptorBusinessCode;
	}

	public void setCardAcceptorBusinessCode(String cardAcceptorBusinessCode) {
		this.cardAcceptorBusinessCode = cardAcceptorBusinessCode;
	}

	public String getCardAcceptorIdCode() {
		return cardAcceptorIdCode;
	}

	public void setCardAcceptorIdCode(String cardAcceptorIdCode) {
		this.cardAcceptorIdCode = cardAcceptorIdCode;
	}

	public String getCardAcceptorStateName() {
		return cardAcceptorStateName;
	}

	public void setCardAcceptorStateName(String cardAcceptorStateName) {
		this.cardAcceptorStateName = cardAcceptorStateName;
	}

	public String getCardAcceptorCity() {
		return cardAcceptorCity;
	}

	public void setCardAcceptorCity(String cardAcceptorCity) {
		this.cardAcceptorCity = cardAcceptorCity;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}


	public String getAged() {
		return aged;
	}

	public void setAged(String aged) {
		this.aged = aged;
	}

	public String getMti() {
		return mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
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
