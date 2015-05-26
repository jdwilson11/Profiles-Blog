package org.jdw.blog.sender;

import javax.transaction.Transactional;

import org.jdw.blog.common.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SendCountService {

    @Autowired
    private SendCountRepository sendCountRepository;

    public SendCount incrementSendCount(PayloadType payloadType) {
        SendCount sendCount = sendCountRepository.findOne(payloadType);
        if (sendCount == null) {
            sendCount = new SendCount();
            sendCount.setPayloadType(payloadType);
        }
        sendCount.setTimesSent(sendCount.getTimesSent() + 1);
        return sendCountRepository.save(sendCount);
    }

    public SendCount findSendCount(PayloadType payloadType) {
        SendCount sendCount = sendCountRepository.findOne(payloadType);
        if (sendCount == null) {
            sendCount = new SendCount();
            sendCount.setPayloadType(payloadType);
            sendCount = sendCountRepository.save(sendCount);
        }
        return sendCount;
    }

}
