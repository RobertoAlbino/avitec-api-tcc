package com.roberto.avitec.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class DateTimeFormatConfiguration {

    private final Logger log = LoggerFactory.getLogger(DateTimeFormatConfiguration.class);

    @PostConstruct
    void started() {
        log.debug("Setando timezone");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-03:00"));
    }

    @Bean
    public FormattingConversionService addFormatters(FormatterRegistry registry) {
        DefaultFormattingConversionService conversionService =
                new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        registrar.registerFormatters(conversionService);
        registrar.setUseIsoFormat(false);
        registrar.registerFormatters(registry);
        log.debug("Registrando padrão de data");
        return conversionService;
    }
}
