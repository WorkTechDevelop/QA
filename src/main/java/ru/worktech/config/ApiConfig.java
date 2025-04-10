package ru.worktech.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:project_config.property")
public interface ApiConfig extends Config {

    @Config.Key("baseUrl")
    String baseUrl();
}