package com.example.voctest.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class VocVO {
    private Long vocNum;
    private String memberNum;
    private String vocTitle;
    private int memberStatus;
    private String vocDetail;
    private String vocPenalty;
    private Long vocPenaltySum;
    private String vocDate;
    private String startDate;
    private String endDate;

    private List<AttachFileVO> attachList;
    }
