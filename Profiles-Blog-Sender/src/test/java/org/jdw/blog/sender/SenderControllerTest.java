package org.jdw.blog.sender;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jdw.blog.BaseWebMvcJUnitTest;
import org.jdw.blog.common.PayloadType;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SenderControllerTest extends BaseWebMvcJUnitTest {

    @Test
    public void testFindSendCount() throws Exception {
        // Relies on pre-existing mock data injected via Liquibase in the "test" profile

        ResultActions actions = mockMvc.perform(
                get("/" + PayloadType.TEST1 + "/count"));
        MvcResult result = actions.andExpect(
                status().isOk())
                .andReturn();
        String sendCountJson = result.getResponse().getContentAsString();

        SendCount sendCount = deserializeSendCount(sendCountJson);
        assertEquals(PayloadType.TEST1, sendCount.getPayloadType());
        assertEquals(1, sendCount.getTimesSent());
    }

    private SendCount deserializeSendCount(String sendCountJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<SendCount> typeRef = new TypeReference<SendCount>() {
        };
        SendCount sendCount = objectMapper.readValue(sendCountJson, typeRef);
        return sendCount;
    }

}
