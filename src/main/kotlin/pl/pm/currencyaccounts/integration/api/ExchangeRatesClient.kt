package pl.pm.currencyaccounts.integration.api

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.core.util.createUrl
import pl.pm.currencyaccounts.integration.model.CurrencyExchangeRate
import pl.pm.currencyaccounts.integration.model.CurrencyRate
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.time.LocalDate

@Component
internal class ExchangeRatesClient(private val client: WebClient) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
    fun getExchangeRates(
        currency: Currency,
        localDate: LocalDate
    ): Mono<Pair<CurrencyRate, Currency>> {
        log.trace("Get currency rate currency: $currency and date: $localDate")
        return client.get()
            .uri(createUrl(currency, localDate))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CurrencyExchangeRate::class.java)
            .flatMapMany(::mapExchangeRates)
            .next()
    }

    private fun mapExchangeRates(response: CurrencyExchangeRate) =
        response
            .rates
            .map { CurrencyRate(it.effectiveDate, it.bid, it.ask) to response.code }
            .toFlux()
}


