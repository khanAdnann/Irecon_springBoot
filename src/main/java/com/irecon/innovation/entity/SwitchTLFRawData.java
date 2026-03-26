package com.irecon.innovation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "switch_atm1_rawdata")
@Data
@NoArgsConstructor
public class SwitchTLFRawData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private Long Id;
	
	@Column(length = 70) 
	private String dateTime;
	@Column(length = 70) 
    private String recType;
	@Column(length = 70) 
    private String authPpd;
	@Column(length = 70) 
    private String termLn;
	@Column(length = 70) 
    private String termFiid;
	@Column(length = 70) 
    private String termTermId;
	@Column(length = 70) 
    private String crdLn;
	@Column(length = 70) 
    private String crdFiid;
	@Column(length = 70) 
    private String crdPan;
	@Column(length = 70) 
    private String crdMbrNum;
	@Column(length = 70) 
    private String brchId;
	@Column(length = 70) 
    private String regnId;
	@Column(length = 70) 
    private String userFld1x;
	@Column(length = 70) 
    private String typCde;
	@Column(length = 70) 
    private String typ;
	@Column(length = 70) 
    private String rteStat;
	@Column(length = 70) 
    private String originator;
	@Column(length = 70) 
    private String responder;
	@Column(length = 70) 
    private String entryTime;
	@Column(length = 70) 
    private String exitTime;
	@Column(length = 70) 
    private String reEntryTime;
	@Column(length = 70) 
    private String tranDate;
	@Column(length = 70) 
    private String tranTim;
	@Column(length = 70) 
    private String postDat;
	@Column(length = 70) 
    private String acqIchgSetlDat;
	@Column(length = 70) 
    private String issIchgSetlDat;
	@Column(length = 70) 
    private String termTyp;
	@Column(length = 70) 
    private String timOfst;
	@Column(length = 70) 
    private String acqInstIdNum;
	@Column(length = 70) 
    private String rcvInstIdNum;
	@Column(length = 70) 
    private String tranCde;
	@Column(length = 70) 
    private String fromAcct;
	@Column(length = 70) 
    private String userFld1;
	@Column(length = 70) 
    private String toAcct;
	@Column(length = 70) 
    private String multAcct;
	@Column(length = 70) 
    private String amt1;
	@Column(length = 70) 
    private String amt2;
    @Column(length = 70) 
    private String amt3;
    @Column(length = 70) 
    private String depBalCr;
    @Column(length = 70) 
    private String depTyp;
    @Column(length = 70) 
    private String respCde;
    @Column(length = 70) 
    private String termNameLoc;
    @Column(length = 70) 
    private String termOwnerName;
    @Column(length = 70) 
    private String termCity;
    @Column(length = 70) 
    private String termSt;
    @Column(length = 70) 
    private String termCntry;
    @Column(length = 70) 
    private String origOseqNum;
    @Column(length = 70) 
    private String origOtranDat;
    @Column(length = 70) 
    private String origOtranTim;
    @Column(length = 70) 
    private String origB24Post;
    @Column(length = 70) 
    private String origCrncyCde;
    @Column(length = 70) 
    private String multCrncyAuthCrncyCde;
    @Column(length = 70) 
    private String multCrncyAuthConvRate;
    @Column(length = 70) 
    private String multCrncySetlCrncyCde;
    @Column(length = 70) 
    private String multCrncySetlConvRate;
    @Column(length = 70) 
    private String multCrncyConvDatTim;
    @Column(length = 70) 
    private String rvslRsn;
    @Column(length = 70) 
    private String pinOfst;
    @Column(length = 70) 
    private String shrgGrp;
    @Column(length = 70) 
    private String destOrder;
    @Column(length = 70) 
    private String authIdResp;
    @Column(length = 70) 
    private String refrImpInd;
    @Column(length = 70) 
    private String refrAvailImp;
    @Column(length = 70) 
    private String refrLedgImp;
    @Column(length = 70) 
    private String refrHldAmtImp;
    @Column(length = 70) 
    private String refrCafRefrInd;
    @Column(length = 70) 
    private String refrUserFld3;
    @Column(length = 70) 
    private String depSetlImpFlg;
    @Column(length = 70) 
    private String adjSetlImpFlg;
    @Column(length = 70) 
    private String refrInd;
    @Column(length = 70) 
    private String userFld4;
    @Column(length = 70) 
    private String frwdInstIdNum;
    @Column(length = 70) 
    private String crdAccptIdNum;
    @Column(length = 70) 
    private String crdIssIdNum;
    @Column(length = 70) 
    private String userFld6;
    @Column(length = 70) 
    private String dcrsRemarks;
    @Column(length = 70) 
    private String fileDate;
    @Column(length = 70) 
    private String fileName;
    
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
	public String getAuthPpd() {
		return authPpd;
	}
	public void setAuthPpd(String authPpd) {
		this.authPpd = authPpd;
	}
	public String getTermLn() {
		return termLn;
	}
	public void setTermLn(String termLn) {
		this.termLn = termLn;
	}
	public String getTermFiid() {
		return termFiid;
	}
	public void setTermFiid(String termFiid) {
		this.termFiid = termFiid;
	}
	public String getTermTermId() {
		return termTermId;
	}
	public void setTermTermId(String termTermId) {
		this.termTermId = termTermId;
	}
	public String getCrdLn() {
		return crdLn;
	}
	public void setCrdLn(String crdLn) {
		this.crdLn = crdLn;
	}
	public String getCrdFiid() {
		return crdFiid;
	}
	public void setCrdFiid(String crdFiid) {
		this.crdFiid = crdFiid;
	}
	public String getCrdPan() {
		return crdPan;
	}
	public void setCrdPan(String crdPan) {
		this.crdPan = crdPan;
	}
	public String getCrdMbrNum() {
		return crdMbrNum;
	}
	public void setCrdMbrNum(String crdMbrNum) {
		this.crdMbrNum = crdMbrNum;
	}
	public String getBrchId() {
		return brchId;
	}
	public void setBrchId(String brchId) {
		this.brchId = brchId;
	}
	public String getRegnId() {
		return regnId;
	}
	public void setRegnId(String regnId) {
		this.regnId = regnId;
	}
	public String getUserFld1x() {
		return userFld1x;
	}
	public void setUserFld1x(String userFld1x) {
		this.userFld1x = userFld1x;
	}
	public String getTypCde() {
		return typCde;
	}
	public void setTypCde(String typCde) {
		this.typCde = typCde;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getRteStat() {
		return rteStat;
	}
	public void setRteStat(String rteStat) {
		this.rteStat = rteStat;
	}
	public String getOriginator() {
		return originator;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	public String getResponder() {
		return responder;
	}
	public void setResponder(String responder) {
		this.responder = responder;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public String getReEntryTime() {
		return reEntryTime;
	}
	public void setReEntryTime(String reEntryTime) {
		this.reEntryTime = reEntryTime;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getTranTim() {
		return tranTim;
	}
	public void setTranTim(String tranTim) {
		this.tranTim = tranTim;
	}
	public String getPostDat() {
		return postDat;
	}
	public void setPostDat(String postDat) {
		this.postDat = postDat;
	}
	public String getAcqIchgSetlDat() {
		return acqIchgSetlDat;
	}
	public void setAcqIchgSetlDat(String acqIchgSetlDat) {
		this.acqIchgSetlDat = acqIchgSetlDat;
	}
	public String getIssIchgSetlDat() {
		return issIchgSetlDat;
	}
	public void setIssIchgSetlDat(String issIchgSetlDat) {
		this.issIchgSetlDat = issIchgSetlDat;
	}
	public String getTermTyp() {
		return termTyp;
	}
	public void setTermTyp(String termTyp) {
		this.termTyp = termTyp;
	}
	public String getTimOfst() {
		return timOfst;
	}
	public void setTimOfst(String timOfst) {
		this.timOfst = timOfst;
	}
	public String getAcqInstIdNum() {
		return acqInstIdNum;
	}
	public void setAcqInstIdNum(String acqInstIdNum) {
		this.acqInstIdNum = acqInstIdNum;
	}
	public String getRcvInstIdNum() {
		return rcvInstIdNum;
	}
	public void setRcvInstIdNum(String rcvInstIdNum) {
		this.rcvInstIdNum = rcvInstIdNum;
	}
	public String getTranCde() {
		return tranCde;
	}
	public void setTranCde(String tranCde) {
		this.tranCde = tranCde;
	}
	public String getFromAcct() {
		return fromAcct;
	}
	public void setFromAcct(String fromAcct) {
		this.fromAcct = fromAcct;
	}
	public String getUserFld1() {
		return userFld1;
	}
	public void setUserFld1(String userFld1) {
		this.userFld1 = userFld1;
	}
	public String getToAcct() {
		return toAcct;
	}
	public void setToAcct(String toAcct) {
		this.toAcct = toAcct;
	}
	public String getMultAcct() {
		return multAcct;
	}
	public void setMultAcct(String multAcct) {
		this.multAcct = multAcct;
	}
	public String getAmt1() {
		return amt1;
	}
	public void setAmt1(String amt1) {
		this.amt1 = amt1;
	}
	public String getAmt2() {
		return amt2;
	}
	public void setAmt2(String amt2) {
		this.amt2 = amt2;
	}
	public String getAmt3() {
		return amt3;
	}
	public void setAmt3(String amt3) {
		this.amt3 = amt3;
	}
	public String getDepBalCr() {
		return depBalCr;
	}
	public void setDepBalCr(String depBalCr) {
		this.depBalCr = depBalCr;
	}
	public String getDepTyp() {
		return depTyp;
	}
	public void setDepTyp(String depTyp) {
		this.depTyp = depTyp;
	}
	public String getRespCde() {
		return respCde;
	}
	public void setRespCde(String respCde) {
		this.respCde = respCde;
	}
	public String getTermNameLoc() {
		return termNameLoc;
	}
	public void setTermNameLoc(String termNameLoc) {
		this.termNameLoc = termNameLoc;
	}
	public String getTermOwnerName() {
		return termOwnerName;
	}
	public void setTermOwnerName(String termOwnerName) {
		this.termOwnerName = termOwnerName;
	}
	public String getTermCity() {
		return termCity;
	}
	public void setTermCity(String termCity) {
		this.termCity = termCity;
	}
	public String getTermSt() {
		return termSt;
	}
	public void setTermSt(String termSt) {
		this.termSt = termSt;
	}
	public String getTermCntry() {
		return termCntry;
	}
	public void setTermCntry(String termCntry) {
		this.termCntry = termCntry;
	}
	public String getOrigOseqNum() {
		return origOseqNum;
	}
	public void setOrigOseqNum(String origOseqNum) {
		this.origOseqNum = origOseqNum;
	}
	public String getOrigOtranDat() {
		return origOtranDat;
	}
	public void setOrigOtranDat(String origOtranDat) {
		this.origOtranDat = origOtranDat;
	}
	public String getOrigOtranTim() {
		return origOtranTim;
	}
	public void setOrigOtranTim(String origOtranTim) {
		this.origOtranTim = origOtranTim;
	}
	public String getOrigB24Post() {
		return origB24Post;
	}
	public void setOrigB24Post(String origB24Post) {
		this.origB24Post = origB24Post;
	}
	public String getOrigCrncyCde() {
		return origCrncyCde;
	}
	public void setOrigCrncyCde(String origCrncyCde) {
		this.origCrncyCde = origCrncyCde;
	}
	public String getMultCrncyAuthCrncyCde() {
		return multCrncyAuthCrncyCde;
	}
	public void setMultCrncyAuthCrncyCde(String multCrncyAuthCrncyCde) {
		this.multCrncyAuthCrncyCde = multCrncyAuthCrncyCde;
	}
	public String getMultCrncyAuthConvRate() {
		return multCrncyAuthConvRate;
	}
	public void setMultCrncyAuthConvRate(String multCrncyAuthConvRate) {
		this.multCrncyAuthConvRate = multCrncyAuthConvRate;
	}
	public String getMultCrncySetlCrncyCde() {
		return multCrncySetlCrncyCde;
	}
	public void setMultCrncySetlCrncyCde(String multCrncySetlCrncyCde) {
		this.multCrncySetlCrncyCde = multCrncySetlCrncyCde;
	}
	public String getMultCrncySetlConvRate() {
		return multCrncySetlConvRate;
	}
	public void setMultCrncySetlConvRate(String multCrncySetlConvRate) {
		this.multCrncySetlConvRate = multCrncySetlConvRate;
	}
	public String getMultCrncyConvDatTim() {
		return multCrncyConvDatTim;
	}
	public void setMultCrncyConvDatTim(String multCrncyConvDatTim) {
		this.multCrncyConvDatTim = multCrncyConvDatTim;
	}
	public String getRvslRsn() {
		return rvslRsn;
	}
	public void setRvslRsn(String rvslRsn) {
		this.rvslRsn = rvslRsn;
	}
	public String getPinOfst() {
		return pinOfst;
	}
	public void setPinOfst(String pinOfst) {
		this.pinOfst = pinOfst;
	}
	public String getShrgGrp() {
		return shrgGrp;
	}
	public void setShrgGrp(String shrgGrp) {
		this.shrgGrp = shrgGrp;
	}
	public String getDestOrder() {
		return destOrder;
	}
	public void setDestOrder(String destOrder) {
		this.destOrder = destOrder;
	}
	public String getAuthIdResp() {
		return authIdResp;
	}
	public void setAuthIdResp(String authIdResp) {
		this.authIdResp = authIdResp;
	}
	public String getRefrImpInd() {
		return refrImpInd;
	}
	public void setRefrImpInd(String refrImpInd) {
		this.refrImpInd = refrImpInd;
	}
	public String getRefrAvailImp() {
		return refrAvailImp;
	}
	public void setRefrAvailImp(String refrAvailImp) {
		this.refrAvailImp = refrAvailImp;
	}
	public String getRefrLedgImp() {
		return refrLedgImp;
	}
	public void setRefrLedgImp(String refrLedgImp) {
		this.refrLedgImp = refrLedgImp;
	}
	public String getRefrHldAmtImp() {
		return refrHldAmtImp;
	}
	public void setRefrHldAmtImp(String refrHldAmtImp) {
		this.refrHldAmtImp = refrHldAmtImp;
	}
	public String getRefrCafRefrInd() {
		return refrCafRefrInd;
	}
	public void setRefrCafRefrInd(String refrCafRefrInd) {
		this.refrCafRefrInd = refrCafRefrInd;
	}
	public String getRefrUserFld3() {
		return refrUserFld3;
	}
	public void setRefrUserFld3(String refrUserFld3) {
		this.refrUserFld3 = refrUserFld3;
	}
	public String getDepSetlImpFlg() {
		return depSetlImpFlg;
	}
	public void setDepSetlImpFlg(String depSetlImpFlg) {
		this.depSetlImpFlg = depSetlImpFlg;
	}
	public String getAdjSetlImpFlg() {
		return adjSetlImpFlg;
	}
	public void setAdjSetlImpFlg(String adjSetlImpFlg) {
		this.adjSetlImpFlg = adjSetlImpFlg;
	}
	public String getRefrInd() {
		return refrInd;
	}
	public void setRefrInd(String refrInd) {
		this.refrInd = refrInd;
	}
	public String getUserFld4() {
		return userFld4;
	}
	public void setUserFld4(String userFld4) {
		this.userFld4 = userFld4;
	}
	public String getFrwdInstIdNum() {
		return frwdInstIdNum;
	}
	public void setFrwdInstIdNum(String frwdInstIdNum) {
		this.frwdInstIdNum = frwdInstIdNum;
	}
	public String getCrdAccptIdNum() {
		return crdAccptIdNum;
	}
	public void setCrdAccptIdNum(String crdAccptIdNum) {
		this.crdAccptIdNum = crdAccptIdNum;
	}
	public String getCrdIssIdNum() {
		return crdIssIdNum;
	}
	public void setCrdIssIdNum(String crdIssIdNum) {
		this.crdIssIdNum = crdIssIdNum;
	}
	public String getUserFld6() {
		return userFld6;
	}
	public void setUserFld6(String userFld6) {
		this.userFld6 = userFld6;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    
    

}
