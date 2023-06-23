package me.devyonghee.gateway.filter

import io.jsonwebtoken.Jwts
import me.devyonghee.gateway.configuration.JwtProperty
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(
    private val jwtProperty: JwtProperty
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(AuthorizationHeaderFilter::class.java)

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request

            val authorization: Collection<String>? = request.headers[HttpHeaders.AUTHORIZATION]
            if (authorization.isNullOrEmpty()) {
                return@GatewayFilter onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED)
            }

            val jwt: String = authorization.first().replace("Bearer ", "")
            if (isNotJwtValid(jwt)) {
                return@GatewayFilter onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED)
            }
            chain.filter(exchange)
        }
    }

    private fun onError(exchange: ServerWebExchange, error: String, httpStatus: HttpStatus): Mono<Void> {
        logger.error(error)
        return exchange.response.apply { statusCode = httpStatus }.setComplete()
    }

    private fun isNotJwtValid(jwt: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(jwtProperty.secret)
                .build()
                .parseClaimsJws(jwt)
                .body
                .subject
                .isNullOrEmpty()
        } catch (e: Exception) {
            false
        }
    }

    class Config
}
