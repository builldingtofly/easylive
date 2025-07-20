package com.easylive.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
//@ConditionalOnProperty(prefix = "AdminBean",name = "enabled", havingValue = "true")
public class Appconfig {
    @Value("${project.folder}")
    private String projectFolder;
    @Value("${admin.account:0}")
    private String adminAcount;
    @Value("${admin.password:0}")
    private String adminPassword;
    @Value("${admin.showFFmegLog:true}")
    private Boolean showLog;
}
