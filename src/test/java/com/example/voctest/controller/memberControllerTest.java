package com.example.voctest.controller;

import com.example.voctest.beans.dao.MemberDAO;
import com.example.voctest.beans.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class memberControllerTest {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void testLogin(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberNum("11111");
        memberVO.setMemberPw("test");
        memberDAO.login(memberVO);
    }
}
