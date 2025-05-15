package DataBaseManageServices.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:project_config.property")
public interface DbConfig extends Config {
    @Key("db.url")
    String url();

    @Key("db.username")
    String username();

    @Key("db.password")
    String password();
}

