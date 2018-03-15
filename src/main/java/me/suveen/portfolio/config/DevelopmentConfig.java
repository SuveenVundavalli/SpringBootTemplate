package me.suveen.portfolio.config;

import me.suveen.portfolio.backend.service.EmailService;
import me.suveen.portfolio.backend.service.MockEmailService;
import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/.portfolio/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService() {

        return new MockEmailService();
    }
    public ServletRegistrationBean mySqlConsoleServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebdavServlet());
        bean.addUrlMappings("/h2-console/*");
        return bean;
    }
}
