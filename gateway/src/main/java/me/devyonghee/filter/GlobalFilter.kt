package me.devyonghee.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(GlobalFilter::class.java)

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->

            logger.info("Global PRE filter baseMessage: `{}`", config.baseMessage)
            if (config.preLogger) {
                logger.info("Global PRE filter start: request id -> {}", exchange.request.id)
            }

            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    logger.info("Custom POST filter: response status code -> {}", exchange.response.statusCode)
                }
            })
        }
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    )
}