package org.fastercode.idgenerator.spring.starter;

import org.fastercode.idgenerator.core.IDGenDistributed;
import org.fastercode.idgenerator.core.generator.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author huyaolong
 */
@Configuration
@ConditionalOnClass({IDGenDistributed.class})
@EnableConfigurationProperties(IDGeneratorProperties.class)
public class IDGeneratorAutoConfiguration {

    @Autowired(required = false)
    private IDGeneratorProperties idGeneratorProperties;

    @Lazy(value = false)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @ConditionalOnMissingBean({IDGenDistributed.class})
    @Bean(initMethod = "init", destroyMethod = "close", name = "idGenerator")
    public IDGenerator idGenDistributed() {
        return new IDGenDistributed(this.idGeneratorProperties);
    }
}
