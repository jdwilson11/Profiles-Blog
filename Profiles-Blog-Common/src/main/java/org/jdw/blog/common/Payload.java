package org.jdw.blog.common;


/**
 * Sent from the "sender" project to be persisted in the "receiver" project.
 */
public class Payload {

    private PayloadType payloadType;

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

}
