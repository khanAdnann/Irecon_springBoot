package com.irecon.innovation.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "nfs_rev_acq_report")
@Data
public class NfsRevAcqReportRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // assuming there's a primary key, else adjust

    @Column(length = 100)
    private String transType;
    @Column(length = 100)
    private String respCode;

    @Column(length = 100)
    private String cardno;

    @Column(length = 100)
    private String rrn;

    @Column(length = 100)
    private String stanNo;

    @Column(length = 100)
    private String acq;

    @Column(length = 100)
    private String iss;

    @Column(length = 100)
    private String trasnDate;

    @Column(length = 100)
    private String transTime;
    @Column(length = 100)
    private String atmId;
    @Column(length = 100)
    private String settleDate;

    @Column(length = 100)
    private String requestAmt;

    @Column(length = 100)
    private String receivedAmt;

    @Column(length = 100)
    private String status;

    @Column(length = 100)
    private String dcrsRemarks;

    @Column(length = 100)
    private String fileDate;  // because str_to_date(?,'%Y/%m/%d')

    @Column(length = 100)
    private String cycle;

    @Column(length = 100)
    private String merchantType;
    @Column(length = 100)
    private String fpan;

    @Column(length = 100)
    private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getStanNo() {
		return stanNo;
	}

	public void setStanNo(String stanNo) {
		this.stanNo = stanNo;
	}

	public String getAcq() {
		return acq;
	}

	public void setAcq(String acq) {
		this.acq = acq;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getTrasnDate() {
		return trasnDate;
	}

	public void setTrasnDate(String trasnDate) {
		this.trasnDate = trasnDate;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getAtmId() {
		return atmId;
	}

	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getRequestAmt() {
		return requestAmt;
	}

	public void setRequestAmt(String requestAmt) {
		this.requestAmt = requestAmt;
	}

	public String getReceivedAmt() {
		return receivedAmt;
	}

	public void setReceivedAmt(String receivedAmt) {
		this.receivedAmt = receivedAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDcrsRemarks() {
		return dcrsRemarks;
	}

	public void setDcrsRemarks(String dcrsRemarks) {
		this.dcrsRemarks = dcrsRemarks;
	}

	
	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getFpan() {
		return fpan;
	}

	public void setFpan(String fpan) {
		this.fpan = fpan;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFilename(String fileName) {
		this.fileName = fileName;
	}
    
}
