package com.example.voctest.controller;

import com.example.voctest.beans.vo.AttachFileVO;
import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.PageDTO;
import com.example.voctest.beans.vo.VocVO;
import com.example.voctest.services.VocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/voc/*")
@RequiredArgsConstructor
public class VocController {
    private final VocService vocService;

    @GetMapping("list")
    public String list(Criteria criteria, Model model, HttpServletRequest request,Long vocNum){
        HttpSession session = request.getSession();
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");
        model.addAttribute("list", vocService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(vocService.getTotal(criteria), 10, criteria));
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        return "voc/list";
    }

    @GetMapping("vocList")
    public String vocList(Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("list", vocService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(vocService.getTotal(criteria), 10, criteria));
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        return "voc/vocList";
    }

    @GetMapping("vocDate")
    public String vocDate(VocVO vocVO, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("getDate",vocService.getDate(vocVO));
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        return "voc/vocDate";
    }

    @PostMapping("register")
    public RedirectView register(VocVO vocVO, RedirectAttributes rttr, Model model, HttpServletRequest request){
        log.info("-------------현재 멤버------------------");
        log.info("register : " + vocVO.toString());
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        log.info("-------------멤버 확인------------------");
        log.info("memberNum : " + session.getAttribute("memberNum"));
        log.info("-------------------------------");

        if(vocVO.getAttachList() != null){
            vocVO.getAttachList().forEach(attach -> log.info(attach.toString()));
        }
        vocService.register(vocVO);


        rttr.addFlashAttribute("memberNum", vocVO.getMemberNum());
        log.info("------------------다시 뽑기----------------------");
        log.info("register : " + vocVO.toString());
        log.info("-------------------------------");

        return new RedirectView("list");
    }
    @GetMapping("register")
    public void register(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
    }
    //    게시글 첨부파일
    @GetMapping(value = "getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AttachFileVO> getAttachList(Long vocNum){
        log.info("getAttachList " + vocNum);
        return vocService.getAttachList(vocNum);
    }

    private void deleteFiles(List<AttachFileVO> attachList){
        if(attachList == null || attachList.size() == 0){
            return;
        }

        log.info("delete attach files...........");
        log.info(attachList.toString());

        attachList.forEach(attach -> {
            try {
                Path file = Paths.get("C:/upload/" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());
                Files.delete(file);

                if(Files.probeContentType(file).startsWith("image")){
                    Path thumbnail = Paths.get("C:/upload/" + attach.getUploadPath() + "/s_" + attach.getUuid() + "_" + attach.getFileName());
                    Files.delete(thumbnail);
                }
            } catch (Exception e) {
                log.error("delete file error " + e.getMessage());
            }
        });


    }


    @GetMapping({"read", "modify"})
    public void read(@RequestParam("vocNum") Long vocNum,Criteria criteria, Model model, HttpServletRequest request){
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 5);
        //read 요청 시 read 출력
        //modify 요청 시 modify 출력
        log.info("-------------------------------");
        log.info(reqType + " : " + vocNum);
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));

        model.addAttribute("voc", vocService.get(vocNum));
        model.addAttribute("criteria", criteria);
    }

    @PostMapping("modify")
    public RedirectView modify(VocVO vocVO, RedirectAttributes rttr, HttpServletRequest request,Model model){
        log.info("-------------------------------");
        log.info("modify : " + vocVO.toString());
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));


        if(vocService.modify(vocVO)){
            rttr.addAttribute("result", "success");
            rttr.addAttribute("vocNum", vocVO.getVocNum());
        }
        return new RedirectView("read");
    }

    @PostMapping("read")
    public RedirectView updateOk(Long vocNum, VocVO vocVO, RedirectAttributes rttr, HttpServletRequest request, Model model){
        log.info("-------------------------------");
        log.info("updateOk : " + vocNum);
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));

        if(vocService.updateOk(vocNum)){
            rttr.addAttribute("vocNum", vocVO.getVocNum());
            rttr.addAttribute("memberNum",vocVO.getMemberNum());
        }
        return new RedirectView("read");
    }

    @PostMapping("remove")
    public RedirectView remove(@RequestParam("vocNum") Long vocNum, RedirectAttributes rttr) {
        log.info("-------------------------------");
        log.info("remove : " + vocNum);
        log.info("-------------------------------");


        List<AttachFileVO> attachList = vocService.getAttachList(vocNum);

        if (vocService.remove(vocNum)) {
            deleteFiles(attachList);
            rttr.addFlashAttribute("result", "success");
        } else {
            rttr.addFlashAttribute("result", "fail");
        }
        return new RedirectView("list");
    }


}
