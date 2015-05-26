package org.jdw.blog.receiver;

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

public class ReceiverControllerTest extends BaseWebMvcJUnitTest {

    @Test
    public void testFindReceiveCount() throws Exception {
        // Relies on pre-existing mock data injected via Liquibase in the "test" profile

        ResultActions actions = mockMvc.perform(
                get("/" + PayloadType.TEST1 + "/count"));
        MvcResult result = actions.andExpect(
                status().isOk())
                .andReturn();
        String sendCountJson = result.getResponse().getContentAsString();

        ReceiveCount sendCount = deserializeReceiveCount(sendCountJson);
        assertEquals(PayloadType.TEST1, sendCount.getPayloadType());
        assertEquals(1, sendCount.getTimesReceived());
    }

    private ReceiveCount deserializeReceiveCount(String sendCountJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<ReceiveCount> typeRef = new TypeReference<ReceiveCount>() {
        };
        ReceiveCount sendCount = objectMapper.readValue(sendCountJson, typeRef);
        return sendCount;
    }

}
