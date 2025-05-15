package ru.worktech.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:project_config.property")
public interface ApiConfig extends Config {
    @Key("api.baseUrl")
    String baseUrl();

    @Key("api.username")
    String username();

    @Key("api.password")
    String password();
}