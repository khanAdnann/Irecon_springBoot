package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "rupay_int_presentment")
public class RupayInternationalPresentmentRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unqId;


    @Column(name = "Report_Date")
    private String reportDate;

    @Column(name = "Presentment_Raise_Date")
    private String presentmentRaiseDate;

    @Column(name = "Presentment_Settlement_Date")
    private String presentmentSettlementDate;

    @Column(name = "Function_Code_and_Description")
    private String functionCodeAndDescription;

    @Column(name = "PAN")
    private String pan;

    @Column(name = "Local_Transaction")
    private String localTransaction;

    @Column(name = "RRN")
    private String rrn;

    @Column(name = "Processing_Code")
    private String processingCode;

    @Column(name = "Currency_Code")
    private String currencyCode;

    @Column(name = "E_Commerce_Indicator")
    private String eCommerceIndicator;

    @Column(name = "Amount_Transaction")
    private String amountTransaction;

    @Column(name = "Amount_Additional")
    private String amountAdditional;

    @Column(name = "Settlement_Amount_Transaction")
    private String settlementAmountTransaction;

    @Column(name = "Settlement_Amount_Additional")
    private String settlementAmountAdditional;

    @Column(name = "Approval_Code")
    private String approvalCode;

    @Column(name = "Originator_Point")
    private String originatorPoint;

    @Column(name = "POS_Entry_Mode")
    private String posEntryMode;

    @Column(name = "POS_Condition_Code")
    private String posConditionCode;

    @Column(name = "Acquirer_Institution_ID_code")
    private String acquirerInstitutionIdCode;

    @Column(name = "Transaction_Originator_Institution_ID_code")
    private String transactionOriginatorInstitutionIdCode;

    @Column(name = "Acquirer_Name_and_Country")
    private String acquirerNameAndCountry;

    @Column(name = "Issuer_Institution_ID_code")
    private String issuerInstitutionIdCode;

    @Column(name = "Transaction_Destination_Institution_ID_code")
    private String transactionDestinationInstitutionIdCode;

    @Column(name = "Issuer_Name_and_Country")
    private String issuerNameAndCountry;

    @Column(name = "Card_Type")
    private String cardType;

    @Column(name = "Card_Brand")
    private String cardBrand;

    @Column(name = "Card_Acceptor_Terminal_ID")
    private String cardAcceptorTerminalId;

    @Column(name = "Card_Acceptor_Name")
    private String cardAcceptorName;

    @Column(name = "Card_Acceptor_Location")
    private String cardAcceptorLocation;

    @Column(name = "Card_Acceptor_Country_Code")
    private String cardAcceptorCountryCode;

    @Column(name = "Card_Acceptor_Business_Code")
    private String cardAcceptorBusinessCode;

    @Column(name = "Card_Acceptor_ID_Code")
    private String cardAcceptorIdCode;

    @Column(name = "Card_Acceptor_State_Name")
    private String cardAcceptorStateName;

    @Column(name = "Card_Acceptor_City")
    private String cardAcceptorCity;

    @Column(name = "Days_Aged")
    private String daysAged;

    @Column(name = "MTI")
    private String mti;

    @Column(name = "FileDate")
    private String fileDate;

    @Column(name = "cycle")
    private String cycle;

    @Column(name = "FILENAME")
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getLocalTransaction() {
		return localTransaction;
	}

	public void setLocalTransaction(String localTransaction) {
		this.localTransaction = localTransaction;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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

	public String getTransactionOriginatorInstitutionIdCode() {
		return transactionOriginatorInstitutionIdCode;
	}

	public void setTransactionOriginatorInstitutionIdCode(String transactionOriginatorInstitutionIdCode) {
		this.transactionOriginatorInstitutionIdCode = transactionOriginatorInstitutionIdCode;
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

	public String getTransactionDestinationInstitutionIdCode() {
		return transactionDestinationInstitutionIdCode;
	}

	public void setTransactionDestinationInstitutionIdCode(String transactionDestinationInstitutionIdCode) {
		this.transactionDestinationInstitutionIdCode = transactionDestinationInstitutionIdCode;
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

	public String getCardAcceptorLocation() {
		return cardAcceptorLocation;
	}

	public void setCardAcceptorLocation(String cardAcceptorLocation) {
		this.cardAcceptorLocation = cardAcceptorLocation;
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

	public String getDaysAged() {
		return daysAged;
	}

	public void setDaysAged(String daysAged) {
		this.daysAged = daysAged;
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
