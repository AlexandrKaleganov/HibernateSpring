package ru.akaleganov.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * файл конфигурации и нам необходимо сканировать также файла
 * которые находятся в папке ru.akaleganov
 */
@Configuration
@ComponentScan("ru.akaleganov")
@EntityScan("ru.akaleganov.domain")
@EnableJpaRepositories("ru.akaleganov.repository")
public class SpringRootConfig {
}
