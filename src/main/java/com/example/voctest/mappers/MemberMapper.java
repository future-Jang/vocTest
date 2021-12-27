package com.example.voctest.mappers;

import com.example.voctest.beans.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //회원정보
    public void insert(MemberVO memberVO);
    //로그인
    public MemberVO select(MemberVO memberVO);
    //월급 변경
    public void change(MemberVO memberVO);
    //정보
    public MemberVO info(String memberNum);

}
