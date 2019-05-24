package com.roberto.avitec;

import com.roberto.avitec.config.ApplicationProperties;
import com.roberto.avitec.config.DefaultProfileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ComponentScan
@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class AviTecApp {

    private static final Logger log = LoggerFactory.getLogger(com.roberto.avitec.AviTecApp.class);
    private final Environment env;

    public AviTecApp(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(com.roberto.avitec.AviTecApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Aplicacao '{}' esta rodando! Acesso:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "Externo: \t{}://{}:{}\n\t" +
                "Perfil: \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            "http",
            env.getProperty("server.port"),
            "http",
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getActiveProfiles());
    }
}
