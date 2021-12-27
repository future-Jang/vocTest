package com.example.voctest.services;

import com.example.voctest.beans.vo.AttachFileVO;
import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.VocVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VocService {
    public void register(VocVO voc);
    public VocVO get(Long vocNum);
    public boolean modify(VocVO voc);
    public boolean updateOk(Long vocNum);
    public boolean remove(Long vocNum);
    public List<VocVO> getList(Criteria criteria);
    public int getTotal(Criteria criteria);
    public List<AttachFileVO> getAttachList(Long vocNum);
    public List<VocVO> getDate(VocVO vocVO);
}
