package com.example.voctest.controller;

import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.ReplyPageDTO;
import com.example.voctest.beans.vo.ReplyVO;
import com.example.voctest.services.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {

    private final ReplyService replyService;
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {

        int replyCount = replyService.register(replyVO);
        log.info("ReplyVO : " + replyVO);
        log.info("REPLY INSERT COUNT : " + replyCount);
        return replyCount == 1 ?
                new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    게시글 댓글 전체 조회
    @GetMapping("pages/{vocNum}/{page}")
    public ReplyPageDTO getList(@PathVariable("vocNum") Long vocNum, @PathVariable("page") int page){
        log.info("getList............");
        Criteria criteria = new Criteria(page, 10);
        log.info(criteria.toString());
        return replyService.getList(vocNum, criteria);
    }

    //    댓글 조회
    @GetMapping("{rno}")
    public ReplyVO get(@PathVariable("rno") Long rno){
        log.info("get............");
        return replyService.get(rno);
    }

    //    댓글 수정
    @RequestMapping(
            method={RequestMethod.PUT, RequestMethod.PATCH},
            value="{rno}", consumes = "application/json", produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO, @PathVariable("rno") Long rno) throws UnsupportedEncodingException{
        log.info("modify...............");
        replyVO.setRno(rno);
        return replyService.modify(replyVO) == 1 ?
                new ResponseEntity<>(new String("댓글 수정 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    댓글 삭제
    @DeleteMapping(value="{rno}", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) throws UnsupportedEncodingException{
        log.info("remove.............");
        return replyService.remove(rno) == 1 ?
                new ResponseEntity<>(new String("댓글 삭제 성공".getBytes(), "UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}