package me.devyonghee.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean

//application.yml에 설정한 라우팅 정보를 코드로 작성
//@Configuration
class FilterConfiguration {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            route {
                path("/first-service/**")
                uri("http://localhost:8081/")
                filters {
                    addRequestHeader("first-request", "first-request-header")
                        .addResponseHeader("first-response", "first-response-header")
                }
            }
            route {
                path("/second-service/**")
                uri("http://localhost:8082/")
                filters {
                    addRequestHeader("second-request", "second-request-header")
                        .addResponseHeader("second-response", "second-response-header")
                }
            }
        }
    }
}