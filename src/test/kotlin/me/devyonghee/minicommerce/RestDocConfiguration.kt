package me.devyonghee.minicommerce

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint


@TestConfiguration
class RestDocConfiguration {

    @Bean
    fun customizer(): RestDocsMockMvcConfigurationCustomizer {
        return RestDocsMockMvcConfigurationCustomizer { configurer ->
            configurer.operationPreprocessors()
                .withRequestDefaults(
                    prettyPrint(),
                    modifyUris().scheme("http")
                        .host("localhost")
                        .removePort()
                ).withResponseDefaults(
                    prettyPrint(),
                    modifyUris().scheme("https")
                        .host("localhost")
                        .removePort()
                )
        }
    }
}