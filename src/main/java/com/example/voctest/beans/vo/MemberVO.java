package com.example.voctest.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private String memberNum;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private long salary;
}
