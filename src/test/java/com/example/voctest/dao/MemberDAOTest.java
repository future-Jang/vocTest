package com.example.voctest.dao;

import com.example.voctest.beans.dao.MemberDAO;
import com.example.voctest.beans.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberDAOTest {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void testLogin(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberName("고객사");
        memberVO.setMemberNum("11111");
        memberVO.setMemberPw("1234");
        memberDAO.login(memberVO);
    }

    @Test
    public void testGetMyInfo(){
        memberDAO.getMyInfo("11111");
    }



}
