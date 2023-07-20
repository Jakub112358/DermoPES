package com.dermopes.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
@RequiredArgsConstructor
public class RequestSender {

    private final MockMvc mockMvc;

    public ResultActions sendGetRequest(String path) throws Exception {
        return mockMvc.perform(get(path));
    }

    public ResultActions sendPatchRequest(String path, String request) throws Exception {
        return mockMvc.perform(patch(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(request));
    }

    public ResultActions sendPostRequest(String path, String request) throws Exception {
        return mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE).content(request));
    }

    public ResultActions sendDeleteRequest(String path) throws Exception {
        return mockMvc.perform(delete(path));
    }

}
