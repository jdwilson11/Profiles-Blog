package org.jdw.blog.sender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jdw.blog.common.Payload;
import org.jdw.blog.common.PayloadType;

/**
 * Records the number of times a {@link Payload} of type {@link PayloadType} has been sent.
 */
@Entity
@Table(name = "PAYLOAD_RECORD")
public class SendCount {

    @Id
    @NotNull
    @Column(name = "PAYLOAD_TYPE")
    @Enumerated(EnumType.STRING)
    private PayloadType payloadType;

    @NotNull
    @Column(name = "TIMES_SENT")
    private int timesSent = 0;

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    public int getTimesSent() {
        return timesSent;
    }

    public void setTimesSent(int timesSent) {
        this.timesSent = timesSent;
    }

}
