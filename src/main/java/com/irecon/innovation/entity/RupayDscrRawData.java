package com.irecon.innovation.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rupay_dscr_rawdata")
public class RupayDscrRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Assuming there's a primary key

    @Column(length = 80)
    private String bankName;

    @Column(length = 80)
    private String settBin;

    @Column(length = 80)
    private String issBin;

    @Column(length = 80)
    private String inwardOutward;

    @Column(length = 80)
    private String status;

    @Column(length = 80)
    private String txnCycle;

    @Column(length = 80)
    private String txnType;

    @Column(length = 80)
    private String channel;

    private Integer txnCount;

    @Column(length = 80)
    private String txnCcy;

    private BigDecimal txnAmtDr;

    private BigDecimal txnAmtCr;

    @Column(length = 80)
    private String setCcy;

    private BigDecimal setAmtDr;

    private BigDecimal setAmtCr;

    private BigDecimal intFeeDr;

    private BigDecimal intFeeCr;

    private BigDecimal memIncFeeDr;

    private BigDecimal memIncFeeCr;

    private BigDecimal cusCompenDr;

    private BigDecimal cusCompenCr;

    private BigDecimal othFeeAmtDr;

    private BigDecimal othFeeAmtCr;

    private BigDecimal othFeeGstDr;

    private BigDecimal othFeeGstCr;

    private BigDecimal finalSumCr;

    private BigDecimal finalSumDr;

    private BigDecimal finalNet;

    private LocalDate fileDate;

    @Column(length = 80)
    private String createdBy;

    @Column(length = 80)
    private String cycle;

    @Column(length = 80)
    private String filename;

    @Column(length = 80)
    private String settlementDate;

    // Getters and Setters
}
