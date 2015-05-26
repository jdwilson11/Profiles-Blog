package org.jdw.blog.receiver;

import org.jdw.blog.common.Payload;
import org.jdw.blog.common.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {

    @Autowired
    private ReceiveCountService receiveCountService;

    @RequestMapping(method = RequestMethod.POST, value = "/receive")
    public void receivePayload(@RequestBody Payload payload) {
        receiveCountService.incrementReceiveCount(payload.getPayloadType());
    }

    @RequestMapping("/{payloadType}/count")
    public ReceiveCount findReceiveCount(@PathVariable("payloadType") PayloadType payloadType) {
        return receiveCountService.findReceiveCount(payloadType);
    }

}
