package me.devyonghee.commerce.application.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = ["me.devyonghee.commerce"])
@EnableJpaRepositories(basePackages = ["me.devyonghee.commerce"])
class JpaConfiguration {


    @Component
    class JapAuditorAware : AuditorAware<String> {
        override fun getCurrentAuditor(): Optional<String> {
            return SecurityContextHolder.getContext()
                .authentication?.let { Optional.of(it.name) }
                ?: Optional.empty()
        }
    }
}