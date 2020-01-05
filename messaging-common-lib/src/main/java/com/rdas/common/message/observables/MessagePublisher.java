package com.rdas.common.message.observables;

import com.rdas.common.message.model.ControlMessage;
import com.rdas.common.message.model.ReportMessage;

public interface MessagePublisher<T extends ReportMessage> {
    int getPendingMessageCount();

    void publish(ReportMessage reportMessage);
}
