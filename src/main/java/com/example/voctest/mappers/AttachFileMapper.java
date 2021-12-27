package com.example.voctest.mappers;

import com.example.voctest.beans.vo.AttachFileVO;

import java.util.List;

public interface AttachFileMapper {
    public void insert(AttachFileVO attachFileVO);
    public void delete(String uuid);
    public List<AttachFileVO> findByVocNum(Long vocNum);
    public void deleteAll(Long vocNum);
    public List<AttachFileVO> getOldFiles();
}
