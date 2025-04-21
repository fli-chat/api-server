package com.project.filchat.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("com.project.filchat.infrastructure.config.properties")
public class PropertiesConfig {
}
