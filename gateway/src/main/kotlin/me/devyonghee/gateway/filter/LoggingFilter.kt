package me.devyonghee.gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun apply(config: Config): GatewayFilter {
        return OrderedGatewayFilter({ exchange, chain ->

            logger.info("Logging PRE filter baseMessage: `{}`", config.baseMessage)
            if (config.preLogger) {
                logger.info("Logging PRE filter start: request id -> {}", exchange.request.id)
            }

            chain.filter(exchange).then(
                Mono.fromRunnable {
                    if (config.postLogger) {
                        logger.info("Logging POST filter: response status code -> {}", exchange.response.statusCode)
                    }
                }
            )
        }, Ordered.HIGHEST_PRECEDENCE)
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    )
}
