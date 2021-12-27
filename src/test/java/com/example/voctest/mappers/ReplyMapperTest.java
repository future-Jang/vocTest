package com.example.voctest.mappers;

import com.example.voctest.beans.vo.Criteria;
import com.example.voctest.beans.vo.ReplyVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTest {
    private Long[] arBno = {7L,15L,16L,17L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void testMapper(){
        log.info(replyMapper.toString());
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO reply = new ReplyVO();
            reply.setVocNum(arBno[i % 5]);
            reply.setReply("댓글 테스트" + i);
            reply.setReplier("replier" + i);
            replyMapper.insert(reply);
        });
    }

    @Test
    public void testRead(){
        log.info(replyMapper.read(21L).toString());
    }

    @Test
    public void testUpdate(){
        ReplyVO replyVO = replyMapper.read(22L);
        replyVO.setReply("UPDATE TEST");
        log.info("UPDATE COUNT : " + replyMapper.update(replyVO));
    }


    @Test
    public void testGetListWithPaging(){
        Criteria criteria = new Criteria();
        replyMapper.getListWithPaging(arBno[0], criteria).forEach(reply -> log.info(reply.toString()));
    }
}
