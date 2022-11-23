package pl.pm.currencyaccounts.integration.api

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import pl.pm.currencyaccounts.core.enum.Currency
import pl.pm.currencyaccounts.core.util.createUrl
import pl.pm.currencyaccounts.integration.model.CurrencyExchangeRate
import pl.pm.currencyaccounts.integration.model.CurrencyRate
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

@Component
internal class ExchangeRatesClient(private val client: WebClient) {

    fun getExchangeRates(currency: Currency): Flux<Pair<CurrencyRate, Currency>> =
        client.get()
            .uri(createUrl(currency))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CurrencyExchangeRate::class.java)
            .flatMapMany(::mapExchangeRates)

    private fun mapExchangeRates(response: CurrencyExchangeRate) =
        response
            .rates
            .map { Pair(CurrencyRate(it.effectiveDate, it.bid, it.ask), response.code) }
            .toFlux()
}


