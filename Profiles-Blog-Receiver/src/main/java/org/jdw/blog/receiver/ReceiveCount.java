package org.jdw.blog.receiver;

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
 * Records the number of times a {@link Payload} of type {@link PayloadType} has been received.
 */
@Entity
@Table(name = "PAYLOAD_RECORD")
public class ReceiveCount {

    @Id
    @NotNull
    @Column(name = "PAYLOAD_TYPE")
    @Enumerated(EnumType.STRING)
    private PayloadType payloadType;

    @NotNull
    @Column(name = "TIMES_RECEIVED")
    private int timesReceived = 0;

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    public int getTimesReceived() {
        return timesReceived;
    }

    public void setTimesReceived(int timesReceived) {
        this.timesReceived = timesReceived;
    }

}
