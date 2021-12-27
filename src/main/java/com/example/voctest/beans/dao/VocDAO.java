package com.example.voctest.beans.dao;

import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.VocVO;
import com.example.voctest.mappers.VocMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VocDAO {
    private final VocMapper mapper;

    public void register(VocVO vocVO) {
        mapper.insertSelectKey_vocNum(vocVO);
    }

    public VocVO get(Long vocNum) {
        return mapper.read(vocNum);
    }

    public boolean modify(VocVO voc) {
        return mapper.update(voc) == 1;
    }

    public boolean remove(Long vocNum) {
        return mapper.remove(vocNum) == 1;
    }

    public List<VocVO> getList(Criteria criteria) {
        return mapper.getList(criteria);
    }

    public int getTotal(Criteria criteria) {
        return mapper.getTotal(criteria);
    }

    public boolean updateOk(Long vocNum) {return  mapper.updateOk(vocNum) == 1; }

    public List<VocVO> getDate(VocVO vocVO){return mapper.getDate(vocVO);}
}