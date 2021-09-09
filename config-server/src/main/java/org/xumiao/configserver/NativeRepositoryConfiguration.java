package org.xumiao.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.NativeEnvironmentProperties;
import org.springframework.cloud.config.server.environment.NativeEnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * configserver默认读取netive时需要改环境,这里覆盖本地属性
 */
@Configuration
public class NativeRepositoryConfiguration {

    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    public NativeEnvironmentRepository nativeEnvironmentRepository() {
        NativeEnvironmentProperties nativeEnvironmentProperties = new NativeEnvironmentProperties();
        nativeEnvironmentProperties.setSearchLocations(new String[]{"classpath:/properties/" + environment.getActiveProfiles()[0]});
        return new NativeEnvironmentRepository(
                this.environment, nativeEnvironmentProperties);
    }
}
