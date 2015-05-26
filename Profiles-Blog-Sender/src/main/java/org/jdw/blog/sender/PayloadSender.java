package org.jdw.blog.sender;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.jdw.blog.common.Payload;
import org.jdw.blog.common.PayloadType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class PayloadSender {

    private RestTemplate restTemplate;

    @PostConstruct
    public void initialize() {
        restTemplate = new RestTemplate();

        // Loose JSON serialization defaults
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        restTemplate.setMessageConverters(Arrays.asList(converter));

        // Timeout settings
        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        int tenSeconds = 10000;
        requestFactory.setReadTimeout(tenSeconds);
        requestFactory.setConnectTimeout(tenSeconds);
    }

    public void sendPayloadToReceiver(PayloadType payloadType) {
        Payload payload = new Payload();
        payload.setPayloadType(payloadType);

        // Rudimentary destination hard-coding
        // Only sends to a local receiver
        // Receiver's port taken from Profiles-Blog-Receiver's application.properties
        String url = "http://localhost:9002/receive";

        // Invoke the endpoint in the Profiles-Blog-Receiver project
        restTemplate.postForEntity(url, payload, Void.class);
    }

}
