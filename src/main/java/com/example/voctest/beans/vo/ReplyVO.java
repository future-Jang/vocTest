package com.example.voctest.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVO {
    private Long rno;
    private Long vocNum;
    private String reply;
    private String replier;
    private String replyDate;
    private String updateDate;
}
