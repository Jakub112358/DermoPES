package com.dermopes;

import com.dermopes.repository.QuestionRepository;
import com.dermopes.util.DBOperations;
import com.dermopes.util.RequestSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc (addFilters = false)
public class BaseIT {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected DBOperations dbOperations;
    @Autowired
    protected RequestSender requestSender;
    @Autowired
    protected QuestionRepository questionRepository;

    @SneakyThrows
    protected <T> String toJsonString(T obj) {
        return objectMapper.writeValueAsString(obj);
    }

}
