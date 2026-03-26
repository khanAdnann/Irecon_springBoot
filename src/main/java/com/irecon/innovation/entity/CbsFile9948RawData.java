package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cbs_nfs_iss_rawdata_demo")
@Data
public class CbsFile9948RawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tran_date", length = 100)
    private String tranDate;

    @Column(length = 100)
    private String narration;

    @Column(name = "dr_amount", length = 100)
    private String drAmount;

    @Column(name = "cr_amount", length = 100)
    private String crAmount;

    @Column(name = "GL_BALANCE", length = 100)
    private String glBalance;

    @Column(name = "tran_date2", length = 100)
    private String tranDate2;

    @Column(length = 100)
    private String dummy;

    @Column(name = "sys_man", length = 100)
    private String sysMan;

    @Column(name = "tran_id", length = 100)
    private String tranId;

    @Column(name = "acc_number", length = 100)
    private String accNumber;

    @Column(name = "narration2", length = 100)
    private String narration2;

    @Column(length = 100)
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

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getDrAmount() {
		return drAmount;
	}

	public void setDrAmount(String drAmount) {
		this.drAmount = drAmount;
	}

	public String getCrAmount() {
		return crAmount;
	}

	public void setCrAmount(String crAmount) {
		this.crAmount = crAmount;
	}

	public String getGlBalance() {
		return glBalance;
	}

	public void setGlBalance(String glBalance) {
		this.glBalance = glBalance;
	}

	public String getTranDate2() {
		return tranDate2;
	}

	public void setTranDate2(String tranDate2) {
		this.tranDate2 = tranDate2;
	}

	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public String getSysMan() {
		return sysMan;
	}

	public void setSysMan(String sysMan) {
		this.sysMan = sysMan;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getNarration2() {
		return narration2;
	}

	public void setNarration2(String narration2) {
		this.narration2 = narration2;
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
