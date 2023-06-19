package me.devyonghee.gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(CustomFilter::class.java)

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            logger.info("Custom PRE filter: request id -> {}", exchange.request.id)

            chain.filter(exchange).then(
                Mono.fromRunnable {
                    logger.info("Custom POST filter: response status code -> {}", exchange.response.statusCode)
                }
            )
        }
    }

    class Config
}
