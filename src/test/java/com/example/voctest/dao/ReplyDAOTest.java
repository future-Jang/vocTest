package com.example.voctest.dao;

import com.example.voctest.beans.dao.ReplyDAO;
import com.example.voctest.beans.vo.Criteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyDAOTest {
    private Long[] arBno = {7L,15L,16L,17L};

    @Setter(onMethod_ = @Autowired)
    private ReplyDAO replyDAO;

    @Test
    public void testRead(){
        log.info(replyDAO.get(21L).toString());
    }

    @Test
    public void testGetListWithPaging(){
        Criteria criteria = new Criteria();
        replyDAO.getList(arBno[0], criteria).forEach(reply -> log.info(reply.toString()));
    }


}
