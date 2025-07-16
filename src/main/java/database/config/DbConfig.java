package database.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.property")
public interface DbConfig extends Config {
    @Key("db.url")
    String url();

    @Key("db.username")
    String username();

    @Key("db.password")
    String password();
}

