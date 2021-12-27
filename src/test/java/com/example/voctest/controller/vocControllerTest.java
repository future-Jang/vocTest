package com.example.voctest.controller;

import com.example.voctest.beans.vo.VocVO;
import com.example.voctest.services.VocService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class vocControllerTest {
    @Autowired
    private VocService vocService ;

    @Test
    public void testList() throws Exception {
        log.info("목록출력...............");
        VocVO vocVo = new VocVO();
        vocService.getDate(vocVo).forEach(vocVO -> vocVO.toString());
    }


    //    가짜 MVC
//    마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어준다.
/*    private MockMvc mockMvc;

    //    요청을 처리해주는 WebApplicationContext를 불러온다.
    @Autowired
    private WebApplicationContext webApplicationContext;

    //    하위의 모든 테스트가 실행 전에 실행되도록 한다.
    @BeforeEach
    public void setUp(){
//        가짜 MVC에 WebApplicationContext를 전달한 후 환경을 생성한다.
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }*/

/*    @Test
    public void testRegister() throws Exception {
        String memberNum = mockMvc.perform(
                MockMvcRequestBuilders.post("/voc/register")
                        .param("vocNum","1")
                        .param("memberNum", "11111")
                        .param("vocTitle","테스트 새 글 제목")
                        .param("memberStatus","0")
                        .param("vocDetail", "테스트 새 글 내용")
                        .param("vocPenalty", "테스트 새 패널티내용")
                        .param("vocPenaltySum","30")
        ).andReturn().getFlashMap().toString();
        log.info(memberNum);
    }*//*

    @Test
    public void testRead() throws Exception {
        String voc = mockMvc.perform(
                MockMvcRequestBuilders.get("/voc/read")
                        .param("vocNum", "7")
        ).andReturn().getModelAndView().getViewName();

        log.info(voc);
    }

    @Test
    public void testModify() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/voc/modify")
                        .param("vocNum","6")
                        .param("memberNum", "55555")
                        .param("vocTitle", "수정된 테스트 새 글 제목")
                        .param("memberStatus","0")
                        .param("vocDetail", "수정된 테스트 새 글 내용")
                        .param("vocPenalty", "수정된 패널티")
                        .param("vocPenaltySum","80")
        ).andReturn().getModelAndView().getModelMap().toString();
        log.info(result);
    }

    @Test
    public void testRemove() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/voc/remove").param("vocNum", "6"))
                .andReturn().getFlashMap().toString();
        log.info(result);
    }

    @Test
    public void updateOk() throws Exception{
        log.info("===================================");
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/voc/read").param("vocNum", "7"))
                .andReturn().getFlashMap().toString();
        log.info("=========test======"+result);
    }*/

}
