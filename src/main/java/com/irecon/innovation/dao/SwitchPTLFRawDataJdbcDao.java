package com.irecon.innovation.dao;

import com.irecon.innovation.entity.SwitchPTLFRawData;
import com.irecon.innovation.entity.SwitchTLFRawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class SwitchPTLFRawDataJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void batchInsert(List<SwitchPTLFRawData> dataList) {
    	
    	
    	final String INSERT_SQL = "  INSERT INTO  switch_pos_rawdata (DATE_TIME,REC_TYP,CRD_LN,CRD_FIID,CRAD_NUM,CRD_MBR_NUM,RETL_KEY_IN,RDF_KEY,RDF_KEY_GRP,RDF_KEY_REGN,RDF_KEY_ID,TERM_ID,SHIFT_NUM,BATCH_NUM,TERM_IN,TERM_FIID,TERM_ID2,TERM_TIME,TERM_ID3,TKEY_RKEY_REC_FRMT,TKEY_RKEY_RETAILER_ID,TKEY_RKEY_CLERK_ID,DATA_FLAG,TYPE,RTE_STAT,ORIGINATOR,RESPONDER,ISS_CDE,ENTRY_TIME,EXIT_TIME,RE_ENTRY_TIME,TRAN_DATE,TRAN_TIM,POST_DAT,ACQ_ICHG_SETL_DAT,ISS_ICHG_SETL_DAT,SEQ_NUM,TERM_NAME_LOC,TERM_OWNER_NAME,TERM_CITY,TERM_ST,TERM_CNTRY_CDE,BRCH_ID,USER_FID,TERM_TIM_OFST,ACQ_INST_ID_NUM,RCV_INST_ID_NUM,TERM_TYPE,CLERK_ID,CTR_AUTH,CTR_AUTH_GRP,CTR_AUTH_USER_ID,RETL_SIC_CDE,ORIG,DEST,TRAN_CDE,CRD_TYPE,ACCT,RESP_CDE,AMOUNT_1,AMOUNT_2,EXPIRY_DATE,TRACK2,PIN_OFST,PRE_AUTH_SEQ_NUM,INVOICE_NUM,ORIG_INVOICE_NUM,AUTHORIZER,AUTH_IND,SHIFT_NUM_2,BATCH_SEQ_NUM,APPRV_CODE,APPRV_CODE_LENGTH,ICHG_RESP,PSEUDO_TERM_ID,RFRL_PHONE,DUMMY_1,DFT_CAPTURE_FLAG,SELT_FLAG,RVRL_CODE,REA_FOR_CHRGBCK,NUM_OF_CHRGBCK,PT_SRV_COND_CODE,PT_SRV_ENTRY_MODE,AUTH_IND2,ORIG_CRNCY_CODE,MULTI_CRNY_AUTH_CRNCY_CODE,MULTY_CRNCY_AUTH_CONV_RATE,MULTI_CRNCY_SETL_CRNCY_CODE,MULTI_CRNCY_SETL_CONV_RATE,MULTI_CRNCY_CONV_DAT_TIME,REFR_IMP_IND,REFR_AVAIL_CR,REFR_CR_LMT,REFR_CR_BAL,REFR_TTL,REFR_CUR,ADJ_SETL_IMPACT_FLAG,REFR_IND,FRWD_INST_ID_NUM,CRD_ACCPT_ID_NUM,CRD_ISS_ID_NUM,ORIG_MSG_TYPE,ORIG_TRAN_TIM,ORIG_TRAN_DATE,ORIG_SEQ_NUM,ORIG_B24_POST_DATE,EXCP_RSN_CODE,OVRRDE_FLAG, ADDR,ZIP,ADDR_VERFY_STAT,PIN_IND,PIN_TRIES,PRE_AUTH_TS,PRE_AUTH_HLDS_STAT,USER_FID2,USER_DATA_D_LEN,USER_DATA_D_INFO,DCRS_REMARKS,FILEDATE,FILENAME) VALUES ("
    			+ String.join(", ", Collections.nCopies(122, "?")) + ")";

        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
            	SwitchPTLFRawData d = dataList.get(i);
                ps.setString(1, d.getDateTime());
                ps.setString(2, d.getRecTyp());
                ps.setString(3, d.getCrdLn());
                ps.setString(4, d.getCrdFiid());
                ps.setString(5, d.getCradNum());
                ps.setString(6, d.getCrdMbrNum());
                ps.setString(7, d.getRetlKeyIn());
                ps.setString(8, d.getRdfKey());
                ps.setString(9, d.getRdfKeyGrp());
                ps.setString(10, d.getRdfKeyRegn());
                ps.setString(11, d.getRdfKeyId());
                ps.setString(12, d.getTermId());
                ps.setString(13, d.getShiftNum());
                ps.setString(14, d.getBatchNum());
                ps.setString(15, d.getTermIn());
                ps.setString(16, d.getTermFiid());
                ps.setString(17, d.getTermId2());
                ps.setString(18, d.getTermTime());
                ps.setString(19, d.getTermId3());
                ps.setString(20, d.getTkeyRkeyRecFrmt());
                ps.setString(21, d.getTkeyRkeyRetailerId());
                ps.setString(22, d.getTkeyRkeyClerkId());
                ps.setString(23, d.getDataFlag());
                ps.setString(24, d.getType());
                ps.setString(25, d.getRteStat());
                ps.setString(26, d.getOriginator());
                ps.setString(27, d.getResponder());
                ps.setString(28, d.getIssCde());
                ps.setString(29, d.getEntryTime());
                ps.setString(30, d.getExitTime());
                ps.setString(31, d.getReEntryTime());
                ps.setString(32, d.getTranDate());
                ps.setString(33, d.getTranTim());
                ps.setString(34, d.getPostDat());
                ps.setString(35, d.getAcqIchgSetlDat());
                ps.setString(36, d.getIssIchgSetlDat());
                ps.setString(37, d.getSeqNum());
                ps.setString(38, d.getTermNameLoc());
                ps.setString(39, d.getTermOwnerName());
                ps.setString(40, d.getTermCity());
                ps.setString(41, d.getTermSt());
                ps.setString(42, d.getTermCntryCde());
                ps.setString(43, d.getBrchId());
                ps.setString(44, d.getUserFid());
                ps.setString(45, d.getTermTimOfst());
                ps.setString(46, d.getAcqInstIdNum());
                ps.setString(47, d.getRcvInstIdNum());
                ps.setString(48, d.getTermType());
                ps.setString(49, d.getClerkId());
                ps.setString(50, d.getCtrAuth());
                ps.setString(51, d.getCtrAuthGrp());
                ps.setString(52, d.getCtrAuthUserId());
                ps.setString(53, d.getRetlSicCde());
                ps.setString(54, d.getOrig());
                ps.setString(55, d.getDest());
                ps.setString(56, d.getTranCde());
                ps.setString(57, d.getCrdType());
                ps.setString(58, d.getAcct());
                ps.setString(59, d.getRespCde());
                ps.setString(60, d.getAmount1());
                ps.setString(61, d.getAmount2());
                ps.setString(62, d.getExpiryDate());
                ps.setString(63, d.getTrack2());
                ps.setString(64, d.getPinOfst());
                ps.setString(65, d.getPreAuthSeqNum());
                ps.setString(66, d.getInvoiceNum());
                ps.setString(67, d.getOrigInvoiceNum());
                ps.setString(68, d.getAuthorizer());
                ps.setString(69, d.getAuthInd());
                ps.setString(70, d.getShiftNum2());
                ps.setString(71, d.getBatchSeqNum());
                ps.setString(72, d.getApprvCode());
                ps.setString(73, d.getApprvCodeLength());
                ps.setString(74, d.getIchgResp());
                ps.setString(75, d.getPseudoTermId());
                ps.setString(76, d.getRfrlPhone());
                ps.setString(77, d.getDummy1());
                ps.setString(78, d.getDftCaptureFlag());
                ps.setString(79, d.getSeltFlag());
                ps.setString(80, d.getRvrlCode());
                ps.setString(81, d.getReaForChrgbck());
                ps.setString(82, d.getNumOfChrgbck());
                ps.setString(83, d.getPtSrvCondCode());
                ps.setString(84, d.getPtSrvEntryMode());
                ps.setString(85, d.getAuthInd2());
                ps.setString(86, d.getOrigCrncyCode());
                ps.setString(87, d.getMultiCrnyAuthCrncyCode());
                ps.setString(88, d.getMultyCrncyAuthConvRate());
                ps.setString(89, d.getMultiCrncySetlCrncyCode());
                ps.setString(90, d.getMultiCrncySetlConvRate());
                ps.setString(91, d.getMultiCrncyConvDatTime());
                ps.setString(92, d.getRefrImpInd());
                ps.setString(93, d.getRefrAvailCr());
                ps.setString(94, d.getRefrCrLmt());
                ps.setString(95, d.getRefrCrBal());
                ps.setString(96, d.getRefrTtl());
                ps.setString(97, d.getRefrCur());
                ps.setString(98, d.getAdjSetlImpactFlag());
                ps.setString(99, d.getRefrInd());
                ps.setString(100, d.getFrwdInstIdNum());
                ps.setString(101, d.getCrdAccptIdNum());
                ps.setString(102, d.getCrdIssIdNum());
                ps.setString(103, d.getOrigMsgType());
                ps.setString(104, d.getOrigTranTim());
                ps.setString(105, d.getOrigTranDate());
                ps.setString(106, d.getOrigSeqNum());
                ps.setString(107, d.getOrigB24PostDate());
                ps.setString(108, d.getExcpRsnCode());
                ps.setString(109, d.getOvrrdeFlag());
                ps.setString(110, d.getAddr());
                ps.setString(111, d.getZip());
                ps.setString(112, d.getAddrVerfyStat());
                ps.setString(113, d.getPinInd());
                ps.setString(114, d.getPinTries());
                ps.setString(115, d.getPreAuthTs());
                ps.setString(116, d.getPreAuthHldsStat());
                ps.setString(117, d.getUserFid2());
                ps.setString(118, d.getUserDataDLen());
                ps.setString(119, d.getUserDataDInfo());
                ps.setString(120, d.getDcrsRemarks());
                ps.setString(121, d.getFileDate());
                ps.setString(122, d.getFilename());
            }

            public int getBatchSize() {
                return dataList.size();
            }
        });
    }
}
