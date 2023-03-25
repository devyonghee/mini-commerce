package me.devyonghee.commerce.application.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["me.devyonghee.commerce"])
@EnableJpaRepositories(basePackages = ["me.devyonghee.commerce"])
class JpaConfiguration 