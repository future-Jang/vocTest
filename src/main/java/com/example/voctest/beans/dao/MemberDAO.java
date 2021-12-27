package com.example.voctest.beans.dao;

import com.example.voctest.beans.vo.MemberVO;
import com.example.voctest.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public void join(MemberVO memberVO){memberMapper.insert(memberVO);}
    public MemberVO login(MemberVO memberVO){return memberMapper.select(memberVO);}
    public void modifySal(MemberVO memberVO){memberMapper.change(memberVO);}
    public MemberVO getMyInfo(String memberNum){return memberMapper.info(memberNum);}


}
