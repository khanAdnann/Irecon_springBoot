package com.irecon.innovation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "switch_pos1_rawdata")
@Data
public class SwitchPTLFRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 90)
    private String dateTime;
    @Column(length = 90)
    private String recTyp;
    @Column(length = 90)
    private String crdLn;
    @Column(length = 90)
    private String crdFiid;
    @Column(length = 90)
    private String cradNum;
    @Column(length = 90)
    private String crdMbrNum;
    @Column(length = 90)
    private String retlKeyIn;
    @Column(length = 90)
    private String rdfKey;
    @Column(length = 90)
    private String rdfKeyGrp;
    @Column(length = 90)
    private String rdfKeyRegn;
    @Column(length = 90)
    private String rdfKeyId;
    @Column(length = 90)
    private String termId;
    @Column(length = 90)
    private String shiftNum;
    @Column(length = 90)
    private String batchNum;
    @Column(length = 90)
    private String termIn;
    @Column(length = 90)
    private String termFiid;
    @Column(length = 90)
    private String termId2;
    @Column(length = 90)
    private String termTime;
    @Column(length = 90)
    private String termId3;
    @Column(length = 90)
    private String tkeyRkeyRecFrmt;
    @Column(length = 90)
    private String tkeyRkeyRetailerId;
    @Column(length = 90)
    private String tkeyRkeyClerkId;
    @Column(length = 90)
    private String dataFlag;
    @Column(length = 90)
    private String type;
    @Column(length = 90)
    private String rteStat;
    @Column(length = 90)
    private String originator;
    @Column(length = 90)
    private String responder;
    @Column(length = 90)
    private String issCde;
    @Column(length = 90)
    private String entryTime;
    @Column(length = 90)
    private String exitTime;
    @Column(length = 90)
    private String reEntryTime;
    @Column(length = 90)
    private String tranDate;
    @Column(length = 90)
    private String tranTim;
    @Column(length = 90)
    private String postDat;
    @Column(length = 90)
    private String acqIchgSetlDat;
    @Column(length = 90)
    private String issIchgSetlDat;
    @Column(length = 90)
    private String seqNum;
    @Column(length = 90)
    private String termNameLoc;
    @Column(length = 90)
    private String termOwnerName;
    @Column(length = 90)
    private String termCity;
    @Column(length = 90)
    private String termSt;
    @Column(length = 90)
    private String termCntryCde;
    @Column(length = 90)
    private String brchId;
    @Column(length = 90)
    private String userFid;
    @Column(length = 90)
    private String termTimOfst;
    @Column(length = 90)
    private String acqInstIdNum;
    @Column(length = 90)
    private String rcvInstIdNum;
    @Column(length = 90)
    private String termType;
    @Column(length = 90)
    private String clerkId;
    @Column(length = 90)
    private String ctrAuth;
    @Column(length = 90)
    private String ctrAuthGrp;
    @Column(length = 90)
    private String ctrAuthUserId;
    @Column(length = 90)
    private String retlSicCde;
    @Column(length = 90)
    private String orig;
    @Column(length = 90)
    private String dest;
    @Column(length = 90)
    private String tranCde;
    @Column(length = 90)
    private String crdType;
    @Column(length = 90)
    private String acct;
    @Column(length = 90)
    private String respCde;
    @Column(length = 90)
    private String amount1;
    @Column(length = 90)
    private String amount2;
    @Column(length = 90)
    private String expiryDate;
    @Column(length = 90)
    private String track2;
    @Column(length = 90)
    private String pinOfst;
    @Column(length = 90)
    private String preAuthSeqNum;
    @Column(length = 90)
    private String invoiceNum;
    @Column(length = 90)
    private String origInvoiceNum;
    @Column(length = 90)
    private String authorizer;
    @Column(length = 90)
    private String authInd;
    @Column(length = 90)
    private String shiftNum2;
    @Column(length = 90)
    private String batchSeqNum;
    @Column(length = 90)
    private String apprvCode;
    @Column(length = 90)
    private String apprvCodeLength;
    @Column(length = 90)
    private String ichgResp;
    @Column(length = 90)
    private String pseudoTermId;
    @Column(length = 90)
    private String rfrlPhone;
    @Column(length = 90)
    private String dummy1;
    @Column(length = 90)
    private String dftCaptureFlag;
    @Column(length = 90)
    private String seltFlag;
    @Column(length = 90)
    private String rvrlCode;
    @Column(length = 90)
    private String reaForChrgbck;
    @Column(length = 90)
    private String numOfChrgbck;
    @Column(length = 90)
    private String ptSrvCondCode;
    @Column(length = 90)
    private String ptSrvEntryMode;
    @Column(length = 90)
    private String authInd2;
    @Column(length = 90)
    private String origCrncyCode;
    @Column(length = 90)
    private String multiCrnyAuthCrncyCode;
    @Column(length = 90)
    private String multyCrncyAuthConvRate;
    @Column(length = 90)
    private String multiCrncySetlCrncyCode;
    @Column(length = 90)
    private String multiCrncySetlConvRate;
    @Column(length = 90)
    private String multiCrncyConvDatTime;
    @Column(length = 90)
    private String refrImpInd;
    @Column(length = 90)
    private String refrAvailCr;
    @Column(length = 90)
    private String refrCrLmt;
    @Column(length = 90)
    private String refrCrBal;
    @Column(length = 90)
    private String refrTtl;
    @Column(length = 90)
    private String refrCur;
    @Column(length = 90)
    private String adjSetlImpactFlag;
    @Column(length = 90)
    private String refrInd;
    @Column(length = 90)
    private String frwdInstIdNum;
    @Column(length = 90)
    private String crdAccptIdNum;
    @Column(length = 90)
    private String crdIssIdNum;
    @Column(length = 90)
    private String origMsgType;
    @Column(length = 90)
    private String origTranTim;
    @Column(length = 90)
    private String origTranDate;
    @Column(length = 90)
    private String origSeqNum;
    @Column(length = 90)
    private String origB24PostDate;
    @Column(length = 90)
    private String excpRsnCode;
    @Column(length = 90)
    private String ovrrdeFlag;
    @Column(length = 90)
    private String addr;
    @Column(length = 90)
    private String zip;
    @Column(length = 90)
    private String addrVerfyStat;
    @Column(length = 90)
    private String pinInd;
    @Column(length = 90)
    private String pinTries;
    @Column(length = 90)
    private String preAuthTs;
    @Column(length = 90)
    private String preAuthHldsStat;
    @Column(length = 90)
    private String userFid2;
    @Column(length = 90)
    private String userDataDLen;
    @Column(length = 90)
    private String userDataDInfo;
    @Column(length = 90)
    private String dcrsRemarks;
    @Column(length = 90)
    private String fileDate;

    @Column(length = 90)
    private String filename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getRecTyp() {
		return recTyp;
	}

	public void setRecTyp(String recTyp) {
		this.recTyp = recTyp;
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

	public String getCradNum() {
		return cradNum;
	}

	public void setCradNum(String cradNum) {
		this.cradNum = cradNum;
	}

	public String getCrdMbrNum() {
		return crdMbrNum;
	}

	public void setCrdMbrNum(String crdMbrNum) {
		this.crdMbrNum = crdMbrNum;
	}

	public String getRetlKeyIn() {
		return retlKeyIn;
	}

	public void setRetlKeyIn(String retlKeyIn) {
		this.retlKeyIn = retlKeyIn;
	}

	public String getRdfKey() {
		return rdfKey;
	}

	public void setRdfKey(String rdfKey) {
		this.rdfKey = rdfKey;
	}

	public String getRdfKeyGrp() {
		return rdfKeyGrp;
	}

	public void setRdfKeyGrp(String rdfKeyGrp) {
		this.rdfKeyGrp = rdfKeyGrp;
	}

	public String getRdfKeyRegn() {
		return rdfKeyRegn;
	}

	public void setRdfKeyRegn(String rdfKeyRegn) {
		this.rdfKeyRegn = rdfKeyRegn;
	}

	public String getRdfKeyId() {
		return rdfKeyId;
	}

	public void setRdfKeyId(String rdfKeyId) {
		this.rdfKeyId = rdfKeyId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getShiftNum() {
		return shiftNum;
	}

	public void setShiftNum(String shiftNum) {
		this.shiftNum = shiftNum;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getTermIn() {
		return termIn;
	}

	public void setTermIn(String termIn) {
		this.termIn = termIn;
	}

	public String getTermFiid() {
		return termFiid;
	}

	public void setTermFiid(String termFiid) {
		this.termFiid = termFiid;
	}

	public String getTermId2() {
		return termId2;
	}

	public void setTermId2(String termId2) {
		this.termId2 = termId2;
	}

	public String getTermTime() {
		return termTime;
	}

	public void setTermTime(String termTime) {
		this.termTime = termTime;
	}

	public String getTermId3() {
		return termId3;
	}

	public void setTermId3(String termId3) {
		this.termId3 = termId3;
	}

	public String getTkeyRkeyRecFrmt() {
		return tkeyRkeyRecFrmt;
	}

	public void setTkeyRkeyRecFrmt(String tkeyRkeyRecFrmt) {
		this.tkeyRkeyRecFrmt = tkeyRkeyRecFrmt;
	}

	public String getTkeyRkeyRetailerId() {
		return tkeyRkeyRetailerId;
	}

	public void setTkeyRkeyRetailerId(String tkeyRkeyRetailerId) {
		this.tkeyRkeyRetailerId = tkeyRkeyRetailerId;
	}

	public String getTkeyRkeyClerkId() {
		return tkeyRkeyClerkId;
	}

	public void setTkeyRkeyClerkId(String tkeyRkeyClerkId) {
		this.tkeyRkeyClerkId = tkeyRkeyClerkId;
	}

	public String getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getIssCde() {
		return issCde;
	}

	public void setIssCde(String issCde) {
		this.issCde = issCde;
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

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
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

	public String getTermCntryCde() {
		return termCntryCde;
	}

	public void setTermCntryCde(String termCntryCde) {
		this.termCntryCde = termCntryCde;
	}

	public String getBrchId() {
		return brchId;
	}

	public void setBrchId(String brchId) {
		this.brchId = brchId;
	}

	public String getUserFid() {
		return userFid;
	}

	public void setUserFid(String userFid) {
		this.userFid = userFid;
	}

	public String getTermTimOfst() {
		return termTimOfst;
	}

	public void setTermTimOfst(String termTimOfst) {
		this.termTimOfst = termTimOfst;
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

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getClerkId() {
		return clerkId;
	}

	public void setClerkId(String clerkId) {
		this.clerkId = clerkId;
	}

	public String getCtrAuth() {
		return ctrAuth;
	}

	public void setCtrAuth(String ctrAuth) {
		this.ctrAuth = ctrAuth;
	}

	public String getCtrAuthGrp() {
		return ctrAuthGrp;
	}

	public void setCtrAuthGrp(String ctrAuthGrp) {
		this.ctrAuthGrp = ctrAuthGrp;
	}

	public String getCtrAuthUserId() {
		return ctrAuthUserId;
	}

	public void setCtrAuthUserId(String ctrAuthUserId) {
		this.ctrAuthUserId = ctrAuthUserId;
	}

	public String getRetlSicCde() {
		return retlSicCde;
	}

	public void setRetlSicCde(String retlSicCde) {
		this.retlSicCde = retlSicCde;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getTranCde() {
		return tranCde;
	}

	public void setTranCde(String tranCde) {
		this.tranCde = tranCde;
	}

	public String getCrdType() {
		return crdType;
	}

	public void setCrdType(String crdType) {
		this.crdType = crdType;
	}

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public String getRespCde() {
		return respCde;
	}

	public void setRespCde(String respCde) {
		this.respCde = respCde;
	}

	public String getAmount1() {
		return amount1;
	}

	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}

	public String getAmount2() {
		return amount2;
	}

	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getPinOfst() {
		return pinOfst;
	}

	public void setPinOfst(String pinOfst) {
		this.pinOfst = pinOfst;
	}

	public String getPreAuthSeqNum() {
		return preAuthSeqNum;
	}

	public void setPreAuthSeqNum(String preAuthSeqNum) {
		this.preAuthSeqNum = preAuthSeqNum;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getOrigInvoiceNum() {
		return origInvoiceNum;
	}

	public void setOrigInvoiceNum(String origInvoiceNum) {
		this.origInvoiceNum = origInvoiceNum;
	}

	public String getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}

	public String getAuthInd() {
		return authInd;
	}

	public void setAuthInd(String authInd) {
		this.authInd = authInd;
	}

	public String getShiftNum2() {
		return shiftNum2;
	}

	public void setShiftNum2(String shiftNum2) {
		this.shiftNum2 = shiftNum2;
	}

	public String getBatchSeqNum() {
		return batchSeqNum;
	}

	public void setBatchSeqNum(String batchSeqNum) {
		this.batchSeqNum = batchSeqNum;
	}

	public String getApprvCode() {
		return apprvCode;
	}

	public void setApprvCode(String apprvCode) {
		this.apprvCode = apprvCode;
	}

	public String getApprvCodeLength() {
		return apprvCodeLength;
	}

	public void setApprvCodeLength(String apprvCodeLength) {
		this.apprvCodeLength = apprvCodeLength;
	}

	public String getIchgResp() {
		return ichgResp;
	}

	public void setIchgResp(String ichgResp) {
		this.ichgResp = ichgResp;
	}

	public String getPseudoTermId() {
		return pseudoTermId;
	}

	public void setPseudoTermId(String pseudoTermId) {
		this.pseudoTermId = pseudoTermId;
	}

	public String getRfrlPhone() {
		return rfrlPhone;
	}

	public void setRfrlPhone(String rfrlPhone) {
		this.rfrlPhone = rfrlPhone;
	}

	public String getDummy1() {
		return dummy1;
	}

	public void setDummy1(String dummy1) {
		this.dummy1 = dummy1;
	}

	public String getDftCaptureFlag() {
		return dftCaptureFlag;
	}

	public void setDftCaptureFlag(String dftCaptureFlag) {
		this.dftCaptureFlag = dftCaptureFlag;
	}

	public String getSeltFlag() {
		return seltFlag;
	}

	public void setSeltFlag(String seltFlag) {
		this.seltFlag = seltFlag;
	}

	public String getRvrlCode() {
		return rvrlCode;
	}

	public void setRvrlCode(String rvrlCode) {
		this.rvrlCode = rvrlCode;
	}

	public String getReaForChrgbck() {
		return reaForChrgbck;
	}

	public void setReaForChrgbck(String reaForChrgbck) {
		this.reaForChrgbck = reaForChrgbck;
	}

	public String getNumOfChrgbck() {
		return numOfChrgbck;
	}

	public void setNumOfChrgbck(String numOfChrgbck) {
		this.numOfChrgbck = numOfChrgbck;
	}

	public String getPtSrvCondCode() {
		return ptSrvCondCode;
	}

	public void setPtSrvCondCode(String ptSrvCondCode) {
		this.ptSrvCondCode = ptSrvCondCode;
	}

	public String getPtSrvEntryMode() {
		return ptSrvEntryMode;
	}

	public void setPtSrvEntryMode(String ptSrvEntryMode) {
		this.ptSrvEntryMode = ptSrvEntryMode;
	}

	public String getAuthInd2() {
		return authInd2;
	}

	public void setAuthInd2(String authInd2) {
		this.authInd2 = authInd2;
	}

	public String getOrigCrncyCode() {
		return origCrncyCode;
	}

	public void setOrigCrncyCode(String origCrncyCode) {
		this.origCrncyCode = origCrncyCode;
	}

	public String getMultiCrnyAuthCrncyCode() {
		return multiCrnyAuthCrncyCode;
	}

	public void setMultiCrnyAuthCrncyCode(String multiCrnyAuthCrncyCode) {
		this.multiCrnyAuthCrncyCode = multiCrnyAuthCrncyCode;
	}

	public String getMultyCrncyAuthConvRate() {
		return multyCrncyAuthConvRate;
	}

	public void setMultyCrncyAuthConvRate(String multyCrncyAuthConvRate) {
		this.multyCrncyAuthConvRate = multyCrncyAuthConvRate;
	}

	public String getMultiCrncySetlCrncyCode() {
		return multiCrncySetlCrncyCode;
	}

	public void setMultiCrncySetlCrncyCode(String multiCrncySetlCrncyCode) {
		this.multiCrncySetlCrncyCode = multiCrncySetlCrncyCode;
	}

	public String getMultiCrncySetlConvRate() {
		return multiCrncySetlConvRate;
	}

	public void setMultiCrncySetlConvRate(String multiCrncySetlConvRate) {
		this.multiCrncySetlConvRate = multiCrncySetlConvRate;
	}

	public String getMultiCrncyConvDatTime() {
		return multiCrncyConvDatTime;
	}

	public void setMultiCrncyConvDatTime(String multiCrncyConvDatTime) {
		this.multiCrncyConvDatTime = multiCrncyConvDatTime;
	}

	public String getRefrImpInd() {
		return refrImpInd;
	}

	public void setRefrImpInd(String refrImpInd) {
		this.refrImpInd = refrImpInd;
	}

	public String getRefrAvailCr() {
		return refrAvailCr;
	}

	public void setRefrAvailCr(String refrAvailCr) {
		this.refrAvailCr = refrAvailCr;
	}

	public String getRefrCrLmt() {
		return refrCrLmt;
	}

	public void setRefrCrLmt(String refrCrLmt) {
		this.refrCrLmt = refrCrLmt;
	}

	public String getRefrCrBal() {
		return refrCrBal;
	}

	public void setRefrCrBal(String refrCrBal) {
		this.refrCrBal = refrCrBal;
	}

	public String getRefrTtl() {
		return refrTtl;
	}

	public void setRefrTtl(String refrTtl) {
		this.refrTtl = refrTtl;
	}

	public String getRefrCur() {
		return refrCur;
	}

	public void setRefrCur(String refrCur) {
		this.refrCur = refrCur;
	}

	public String getAdjSetlImpactFlag() {
		return adjSetlImpactFlag;
	}

	public void setAdjSetlImpactFlag(String adjSetlImpactFlag) {
		this.adjSetlImpactFlag = adjSetlImpactFlag;
	}

	public String getRefrInd() {
		return refrInd;
	}

	public void setRefrInd(String refrInd) {
		this.refrInd = refrInd;
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

	public String getOrigMsgType() {
		return origMsgType;
	}

	public void setOrigMsgType(String origMsgType) {
		this.origMsgType = origMsgType;
	}

	public String getOrigTranTim() {
		return origTranTim;
	}

	public void setOrigTranTim(String origTranTim) {
		this.origTranTim = origTranTim;
	}

	public String getOrigTranDate() {
		return origTranDate;
	}

	public void setOrigTranDate(String origTranDate) {
		this.origTranDate = origTranDate;
	}

	public String getOrigSeqNum() {
		return origSeqNum;
	}

	public void setOrigSeqNum(String origSeqNum) {
		this.origSeqNum = origSeqNum;
	}

	public String getOrigB24PostDate() {
		return origB24PostDate;
	}

	public void setOrigB24PostDate(String origB24PostDate) {
		this.origB24PostDate = origB24PostDate;
	}

	public String getExcpRsnCode() {
		return excpRsnCode;
	}

	public void setExcpRsnCode(String excpRsnCode) {
		this.excpRsnCode = excpRsnCode;
	}

	public String getOvrrdeFlag() {
		return ovrrdeFlag;
	}

	public void setOvrrdeFlag(String ovrrdeFlag) {
		this.ovrrdeFlag = ovrrdeFlag;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddrVerfyStat() {
		return addrVerfyStat;
	}

	public void setAddrVerfyStat(String addrVerfyStat) {
		this.addrVerfyStat = addrVerfyStat;
	}

	public String getPinInd() {
		return pinInd;
	}

	public void setPinInd(String pinInd) {
		this.pinInd = pinInd;
	}

	public String getPinTries() {
		return pinTries;
	}

	public void setPinTries(String pinTries) {
		this.pinTries = pinTries;
	}

	public String getPreAuthTs() {
		return preAuthTs;
	}

	public void setPreAuthTs(String preAuthTs) {
		this.preAuthTs = preAuthTs;
	}

	public String getPreAuthHldsStat() {
		return preAuthHldsStat;
	}

	public void setPreAuthHldsStat(String preAuthHldsStat) {
		this.preAuthHldsStat = preAuthHldsStat;
	}

	public String getUserFid2() {
		return userFid2;
	}

	public void setUserFid2(String userFid2) {
		this.userFid2 = userFid2;
	}

	public String getUserDataDLen() {
		return userDataDLen;
	}

	public void setUserDataDLen(String userDataDLen) {
		this.userDataDLen = userDataDLen;
	}

	public String getUserDataDInfo() {
		return userDataDInfo;
	}

	public void setUserDataDInfo(String userDataDInfo) {
		this.userDataDInfo = userDataDInfo;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
    
}
