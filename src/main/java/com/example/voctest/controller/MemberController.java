package com.example.voctest.controller;

import com.example.voctest.beans.vo.MemberVO;
import com.example.voctest.services.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("login")
    public void login(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum", session.getAttribute("memberNum"));
    }
    @PostMapping("login")
    public String login(MemberVO memberVO, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        MemberVO vo = memberService.login(memberVO);
        boolean check = vo != null;
        if (check) {
            session.setAttribute("memberNum", vo.getMemberNum());
            log.info("memberNum : " + session.getAttribute("memberNum"));
            log.info("memberPw : " + session.getAttribute("memberPw"));
            return "redirect:/voc/list";
        }else{
            model.addAttribute("flag","false");
            return "/member/login";
        }
    }


    @GetMapping("join")
    public void join(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum", session.getAttribute("memberNum"));
    }

    @PostMapping("join")
    public String join(MemberVO memberVO,HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum", session.getAttribute("memberNum"));
        memberService.join(memberVO);
        return "member/join";
    }

    @PostMapping("modifySal")
    public String modifySal(MemberVO memberVO, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("check", session.getAttribute("memberNum"));
        model.addAttribute("memberNum", memberVO.getMemberNum());
        memberService.modifySal(memberVO);
        return "member/login";
    }

    @GetMapping("info")
    public String info(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("-------------------------------");
        log.info("info");
        log.info("-------------------------------");
        model.addAttribute("info", memberService.getMyInfo("memberNum"));
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        return "voc/list";
    }



}
