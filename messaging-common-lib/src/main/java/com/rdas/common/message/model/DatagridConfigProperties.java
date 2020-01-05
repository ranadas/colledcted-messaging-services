package com.rdas.common.message.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "reporting.queues")
public class DatagridConfigProperties {

    @Getter @Setter
    private String controlQueueName;

    @Getter @Setter
    private String datagenQueueName;

    @Getter @Setter
    private String rptQueueName;

    @Getter @Setter
    private String distroQueueName;

}
