package com.example.voctest.mappers;

import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    //    댓글 등록
    public int insert(ReplyVO replyVO);
    //    댓글 1개 조회
    public ReplyVO read(Long rno);
    //    댓글 삭제
    public int delete(Long rno);
    //    댓글 수정
    public int update(ReplyVO replyVO);
    //    댓글 목록
    public List<ReplyVO> getListWithPaging(@Param("vocNum") Long vocNum, @Param("criteria") Criteria criteria);

    //    댓글 개수
    public int getTotal(Long vocNum);
}
