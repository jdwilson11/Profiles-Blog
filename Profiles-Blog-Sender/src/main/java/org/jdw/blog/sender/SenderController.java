package org.jdw.blog.sender;

import org.jdw.blog.common.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {

    @Autowired
    private SendCountService sendCountService;

    @Autowired
    private PayloadSender payloadSender;

    @RequestMapping("/{payloadType}/send")
    public SendCount sendPayload(@PathVariable("payloadType") PayloadType payloadType) {
        payloadSender.sendPayloadToReceiver(payloadType);
        return sendCountService.incrementSendCount(payloadType);
    }

    @RequestMapping("/{payloadType}/count")
    public SendCount findSendCount(@PathVariable("payloadType") PayloadType payloadType) {
        return sendCountService.findSendCount(payloadType);
    }

}
