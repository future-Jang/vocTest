package com.example.voctest.mappers;

import com.example.voctest.beans.vo.MemberVO;
import com.example.voctest.beans.vo.VocVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
public class VocMapperTest {
    @Autowired
    private VocMapper vocMapper;

    @Test
    public void getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        VocVO vocVO = new VocVO();
        log.info(vocMapper.getDate(vocVO).toString());
    }

}
