package com.rdas.datagatherer.service.impl;

import com.rdas.common.message.model.DatagenMessage;
import com.rdas.datagatherer.service.DataGathererService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DataGathererServiceImpl implements DataGathererService {
    @Override
    public void processStep(DatagenMessage datagenMessage) {
        log.info("Processing datagen Message, run sql against db ");
        // push a
        //controlMessagePublisher.publish(controlMessage);
    }
}
