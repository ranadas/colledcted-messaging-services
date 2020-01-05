package com.rdas.orchestrator.config;

import com.rdas.common.message.model.DatagridConfigProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatagridConfigProperties.class})
@ComponentScan({"com.rdas.common.message.observables.impl", "com.rdas.orchestrator.service.impl"}) // come observables are in this package. Make it explicit config classes.
public class ApplicationConfig {

}
