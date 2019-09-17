package ru.akaleganov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * файл конфигурации и нам необходимо сканировать также файла
 * которые находятся в папке ru.akaleganov
 */
@Configuration
@ComponentScan("ru.akaleganov")
public class SpringRootConfig {
}
