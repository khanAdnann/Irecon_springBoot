package com.irecon.innovation.dao;

import com.irecon.innovation.entity.SwitchTLFRawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SwitchTLFRawDataJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void batchInsert(List<SwitchTLFRawData> dataList) {
		String InsertQuery = "INSERT INTO   switch_atm_rawdata (DATE_TIME, REC_TYPE, AUTH_PPD,  TERM_LN, TERM_FIID, TERM_TERM_ID,  CRD_LN, CRD_FIID,CRD_PAN, CRD_MBR_NUM, BRCH_ID, REGN_ID, USER_FLD1X, TYP_CDE, TYP, RTE_STAT,ORIGINATOR, RESPONDER, ENTRY_TIME, EXIT_TIME, RE_ENTRY_TIME, TRAN_DATE, TRAN_TIM, POST_DAT, ACQ_ICHG_SETL_DAT, ISS_ICHG_SETL_DAT, SEQ_NUM, TERM_TYP, TIM_OFST, ACQ_INST_ID_NUM, RCV_INST_ID_NUM, TRAN_CDE, FROM_ACCT, USER_FLD1, TO_ACCT, MULT_ACCT, AMT_1, AMT_2, AMT_3, DEP_BAL_CR, DEP_TYP, RESP_CDE, TERM_NAME_LOC, TERM_OWNER_NAME, TERM_CITY, TERM_ST, TERM_CNTRY, ORIG_OSEQ_NUM, ORIG_OTRAN_DAT, ORIG_OTRAN_TIM, ORIG_B24_POST, ORIG_CRNCY_CDE,  MULT_CRNCY_AUTH_CRNCY_CDE, MULT_CRNCY_AUTH_CONV_RATE, MULT_CRNCY_SETL_CRNCY_CDE, MULT_CRNCY_SETL_CONV_RATE, MULT_CRNCY_CONV_DAT_TIM, RVSL_RSN, PIN_OFST, SHRG_GRP, DEST_ORDER, AUTH_ID_RESP, REFR_IMP_IND, REFR_AVAIL_IMP, REFR_LEDG_IMP, REFR_HLD_AMT_IMP, REFR_CAF_REFR_IND, REFR_USER_FLD3, DEP_SETL_IMP_FLG, ADJ_SETL_IMP_FLG, REFR_IND, USER_FLD4, FRWD_INST_ID_NUM, CRD_ACCPT_ID_NUM, CRD_ISS_ID_NUM, USER_FLD6,DCRS_REMARKS,FILEDATE,FILENAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,STR_to_date(?,'%Y/%m/%d'),?)";

        jdbcTemplate.batchUpdate(InsertQuery, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SwitchTLFRawData d = dataList.get(i);
                ps.setString(1, d.getDateTime());
                ps.setString(2, d.getRecType());
                ps.setString(3, d.getAuthPpd());
                ps.setString(4, d.getTermLn());
                ps.setString(5, d.getTermFiid());
                ps.setString(6, d.getTermTermId());
                ps.setString(7, d.getCrdLn());
                ps.setString(8, d.getCrdFiid());
                ps.setString(9, d.getCrdPan());
                ps.setString(10, d.getCrdMbrNum());
                ps.setString(11, d.getBrchId());
                ps.setString(12, d.getRegnId());
                ps.setString(13, d.getUserFld1x());
                ps.setString(14, d.getTypCde());
                ps.setString(15, d.getTyp());
                ps.setString(16, d.getRteStat());
                ps.setString(17, d.getOriginator());
                ps.setString(18, d.getResponder());
                ps.setString(19, d.getEntryTime());
                ps.setString(20, d.getExitTime());
                ps.setString(21, d.getReEntryTime());
                ps.setString(22, d.getTranDate());
                ps.setString(23, d.getTranTim());
                ps.setString(24, d.getPostDat());
                ps.setString(25, d.getAcqIchgSetlDat());
                ps.setString(26, d.getIssIchgSetlDat());
                ps.setString(27, d.getTermTyp());
                ps.setString(28, d.getTimOfst());
                ps.setString(29, d.getAcqInstIdNum());
                ps.setString(30, d.getRcvInstIdNum());
                ps.setString(31, d.getTranCde());
                ps.setString(32, d.getFromAcct());
                ps.setString(33, d.getUserFld1());
                ps.setString(34, d.getToAcct());
                ps.setString(35, d.getMultAcct());
                ps.setString(36, d.getAmt1());
                ps.setString(37, d.getAmt2());
                ps.setString(38, d.getAmt3());
                ps.setString(39, d.getDepBalCr());
                ps.setString(40, d.getDepTyp());
                ps.setString(41, d.getRespCde());
                ps.setString(42, d.getTermNameLoc());
                ps.setString(43, d.getTermOwnerName());
                ps.setString(44, d.getTermCity());
                ps.setString(45, d.getTermSt());
                ps.setString(46, d.getTermCntry());
                ps.setString(47, d.getOrigOseqNum());
                ps.setString(48, d.getOrigOtranDat());
                ps.setString(49, d.getOrigOtranTim());
                ps.setString(50, d.getOrigB24Post());
                ps.setString(51, d.getOrigCrncyCde());
                ps.setString(52, d.getMultCrncyAuthCrncyCde());
                ps.setString(53, d.getMultCrncyAuthConvRate());
                ps.setString(54, d.getMultCrncySetlCrncyCde());
                ps.setString(55, d.getMultCrncySetlConvRate());
                ps.setString(56, d.getMultCrncyConvDatTim());
                ps.setString(57, d.getRvslRsn());
                ps.setString(58, d.getPinOfst());
                ps.setString(59, d.getShrgGrp());
                ps.setString(60, d.getDestOrder());
                ps.setString(61, d.getAuthIdResp());
                ps.setString(62, d.getRefrImpInd());
                ps.setString(63, d.getRefrAvailImp());
                ps.setString(64, d.getRefrLedgImp());
                ps.setString(65, d.getRefrHldAmtImp());
                ps.setString(66, d.getRefrCafRefrInd());
                ps.setString(67, d.getRefrUserFld3());
                ps.setString(68, d.getDepSetlImpFlg());
                ps.setString(69, d.getAdjSetlImpFlg());
                ps.setString(70, d.getRefrInd());
                ps.setString(71, d.getUserFld4());
                ps.setString(72, d.getFrwdInstIdNum());
                ps.setString(73, d.getCrdAccptIdNum());
                ps.setString(74, d.getCrdIssIdNum());
                ps.setString(75, d.getUserFld6());
                ps.setString(76, d.getDcrsRemarks());
             
                ps.setString(77, d.getFileName());
                ps.setString(78, d.getFileDate());
                ps.setString(79, d.getDcrsRemarks());
            }

            public int getBatchSize() {
                return dataList.size();
            }
        });
    }
}
