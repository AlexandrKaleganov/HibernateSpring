package ru.akaleganov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * @author Kaleganov Alexander
 * @version 0.0.1
 * @project HibernateSpring
 * @since 06 янв. 20
 */
@Configuration
@ComponentScan("ru.akaleganov")
@EntityScan("ru.akaleganov.domain")
@EnableJpaRepositories("ru.akaleganov.repository")
public class SpringTestConfig {
    private final Logger log = LoggerFactory.getLogger(SpringTestConfig.class);
}
