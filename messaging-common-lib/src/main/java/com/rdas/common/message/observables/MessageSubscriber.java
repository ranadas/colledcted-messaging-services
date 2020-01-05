package com.rdas.common.message.observables;

import com.rdas.common.message.model.ReportMessage;

public interface MessageSubscriber<T extends ReportMessage> {
    int getPendingMessageCount();
}
