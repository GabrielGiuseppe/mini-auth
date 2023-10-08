package com.vr.miniauth.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.vr.miniauth")
@EnableJpaRepositories("com.vr.miniauth")
@EnableAsync
@EnableTransactionManagement
public class MiniAuthConfiguration {
}
