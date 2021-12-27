package com.example.voctest.services;

import com.example.voctest.beans.dao.MemberDAO;
import com.example.voctest.beans.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;

    public void join(MemberVO memberVO){memberDAO.join(memberVO);}
    public MemberVO login(MemberVO memberVO){return memberDAO.login(memberVO);}
    public void modifySal(MemberVO memberVO){memberDAO.modifySal(memberVO);}
    public MemberVO getMyInfo(String memberNum){return memberDAO.getMyInfo(memberNum);}
}
