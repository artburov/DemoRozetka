package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config/email.properties"})
public interface EmailCredConfig extends Config {

    @Key("signing.user.login")
    String userLogin();

    @Key("signing.email.login")
    String emailLogin();

    @Key("signing.number.login")
    String numberLogin();
}