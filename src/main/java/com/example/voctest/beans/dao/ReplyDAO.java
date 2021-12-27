package com.example.voctest.beans.dao;

import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.ReplyVO;
import com.example.voctest.mappers.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReplyDAO {
    private final ReplyMapper replyMapper;

    public int register(ReplyVO replyVO){
        log.info("register..............");
        return replyMapper.insert(replyVO);
    }

    public ReplyVO get(Long rno){
        log.info("get..............");
        return replyMapper.read(rno);
    }

    public int remove(Long rno){
        log.info("remove..............");
        return replyMapper.delete(rno);
    }

    public int modify(ReplyVO replyVO){
        log.info("modify..............");
        return replyMapper.update(replyVO);
    }

    public List<ReplyVO> getList(Long vocNum, Criteria criteria){
        log.info("getList..............");
        return replyMapper.getListWithPaging(vocNum, criteria);
    }

    public int getTotal(Long vocNum){
        log.info("getTotal.............");
        return replyMapper.getTotal(vocNum);
    }
}
