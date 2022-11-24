package pl.pm.currencyaccounts.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.function.client.WebClient
@Configuration
@Profile("!test")
class WebClientConfig(

    @Value("\${nbp.api.server.url}")
    val nbpApiServerUrl: String
) {
    @Bean
    fun webClient() =
        WebClient
            .builder()
            .baseUrl(nbpApiServerUrl)
            .build()
}