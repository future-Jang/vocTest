package com.example.voctest.beans.dao;


import com.example.voctest.beans.vo.AttachFileVO;
import com.example.voctest.mappers.AttachFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachFileDAO {
    @Autowired
    private AttachFileMapper attachFileMapper;

    public void insert(AttachFileVO attachFileVO){
        attachFileMapper.insert(attachFileVO);
    }

    public void delete(String uuid){
        attachFileMapper.delete(uuid);
    }

    public List<AttachFileVO> findByVocNum(Long vocNum){
        return attachFileMapper.findByVocNum(vocNum);
    }

    public void deleteAll(Long vocNum){attachFileMapper.deleteAll(vocNum);}

    public List<AttachFileVO> getOldFiles() {return attachFileMapper.getOldFiles();}
}
