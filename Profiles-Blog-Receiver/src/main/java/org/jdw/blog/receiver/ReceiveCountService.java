package org.jdw.blog.receiver;

import javax.transaction.Transactional;

import org.jdw.blog.common.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReceiveCountService {

    @Autowired
    private ReceiveCountRepository receiveCountRepository;

    public ReceiveCount incrementReceiveCount(PayloadType payloadType) {
        ReceiveCount receiveCount = receiveCountRepository.findOne(payloadType);
        if (receiveCount == null) {
            receiveCount = new ReceiveCount();
            receiveCount.setPayloadType(payloadType);
        }
        receiveCount.setTimesReceived(receiveCount.getTimesReceived() + 1);
        return receiveCountRepository.save(receiveCount);
    }

    public ReceiveCount findReceiveCount(PayloadType payloadType) {
        ReceiveCount receiveCount = receiveCountRepository.findOne(payloadType);
        if (receiveCount == null) {
            receiveCount = new ReceiveCount();
            receiveCount.setPayloadType(payloadType);
            receiveCount = receiveCountRepository.save(receiveCount);
        }
        return receiveCount;
    }

}
