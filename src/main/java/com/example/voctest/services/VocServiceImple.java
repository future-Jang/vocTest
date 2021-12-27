package com.example.voctest.services;

import com.example.voctest.beans.dao.AttachFileDAO;
import com.example.voctest.beans.dao.VocDAO;
import com.example.voctest.beans.vo.AttachFileVO;
import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.VocVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocServiceImple implements VocService{
    private final VocDAO vocDAO;
    private final AttachFileDAO attachFileDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(VocVO vocVO) {
        vocDAO.register(vocVO);
        if(vocVO.getAttachList() == null || vocVO.getAttachList().size() == 0){
            return;
        }

        vocVO.getAttachList().forEach(attach -> {
            attach.setVocNum(vocVO.getVocNum());
            attachFileDAO.insert(attach);
        });
    }

    @Override
    public VocVO get(Long vocNum) {
        return vocDAO.get(vocNum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modify(VocVO vocVo) {
        boolean boardModifyResult = false;

        attachFileDAO.deleteAll(vocVo.getVocNum());
        boardModifyResult = vocDAO.modify(vocVo);

        if(boardModifyResult && vocVo.getAttachList() != null && vocVo.getAttachList().size() != 0){
            vocVo.getAttachList().forEach(attach -> {
                attach.setVocNum(vocVo.getVocNum());
                attachFileDAO.insert(attach);
            });
        }
        return boardModifyResult;
    }

    @Override
    public boolean updateOk(Long vocNum) {  return vocDAO.updateOk(vocNum); }

    @Override
    public boolean remove(Long vocNum) {
        return vocDAO.remove(vocNum);
    }

    @Override
    public List<VocVO> getList(Criteria criteria) { return vocDAO.getList(criteria); }

    @Override
    public int getTotal(Criteria criteria) { return vocDAO.getTotal(criteria); }

    @Override
    public List<AttachFileVO> getAttachList(Long vocNum) {
        return attachFileDAO.findByVocNum(vocNum);
    }

    @Override
    public List<VocVO> getDate(VocVO vocVO) {
        return vocDAO.getDate(vocVO);
    }


}
