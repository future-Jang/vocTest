package com.example.voctest.mappers;


import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.VocVO;

import java.util.List;

public interface VocMapper {
    //    게시글 목록
    public List<VocVO> getList(Criteria criteria);

    //    게시글 추가
    public void insert(VocVO vocVo);

    //    게시글 추가(PK가져오기)
    public void insertSelectKey_vocNum(VocVO vocVO);

    //    게시글 상세보기(특정 게시글 정보)
    public VocVO read(Long vocNum);

    //    게시글 수정
    public int update(VocVO vocVo);

    //  게시글 수정 후
    public int updateOk(Long vocNum);

    //    게시글 삭제
    public int remove(Long vocNum);

    //    게시글 전체 개수
    public int getTotal(Criteria criteria);

    //새 월급
    public List<VocVO> getDate(VocVO vocVo);

}
