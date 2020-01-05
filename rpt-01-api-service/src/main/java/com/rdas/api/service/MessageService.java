package com.rdas.api.service;

import com.rdas.common.message.model.ControlMessage;

public interface MessageService {

    String startProcess(ControlMessage controlMessage);
}
