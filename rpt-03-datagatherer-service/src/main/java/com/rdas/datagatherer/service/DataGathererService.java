package com.rdas.datagatherer.service;

import com.rdas.common.message.model.DatagenMessage;

public interface DataGathererService {

    void processStep(DatagenMessage datagenMessage);
}
